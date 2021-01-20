package br.com.alura.livraria.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Messages {
	
	public static void addMessage(String clientId, Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(severity, summary, detail));
	}
	
	public static void addError(String clientId, String summary, String detail) {
		addMessage(clientId, FacesMessage.SEVERITY_ERROR, summary, detail);
	}
	
	public static void addWarning(String clientId, String summary, String detail) {
		addMessage(clientId, FacesMessage.SEVERITY_WARN, summary, detail);
	}

	public static void addInfo(String clientId, String summary, String detail) {
		addMessage(clientId, FacesMessage.SEVERITY_INFO, summary, detail);
	}
}
