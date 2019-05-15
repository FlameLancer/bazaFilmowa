package dao;

import java.util.List;

import core.Cast;

public class CastDAO extends DAO<Cast, Integer>{

    @Override
    public Cast findById(Integer id) {
        openCurrentSession();
        Cast cast = (Cast) getCurrentSession().get(Cast.class, id);
        closeCurrentSession();
        return cast;   
    }

    @Override
    public List<Cast> findAll() {
        openCurrentSession();
        List<Cast> cast = (List<Cast>) getCurrentSession().createQuery("from Cast").list();
        closeCurrentSession();
        return cast;
    }

    @Override
    public List<Cast> getAll() {

        List<Cast> lista = openCurrentSessionwithTransaction().createCriteria(Cast.class).list();
        closeCurrentSessionwithTransaction();
        return lista;
    };

    public List<Cast> getByName(String name){

        openCurrentSession();
        String sV = "%"+name+"%";
        List<Cast> res = getCurrentSession().createQuery("from Cast where name like '"+sV+"' order by name").list();
        closeCurrentSession();
        return res;

    }

    public List<Cast> getBySurname(String name){

        openCurrentSession();
        String sV = "%"+name+"%";
        List<Cast> res = getCurrentSession().createQuery("from Cast where surname like '"+sV+"' order by surname").list();
        closeCurrentSession();
        return res;

    }
}
