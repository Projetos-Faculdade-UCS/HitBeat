package hitbeat.dao;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import hitbeat.model.BaseModel;
import hitbeat.util.HibernateUtil;

/**
 * Classe abstrata de Data Access Object para fornecer operações básicas de
 * banco de dados
 * para qualquer modelo que estende BaseModel.
 *
 * @param <T> - O tipo do modelo que este DAO manipula, que deve estender
 *            BaseModel.
 */
public abstract class BaseDAO<T extends BaseModel> {
    private static final int BATCH_SIZE = 100;
    private Class<T> modelClass;
    private String className;

    /**
     * Construtor para inicializar o DAO com uma classe de modelo específica.
     *
     * @param modelClass - O tipo de classe do modelo que este DAO manipulará.
     */
    public BaseDAO(Class<T> modelClass) {
        this.modelClass = modelClass;
        this.className = modelClass.getSimpleName();
    }

    /**
     * Recupera todos os registros do modelo no banco de dados.
     *
     * @return Uma lista de todos os registros do tipo T.
     */
    public List<T> getAll() {
        return executeMethod(session -> {
            String hql = String.format("FROM %s", this.className);
            Query<T> query = session.createQuery(hql, modelClass);
            return query.list();
        });
    }

    /**
     * Recupera um registro específico pelo seu ID.
     *
     * @param id - O ID do registro a ser recuperado.
     * @return O registro com o ID especificado ou null se não for encontrado.
     */
    public T get(Long id) {
        return executeMethod(session -> {
            return session.get(modelClass, id);
        });
    }

    /**
     * Recupera o primeiro registro encontrado no banco de dados.
     *
     * @return O primeiro registro encontrado ou null se a tabela estiver vazia.
     */
    public T first() {
        return executeMethod(session -> {
            String hql = String.format("FROM %s", this.className);
            Query<T> query = session.createQuery(hql, modelClass);
            query.setMaxResults(1);
            return query.uniqueResult();
        });
    }

    /**
     * Salva ou atualiza o registro fornecido no banco de dados.
     *
     * @param objectT - O registro a ser salvo ou atualizado.
     */
    public void save(T objectT) {
        executeMethod(session -> {
            session.beginTransaction();
            session.merge(objectT);
            session.getTransaction().commit();
            return null;
        });
    }

    public void saveAll(List<T> objects) {
        executeMethod(session -> {
            session.beginTransaction();
            for (T t : objects) {
                session.merge(t);
            }
            session.getTransaction().commit();
            return null;
        });
    }

    /**
     * Método utilitário para manipular operações de sessão do Hibernate e garantir
     * a limpeza da sessão.
     *
     * @param <R>      - O tipo do objeto de retorno.
     * @param function - Uma função lambda com a operação de sessão.
     * @return O resultado da função (tipicamente um registro de banco de dados).
     */
    protected <R> R executeMethod(Function<Session, R> function) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            R objectT = function.apply(session);
            transaction.commit();
            return objectT;
        } catch (Exception e) {
            rollbackTransaction(session);
            throw e;
        } finally {
            closeSession(session);
        }
    }

    /**
     * Fecha com segurança a sessão do Hibernate fornecida.
     *
     * @param session - A sessão a ser fechada.
     */
    private void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    /**
     * Desfaz a transação atual em caso de erro.
     *
     * @param session - A sessão contendo a transação a ser desfeita.
     */
    private void rollbackTransaction(Session session) {
        if (session != null && session.getTransaction() != null) {
            session.getTransaction().rollback();
        }
    }

    public void saveAllEntities(List<T> entities, String uniqueField) {
        executeMethod(session -> {
            Map<String, T> existingEntitiesMap = getExistingEntitiesMap(entities, session, uniqueField);

            for (int i = 0; i < entities.size(); i++) {
                T entity = entities.get(i);
                String uniqueValue = getEntityFieldValue(entity, uniqueField);
                T existingEntity = existingEntitiesMap.get(uniqueValue);

                if (existingEntity != null) {
                    updateEntityProperties(existingEntity, entity);
                    session.merge(existingEntity);
                } else {
                    session.persist(entity);
                }

                if ((i + 1) % BATCH_SIZE == 0) {
                    session.flush();
                    session.clear();
                }
            }

            session.flush();
            session.clear();

            return null;
        });
    }

    private Map<String, T> getExistingEntitiesMap(List<T> entities, Session session, String uniqueField) {
        List<String> uniqueValues = entities.stream()
                .map(entity -> getEntityFieldValue(entity, uniqueField))
                .collect(Collectors.toList());
        String hql = "FROM " + modelClass.getName() + " e WHERE e." + uniqueField + " IN (:values)";
        Query<T> query = session.createQuery(hql, modelClass);
        query.setParameterList("values", uniqueValues);
        List<T> existingEntities = query.list();
        return existingEntities.stream()
                .collect(Collectors.toMap(entity -> getEntityFieldValue(entity, uniqueField), entity -> entity));
    }

    protected String getEntityFieldValue(T entity, String field) {
        try {
            return (String) PropertyUtils.getProperty(entity, field);
        } catch (Exception e) {
            throw new RuntimeException("Error reading field: " + field, e);
        }
    }

    protected abstract void updateEntityProperties(T existingEntity, T newEntity);
}
