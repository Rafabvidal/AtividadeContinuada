package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;

public abstract class RepositorioGeral {

    private final DAOSerializadorObjetos dao;

    public RepositorioGeral() {
        this.dao = new DAOSerializadorObjetos(getClasseEntidade());
    }

    public abstract Class<?> getClasseEntidade();

    public DAOSerializadorObjetos getDao() {
        return dao;
    }
}
