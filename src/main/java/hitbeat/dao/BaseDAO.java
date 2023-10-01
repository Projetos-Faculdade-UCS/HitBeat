package hitbeat.dao;

import java.util.List;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hitbeat.model.BaseModel;
import hitbeat.util.HibernateUtil;

public abstract class BaseDAO<T extends BaseModel> {
    private Class<T> modelClass;
    private String className;

    public BaseDAO(Class<T> modelClass){
        this.modelClass = modelClass;
        this.className = modelClass.getSimpleName();
    }
    
    public List<T> getAll() {
        return executeMethod(session -> {
            String hql = String.format("FROM %s", this.className);
            Query<T> query = session.createQuery(hql, modelClass);
            return query.list();
        });
    }

    public T get(Long id){
        return executeMethod(session -> {
            return session.get(modelClass, id);
        });
    }

    public T first(){
        return executeMethod(session -> {
            String hql = String.format("FROM %s", this.className);
            Query<T> query = session.createQuery(hql, modelClass);
            query.setMaxResults(1);
            return query.uniqueResult();
        });
    }

    public void save(T objectT) {
        executeMethod(session -> {
            session.beginTransaction();
            session.merge(objectT);
            session.getTransaction().commit();
            return null;
        });
    }

    protected <R> R executeMethod(Function<Session,R> function) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            R objectT = function.apply(session);
            return objectT;
        } catch (Exception e) {
            rollbackTransaction(session);
            throw e;
        } finally {
            closeSession(session);
        }
    }

    private void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    private void rollbackTransaction(Session session) {
        if (session != null && session.getTransaction() != null) {
            session.getTransaction().rollback();
        }
    }
}
