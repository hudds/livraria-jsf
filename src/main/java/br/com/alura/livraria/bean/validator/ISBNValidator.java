package br.com.alura.livraria.bean.validator;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("isbnValidator")
@RequestScoped
public class ISBNValidator {

	public void startsWith1(FacesContext fc, UIComponent uc, Object value) throws ValidatorException {
		String strValue = value.toString();
		
		if(!strValue.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "O valor ISBN deve começar com 1", null));
		}
		
	}
	
}
