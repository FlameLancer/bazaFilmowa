package dao;

import java.util.List;

import core.Cast;
import core.Movies;
import lombok.NonNull;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

public class MoviesDAO extends DAO<Movies, Integer>{
    
    @Override
    public Movies findById(Integer id) {
        openCurrentSession();
        Movies movies = (Movies) getCurrentSession().get(Movies.class, id);
        closeCurrentSession();
        return movies;   
    }

    @Override
    public List<Movies> findAll() {
        openCurrentSession();
        List<Movies> movies = (List<Movies>) getCurrentSession().createQuery("from Country").list();
        closeCurrentSession();
        return movies;
    }

    @Transactional
    @Override
    public List<Movies> getAll() {

        List<Movies> lista = openCurrentSessionwithTransaction().createCriteria(Movies.class).list();
        closeCurrentSessionwithTransaction();
        return lista;
    };

    public List<Movies> getByOrgTitle(String title){

        openCurrentSession();
        String sV = "%"+title+"%";
        List<Movies> res = getCurrentSession().createQuery("from Movies where orgTitle like '"+sV+"' order by orgTitle").list();
        closeCurrentSession();
        return res;

    }
    public List<Movies> getByDirector(@NonNull Cast cast){

        openCurrentSession();
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Movies> query = builder.createQuery(Movies.class);
        Root<Movies> root = query.from(Movies.class);

        query.where(builder.equal(root.get("director"),cast));
        Query<Movies> q = getCurrentSession().createQuery(query);

        List<Movies> res = q.getResultList();

        closeCurrentSession();
        return res;

    }

}
