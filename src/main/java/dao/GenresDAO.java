package dao;

import java.util.List;

import core.Genres;

public class GenresDAO extends DAO<Genres, Integer>{
    
    @Override
    public Genres findById(Integer id) {
        openCurrentSession();
        Genres genres = (Genres) getCurrentSession().get(Genres.class, id);
        closeCurrentSession();
        return genres;   
    }

    @Override
    public List<Genres> findAll() {
        openCurrentSession();
        List<Genres> genres = (List<Genres>) getCurrentSession().createQuery("from Country").list();
        closeCurrentSession();
        return genres;
    }

    @Override
    public List<Genres> getAll() {

        List<Genres> lista = openCurrentSessionwithTransaction().createCriteria(Genres.class).list();
        closeCurrentSessionwithTransaction();
        return lista;
    };
}
