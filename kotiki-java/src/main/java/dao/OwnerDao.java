package dao;

import Entity.OwnerEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OwnerDao {
    public void saveOwner(OwnerEntity ownerEntity) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernate.beginTransaction();
        sessionHibernate.save(ownerEntity);
        transaction.commit();
        sessionHibernate.close();
    }

    public void updateOwner(OwnerEntity ownerEntity) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernate.beginTransaction();
        sessionHibernate.update(ownerEntity);
        transaction.commit();
        sessionHibernate.close();
    }

    public OwnerEntity findOwnerById(int id) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return sessionHibernate.get(OwnerEntity.class, id);
    }

    public List<OwnerEntity> getAllOwnerTable() {
        List<OwnerEntity> ownerEntities = (List<OwnerEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From OwnerEntity ").list();
        return ownerEntities;
    }

    public void deleteOwner(OwnerEntity ownerEntity) {
        Session sessionHibernste = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernste.beginTransaction();
        sessionHibernste.delete(ownerEntity);
        transaction.commit();
        sessionHibernste.close();
    }

}
