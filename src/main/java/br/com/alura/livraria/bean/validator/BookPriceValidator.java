package br.com.alura.livraria.bean.validator;

import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("bookPriceValidator")
@RequestScoped
public class BookPriceValidator {

	public void inRange(FacesContext fc, UIComponent uc, Object value) {

		BigDecimal price = new BigDecimal(value.toString());
		if (BigDecimal.ONE.compareTo(price) > 0 || new BigDecimal("1000").compareTo(price) < 0) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"O valor do livro deve ser entre R$ 1,00 e R$ 1.000,00", null));
		}
	}

}
