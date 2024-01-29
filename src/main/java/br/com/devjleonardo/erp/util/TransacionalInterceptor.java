package br.com.devjleonardo.erp.util;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction entityTransaction = manager.getTransaction();
        boolean criador = false;

        try {
            if (!entityTransaction.isActive()) {
                // Truque para fazer rollback no que já passou
                // (senão, um futuro commit confirmaria até mesmo operações sem transação)
                entityTransaction.begin();
                entityTransaction.rollback();

                // Agora sim inicia a transação
                entityTransaction.begin();

                criador = true;
            }

            return context.proceed();
        } catch (Exception e) {
            if (entityTransaction != null && criador) {
                entityTransaction.rollback();
            }

            throw e;
        } finally {
            if (entityTransaction != null && entityTransaction.isActive() && criador) {
                entityTransaction.commit();
            }
        }
    }

}