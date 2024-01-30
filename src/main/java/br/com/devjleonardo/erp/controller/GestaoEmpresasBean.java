package br.com.devjleonardo.erp.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Integer NUMERO = 0;

    public GestaoEmpresasBean() {
        NUMERO++;
    }

    public Integer getNumero() {
        return NUMERO;
    }
}
