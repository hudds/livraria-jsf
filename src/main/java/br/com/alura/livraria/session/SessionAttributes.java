package br.com.alura.livraria.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("sessionAttributes")
@SessionScoped
public class SessionAttributes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final Map<String, Object> attributes = new HashMap<>();
	private final Map<String, Object> flashAttributes = new HashMap<>();
	
	
	public Object addAttribute(String name, Object value) {
		return this.attributes.put(name, value);
	}
	
	public Object addFlashAttribute(String name, Object value) {
		return this.flashAttributes.put(name, value);
	}
	
	public Object getAttribute(String name) {
		Object value = attributes.get(name);
		return value;
	}
	
	public Object getAnyAttribute(String name) {
		Object value = getAttribute(name);
		if(value == null && flashAttributes.containsKey(name)) {
			value = getFlashAttribute(name);
		}
		return value;
	}
	
	public Object getFlashAttribute(String name) {
		Object value = flashAttributes.get(name);
		flashAttributes.remove(name);
		return value;
	}
	
	public Object removeFlashAttribute(String name) {
		return this.flashAttributes.remove(name);
	}
	
	public Object removeAttribute(String name) {
		return this.attributes.remove(name);
	}
	
}
