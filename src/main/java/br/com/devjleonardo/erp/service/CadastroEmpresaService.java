package br.com.devjleonardo.erp.service;

import br.com.devjleonardo.erp.model.Empresa;
import br.com.devjleonardo.erp.repository.EmpresaRepository;
import br.com.devjleonardo.erp.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroEmpresaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmpresaRepository empresaRepository;

    @Transacional
    public void salvar(Empresa empresa) {
        empresaRepository.salvar(empresa);
    }

    @Transacional
    public void excluir(Empresa empresa) {
        empresaRepository.remover(empresa);
    }

}
