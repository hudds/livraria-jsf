package br.com.alura.livraria.tx;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@FakeTransactional
@Interceptor
public class FakeTransactionManager implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object execute(InvocationContext context) throws Exception {
		System.out.println("FakeTransactionManager: Starting transaction");
		Object result = context.proceed();
		System.out.println("FakeTransactionManager: Committing transaction");
		return result;
	}

}
