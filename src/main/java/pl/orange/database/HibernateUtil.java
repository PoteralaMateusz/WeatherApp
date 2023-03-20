package pl.orange.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private SessionFactory factory;

    public SessionFactory getFactory(){

        if (factory == null){
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();
        }

        return factory;
    }
}
