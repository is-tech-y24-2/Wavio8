package dao;

import Entity.CatEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class CatDao {

    public void saveNewCat(CatEntity cat) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernate.beginTransaction();
        sessionHibernate.save(cat);
        transaction.commit();
        sessionHibernate.close();
    }

    public CatEntity findCatById(int id) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return sessionHibernate.get(CatEntity.class, id);
    }

    public void updateCatData(CatEntity cat) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernate.beginTransaction();
        sessionHibernate.update(cat);
        transaction.commit();
        sessionHibernate.close();
    }

    public List<CatEntity> getAllCatTable() {
        List<CatEntity> catEntities = (List<CatEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CatEntity ").list();
        return catEntities;
    }

    public void deleteCat(CatEntity cat) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernate.beginTransaction();
        sessionHibernate.delete(cat);
        transaction.commit();
        sessionHibernate.close();
    }

}