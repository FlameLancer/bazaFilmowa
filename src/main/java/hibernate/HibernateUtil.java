package hibernate;

import core.Cast;
import core.Countries;
import core.Genres;
import core.Languages;
import core.Movies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    
    private Session currentSession;
    private Transaction currentTransaction;
    private static ServiceRegistry serviceRegistry;
    private static String mainConfiguration;
    private static SessionFactory sessionFactory;
    
    public static void OpenConnection(String configurationPath){
        mainConfiguration = configurationPath;
        setSessionFactory(mainConfiguration);
    }
    
    private static Configuration setClassesConfiguration(Configuration configuration){

        configuration.addAnnotatedClass(Cast.class);
        configuration.addAnnotatedClass(Countries.class);
        configuration.addAnnotatedClass(Genres.class);
        configuration.addAnnotatedClass(Languages.class);
        configuration.addAnnotatedClass(Movies.class);

        return configuration;
    }
    
    public static void CloseConnection(){
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
     
    }
    
    public static String getMainConfiguration(){
        return mainConfiguration;
    }
    
    public static ServiceRegistry getServiceRegistry() {
        return HibernateUtil.serviceRegistry;
    }
    
    private static void setSessionFactory(String pathConfiguration) {
        Configuration configuration = new Configuration()
                .configure(pathConfiguration);
        
        configuration = setClassesConfiguration(configuration);

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();

         sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        
    }
        
    public Session openCurrentSession() {
        currentSession = sessionFactory.openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
}
