package br.com.devjleonardo.erp.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public class FacesMessages implements Serializable {

    private static final long serialVersionUID = 1L;

    public void info(String mensagem) {
        add(mensagem, FacesMessage.SEVERITY_INFO);
    }

    private void add(String mensagem, FacesMessage.Severity severity) {
        FacesMessage facesMessage = new FacesMessage(mensagem);
        facesMessage.setSeverity(severity);

        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

}
