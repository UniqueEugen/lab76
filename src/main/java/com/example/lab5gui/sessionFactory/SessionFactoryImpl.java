package com.example.lab5gui.sessionFactory;

import com.example.lab5gui.entities.ServiseClass;
import com.example.lab5gui.entities.ferret.FerretEntity;
import com.example.lab5gui.entities.master.MasterEntity;
import lombok.NoArgsConstructor;
import myLibrary.console.Console;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor
public class SessionFactoryImpl {
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            /*try {*/
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(MasterEntity.class);
                configuration.addAnnotatedClass(FerretEntity.class);
                configuration.addAnnotatedClass(ServiseClass.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            /*} catch (Exception e) {
                throw new RuntimeException(e);
            }*/
        }
        return sessionFactory;
    }
}
