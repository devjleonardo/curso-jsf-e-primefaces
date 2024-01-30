package br.com.devjleonardo.erp.controller;

import br.com.devjleonardo.erp.model.Empresa;
import br.com.devjleonardo.erp.model.TipoEmpresa;
import br.com.devjleonardo.erp.repository.EmpresaRepository;
import br.com.devjleonardo.erp.util.FacesMessages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaRepository empresaRepository;

    @Inject
    private FacesMessages messages;

    private List<Empresa> listaEmpresas;

    private String termoPesquisa;

    public void pesquisarPorRazaoSocialOuNomeFantasia() {
        listaEmpresas = empresaRepository.pesquisarPorRazaoSocialOuNomeFantasia(termoPesquisa);

        if (listaEmpresas.isEmpty()) {
            messages.info("Sua pesquisa não retornou registros.");
        }
    }

    public void todasEmpresas() {
        listaEmpresas = empresaRepository.todasEmpresas();
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public TipoEmpresa[] getTiposEmpresa() {
        return TipoEmpresa.values();
    }
}
