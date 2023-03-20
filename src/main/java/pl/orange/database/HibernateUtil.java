package pl.orange.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.orange.model.City;

public class HibernateUtil {

    private static SessionFactory factory;

    public static SessionFactory getFactory(){

        if (factory == null){
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(City.class)
                    .buildSessionFactory();
        }

        return factory;
    }
}
