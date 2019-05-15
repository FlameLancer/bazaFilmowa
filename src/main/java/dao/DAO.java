package dao;

import java.io.Serializable;
import java.util.List;

import hibernate.HibernateUtil;

public abstract class DAO<T, Id extends Serializable> extends HibernateUtil {
    
    public void saveOrUpdate(T entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().saveOrUpdate(entity);
        closeCurrentSessionwithTransaction();
    }
    
    public abstract T findById(Id id);

    public abstract List<T> getAll();
      
    public abstract List<T> findAll();
    
    public void delete(T entity) {
        openCurrentSessionwithTransaction();
        getCurrentSession().delete(entity);
        closeCurrentSessionwithTransaction();
    }

    public void deleteAll() {      
        List<T> entityList = findAll();
        for (T entity : entityList) {
            delete(entity);
        }
    }
}
