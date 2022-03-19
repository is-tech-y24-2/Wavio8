package dao;


import Entity.CatsFriendEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class CatsFriendDao {

    public CatsFriendEntity findCatsFriendById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CatsFriendEntity.class, id);
    }

    public void saveNewData(CatsFriendEntity catsFriendEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(catsFriendEntity);
        transaction.commit();
        session.close();
    }

    public List<CatsFriendEntity> getAllTableCatsFriend() {
        List<CatsFriendEntity> catsFriendEntities = (List<CatsFriendEntity>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From CatsFriendEntity ").list();
        return catsFriendEntities;
    }

    public void updateDataAfterChange(CatsFriendEntity catsFriendEntity) {
        Session sessionHibernate = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = sessionHibernate.beginTransaction();
        sessionHibernate.update(catsFriendEntity);
        transaction.commit();
        sessionHibernate.close();
    }

    public void deleteCatsFriend(CatsFriendEntity catsFriendEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(catsFriendEntity);
        transaction.commit();
        session.close();
    }

}