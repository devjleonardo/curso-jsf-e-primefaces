package br.com.devjleonardo.erp.repository;

import br.com.devjleonardo.erp.model.Empresa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class EmpresaRepository implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager entityManager;

    public EmpresaRepository() {
    }

    public EmpresaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Empresa buscarPorId(Long id) {
        return entityManager.find(Empresa.class, id);
    }

    public List<Empresa> todasEmpresas() {
        return entityManager.createQuery("FROM Empresa", Empresa.class).getResultList();
    }

    public List<Empresa> pesquisarPorRazaoSocialOuNomeFantasia(String termoPesquisa) {
        String jpql =
                "FROM Empresa " +
                "WHERE razaoSocial LIKE :termoPesquisa OR nomeFantasia LIKE :termoPesquisa";

        TypedQuery<Empresa> query = entityManager.createQuery(jpql, Empresa.class);

        query.setParameter("termoPesquisa", termoPesquisa + "%");

        return query.getResultList();
    }

    public Empresa salvar(Empresa empresa) {
        return entityManager.merge(empresa);
    }

    public void remover(Empresa empresa) {
        empresa = buscarPorId(empresa.getId());

        entityManager.remove(empresa);
    }

}
