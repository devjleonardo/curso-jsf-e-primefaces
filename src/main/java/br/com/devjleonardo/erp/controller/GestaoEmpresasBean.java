package br.com.devjleonardo.erp.controller;

import br.com.devjleonardo.erp.model.Empresa;
import br.com.devjleonardo.erp.repository.EmpresaRepository;

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

    private List<Empresa> listaEmpresas;

    public void todasEmpresas() {
        listaEmpresas = empresaRepository.todasEmpresas();
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

}
