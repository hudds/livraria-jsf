package br.com.alura.livraria.filter;

import static br.com.alura.livraria.session.SecuritySessionAttributesNames.LAST_PATH_UNAUTHORIZED;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.livraria.security.AccessRules;
import br.com.alura.livraria.session.SessionAttributes;
import br.com.alura.livraria.session.UserSession;

//@WebFilter("/*")
public class SecurityFilter implements Filter {
	
	@Inject
	private AccessRules accessRules;
	@Inject
	private UserSession userSession;
	@Inject
	private SessionAttributes sessionAttributes;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String requestURL = httpRequest.getRequestURL().toString();
			System.out.println("Request url: " + requestURL);
			String contextPath = httpRequest.getContextPath();
			System.out.println("contextPath: " + contextPath);
			String path = httpRequest.getRequestURI().substring(contextPath.length());
			System.out.println("Request Path: " + path);
			System.out.println("Must be authenticated: " + accessRules.mustBeAuthenticatedToPath(path));

			if (accessRules.mustBeAuthenticatedToPath(path) && !userSession.isAuthenticated()) {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect(contextPath + "/login.xhtml");
				sessionAttributes.addAttribute(LAST_PATH_UNAUTHORIZED, path);
			}

		}
		chain.doFilter(request, response);

	}

}
