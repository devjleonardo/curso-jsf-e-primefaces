package br.com.devjleonardo.erp.repository;

import br.com.devjleonardo.erp.model.Empresa;
import br.com.devjleonardo.erp.model.RamoAtividade;
import br.com.devjleonardo.erp.model.TipooEmpresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class CamadaPersistencia {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("DevjleonardoPU");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();;

        // Declarando os repositórios
        RamoAtividadeRepository ramoAtividadeRepository = new RamoAtividadeRepository(entityManager);
        EmpresaRepository empresaRepository = new EmpresaRepository(entityManager);

        //Buscando as informações do banco
        List<RamoAtividade> listaDeRamoAtivdades = ramoAtividadeRepository.pesquiarPorDescricao("");
        List<Empresa> listaDeEmpresas = empresaRepository.pesquisarPorNomeFantasia("");

        // Exibindo a lista das empresas atuais no banco
        System.out.println("empresas: " + listaDeEmpresas);

        // Criando uma empresa
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia("João da Silva");
        empresa.setRazaoSocial("João da Silva 419525519000157");
        empresa.setCnpj("41.952.519/0001-57");
        empresa.setDataFundacao(new Date());
        empresa.setTipo(TipooEmpresa.MEI);
        empresa.setRamoAtividade(listaDeRamoAtivdades.get(0));

        // Salvando a empresa
        empresaRepository.salvar(empresa);

        entityManager.getTransaction().commit();

        // Verificando se a inserção funcionou
        listaDeEmpresas = empresaRepository.pesquisarPorNomeFantasia("");

        // Exibindo a lista das empresas no banco e com a nova empresa já adicionada
        System.out.println("empresas: " + listaDeEmpresas);

        entityManager.close();
        entityManagerFactory.close();;
    }

}
