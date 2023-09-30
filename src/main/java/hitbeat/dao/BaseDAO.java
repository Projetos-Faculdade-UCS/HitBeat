package hitbeat.dao;

import java.util.List;

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
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        try  {
            session = sessionFactory.openSession();
            String hql = String.format("FROM %s", this.className);
            Query<T> query = session.createQuery(hql, modelClass);
            return query.list();
        }  catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public T get(Long id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hql = String.format("FROM %s g WHERE g.id = :id", this.className);
            Query<T> query = session.createQuery(hql, modelClass);
            query.setParameter("id", id);
            return query.uniqueResult();
        }  catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void save(T objectT) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(objectT);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
