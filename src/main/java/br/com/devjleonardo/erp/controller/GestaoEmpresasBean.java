package br.com.devjleonardo.erp.controller;

import br.com.devjleonardo.erp.model.Empresa;
import br.com.devjleonardo.erp.model.TipooEmpresa;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

   private Empresa empresa = new Empresa();

    public void salvar() {
        System.out.println(
                  "Nome fantasia: " + empresa.getNomeFantasia()
                + " - Razão social: " + empresa.getNomeFantasia()
                + " - Tipo: " + empresa.getTipo()
        );
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public TipooEmpresa[] getTiposEmpresa() {
        return TipooEmpresa.values();
    }

}
