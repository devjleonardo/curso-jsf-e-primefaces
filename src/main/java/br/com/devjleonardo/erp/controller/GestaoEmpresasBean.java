package br.com.devjleonardo.erp.controller;

import br.com.devjleonardo.erp.model.Empresa;
import br.com.devjleonardo.erp.model.RamoAtividade;
import br.com.devjleonardo.erp.model.TipoEmpresa;
import br.com.devjleonardo.erp.repository.EmpresaRepository;
import br.com.devjleonardo.erp.repository.RamoAtividadeRepository;
import br.com.devjleonardo.erp.util.FacesMessages;

import javax.faces.convert.Converter;
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
    private RamoAtividadeRepository ramoAtividadeRepository;

    @Inject
    private FacesMessages messages;

    private String termoPesquisa;

    private Converter ramoAtividadeConverter;

    private List<Empresa> listaEmpresas;


    public void pesquisarPorRazaoSocialOuNomeFantasia() {
        listaEmpresas = empresaRepository.pesquisarPorRazaoSocialOuNomeFantasia(termoPesquisa);

        if (listaEmpresas.isEmpty()) {
            messages.info("Sua pesquisa não retornou registros.");
        }
    }

    public void todasEmpresas() {
        listaEmpresas = empresaRepository.todasEmpresas();
    }

    public List<RamoAtividade> completarRamoAtividade(String termo) {
        List<RamoAtividade> listaRamoAtividades = ramoAtividadeRepository.pesquiarPorDescricao(termo);

        ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);

        return listaRamoAtividades;
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

    public Converter getRamoAtividadeConverter() {
        return ramoAtividadeConverter;
    }

    public TipoEmpresa[] getTiposEmpresa() {
        return TipoEmpresa.values();
    }

}
