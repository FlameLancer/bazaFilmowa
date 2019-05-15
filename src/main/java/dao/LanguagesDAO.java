package dao;

import java.util.List;

import core.Languages;

public class LanguagesDAO extends DAO<Languages, Integer>{
    
    @Override
    public Languages findById(Integer id) {
        openCurrentSession();
        Languages languages = (Languages) getCurrentSession().get(Languages.class, id);
        closeCurrentSession();
        return languages;   
    }

    @Override
    public List<Languages> findAll() {
        openCurrentSession();
        List<Languages> languages = (List<Languages>) getCurrentSession().createQuery("from Country").list();
        closeCurrentSession();
        return languages;
    }

    @Override
    public List<Languages> getAll() {

        List<Languages> lista = openCurrentSessionwithTransaction().createCriteria(Languages.class).list();
        closeCurrentSessionwithTransaction();
        return lista;
    };
}
