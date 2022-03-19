package dao;


import Entity.OwnerCatsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class OwnerCatsDao {

    public void saveOwnerCats(OwnerCatsEntity ownerCatsEntity) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernate.beginTransaction();
        sessionHibernate.save(ownerCatsEntity);
        transaction.commit();
        sessionHibernate.close();
    }

    public void updateOwnerCatsEntity(OwnerCatsEntity ownerCatsEntity) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernate.beginTransaction();
        sessionHibernate.update(ownerCatsEntity);
        transaction.commit();
        sessionHibernate.close();
    }

    public OwnerCatsEntity findOwnerCatsById(int id) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return sessionHibernate.get(OwnerCatsEntity.class, id);
    }

    public List<OwnerCatsEntity> getAllOwnerCats() {
        List<OwnerCatsEntity> ownerCatsEntity = (List<OwnerCatsEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From OwnerCatsEntity ").getResultList();
        return ownerCatsEntity;
    }

    public void deleteOwnerCats(OwnerCatsEntity ownerCatsEntity) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernate.beginTransaction();
        sessionHibernate.delete(ownerCatsEntity);
        transaction.commit();
        sessionHibernate.close();
    }

}