package dao;

import java.util.List;

import core.Countries;

public class CountriesDAO  extends DAO<Countries, Integer> {
    
    @Override
    public Countries findById(Integer id) {
        openCurrentSession();
        Countries country = (Countries) getCurrentSession().get(Countries.class, id);
        closeCurrentSession();
        return country;   
    }

    @Override
    public List<Countries> findAll() {
        openCurrentSession();
        List<Countries> country = (List<Countries>) getCurrentSession().createQuery("from Country").list();
        closeCurrentSession();
        return country;
    }

    @Override
    public List<Countries> getAll() {

        List<Countries> lista = openCurrentSessionwithTransaction().createCriteria(Countries.class).list();
        closeCurrentSessionwithTransaction();
        return lista;
    };
}
