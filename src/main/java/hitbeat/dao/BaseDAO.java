package hitbeat.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import hitbeat.model.BaseModel;
import hitbeat.util.HibernateUtil;

public abstract class BaseDAO<T extends BaseModel> {
    private Class<T> modelClass;

    public BaseDAO(Class<T> modelClass){
        this.modelClass = modelClass;
    }
    
    public List<T> getAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Genre";
            Query<T> query = session.createQuery(hql, modelClass);
            return query.list();
        }
    }

    public T get(Long id){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("FROM %s g WHERE g.id = :id", modelClass.getSimpleName());
            Query<T> query = session.createQuery(hql, modelClass);
            query.setParameter("name", id);
            return query.uniqueResult();
        }
    }

    public void save(T objectT) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(objectT);
            session.getTransaction().commit();
        }
    }
}
