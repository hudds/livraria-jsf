package br.com.alura.livraria.log;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class LogInterceptor {

	
	@AroundInvoke
	public Object execute(InvocationContext context) throws Exception {
		long before = System.currentTimeMillis();
		Object result = context.proceed();
		System.out.println("Execution time for method " + context.getMethod().getName() + ": " + (System.currentTimeMillis() - before)	);
		return result;
	}
	
}
