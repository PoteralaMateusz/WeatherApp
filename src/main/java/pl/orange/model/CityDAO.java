package pl.orange.model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.orange.database.HibernateUtil;

import java.util.List;

public class CityDAO implements DataAccess<City,Long> {

    @Override
    public void save(City city) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {

        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.createQuery("from City", City.class).getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteByID(Long id) {
        Transaction transaction = null;
        try (Session session =HibernateUtil.getFactory().openSession()) {
            City cityToRemove = session.get(City.class,id);
            transaction = session.beginTransaction();
            session.remove(cityToRemove);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
