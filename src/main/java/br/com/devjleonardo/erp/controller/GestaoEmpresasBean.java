package br.com.devjleonardo.erp.controller;

import br.com.devjleonardo.erp.model.Empresa;
import br.com.devjleonardo.erp.model.RamoAtividade;
import br.com.devjleonardo.erp.model.TipoEmpresa;
import br.com.devjleonardo.erp.repository.EmpresaRepository;
import br.com.devjleonardo.erp.repository.RamoAtividadeRepository;
import br.com.devjleonardo.erp.service.CadastroEmpresaService;
import br.com.devjleonardo.erp.util.FacesMessages;
import org.primefaces.context.RequestContext;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
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
    private CadastroEmpresaService cadastroEmpresaService;

    @Inject
    private FacesMessages messages;

    private String termoPesquisa;

    private Converter ramoAtividadeConverter;

    private  Empresa empresa;

    private List<Empresa> listaEmpresas;

    public void todasEmpresas() {
        listaEmpresas = empresaRepository.todasEmpresas();
    }

    public void pesquisarPorRazaoSocialOuNomeFantasia() {
        listaEmpresas = empresaRepository.pesquisarPorRazaoSocialOuNomeFantasia(termoPesquisa);

        if (listaEmpresas.isEmpty()) {
            messages.info("Sua pesquisa não retornou registros.");
        }
    }

    public void prepararNovaEmpresa() {
        empresa = new Empresa();
    }

    public void prepararEdicao() {
        ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
    }

    public List<RamoAtividade> completarRamoAtividade(String termo) {
        List<RamoAtividade> listaRamoAtividades = ramoAtividadeRepository.pesquiarPorDescricao(termo);

        ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);

        return listaRamoAtividades;
    }

    public void salvar() {
        cadastroEmpresaService.salvar(empresa);

        atualizarRegistros();

        messages.info("Empresa salva com sucesso!");

        RequestContext.getCurrentInstance().update(Arrays.asList("form:messages", "form:empresasDataTable"));
    }

    public void excluir() {
        cadastroEmpresaService.excluir(empresa);

        empresa = null;

        atualizarRegistros();

        messages.info("Empresa excluída com sucesso!");
    }

    private void atualizarRegistros() {
        if (javaHouvePesquisa()) {
            pesquisarPorRazaoSocialOuNomeFantasia();
        } else {
            todasEmpresas();
        }
    }

    private boolean javaHouvePesquisa() {
        return termoPesquisa != null && !termoPesquisa.isEmpty();
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TipoEmpresa[] getTiposEmpresa() {
        return TipoEmpresa.values();
    }

    public boolean isEmpresaSelecionada() {
        return empresa != null && empresa.getId() != null;
    }

}
