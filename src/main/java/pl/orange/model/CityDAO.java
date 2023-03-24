package pl.orange.model;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.orange.database.HibernateUtil;

import java.util.List;

public class CityDAO implements DataAccess<City, Long> {

    private final static Logger LOGGER = Logger.getLogger(CityDAO.class);

    @Override
    public void save(City city) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("SAVE TO DB ERROR");
            e.printStackTrace();
        }
    }

    @Override
    public List<City> findAll() {

        try (Session session = HibernateUtil.getFactory().openSession()) {
            return session.createQuery("from City", City.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.error("SELECT FROM DB ERROR");
        return null;
    }

    @Override
    public void deleteByID(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            City cityToRemove = session.get(City.class, id);
            transaction = session.beginTransaction();
            session.remove(cityToRemove);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("DELETE BY ID FROM DB ERROR");
            e.printStackTrace();
        }

    }

    @Override
    public void deleteByName(City city) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getFactory().openSession()) {
            transaction = session.beginTransaction();
            List<City> cityToRemove = session.createQuery("from City where name='" + city.getName() + "'", City.class).getResultList();
            for (City cityFromDB : cityToRemove) {
                session.remove(cityFromDB);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("DELETE BY NAME FROM DB ERROR");
            e.printStackTrace();
        }
    }
}
