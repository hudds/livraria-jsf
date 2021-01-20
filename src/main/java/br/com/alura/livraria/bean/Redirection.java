package br.com.alura.livraria.bean;

public class Redirection {
	
	private final String path;

	public Redirection(String path) {
		if(path == null || path.isBlank()) {
			this.path = "";
		} else {
			this.path = path+"?faces-redirect=true";
		}
	}
	
	@Override
	public String toString() {
		return path;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Redirection other = (Redirection) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
	
	
	
}
