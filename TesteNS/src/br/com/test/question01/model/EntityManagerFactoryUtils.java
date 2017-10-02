package br.com.test.question01.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EntityManagerFactoryUtils {
	
	private static EntityManagerFactoryUtils instance=new EntityManagerFactoryUtils();
    private static EntityManagerFactory emf;
    
    public static EntityManagerFactoryUtils getInstance(){
            return instance;
    }
    
    private EntityManagerFactoryUtils(){
        emf = Persistence.createEntityManagerFactory("test");
    }
    
    public static EntityManagerFactory getEmf(){
       return emf;
    }
}
