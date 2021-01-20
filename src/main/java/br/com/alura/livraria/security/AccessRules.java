package br.com.alura.livraria.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AccessRules implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public final Set<String> mustBeAuthenticatedPaths;
	
	public AccessRules() {
		mustBeAuthenticatedPaths = new HashSet<String>();
		mustBeAuthenticatedPaths.add("/livro.xhtml");
		mustBeAuthenticatedPaths.add("/autor.xhtml");
		mustBeAuthenticatedPaths.add("/vendas.xhtml");
	}
	
	public Set<String> getMustBeAuthenticatedPaths(){
		return new HashSet<String>(mustBeAuthenticatedPaths);
	}
	
	public boolean mustBeAuthenticatedToURL(String requestURL) {
		for (String path : mustBeAuthenticatedPaths) {
			if (requestURL.endsWith(path)) {
				return true;
			}
		}
		return false;
	}

	public boolean mustBeAuthenticatedToPath(String path) {
		return mustBeAuthenticatedPaths.contains(path);
	}
}
