package ar.edu.unq.sarmiento.epers.model;

import java.io.Serializable;

import javax.persistence.NoResultException;

public class SearchModel<T> implements Serializable {

	private static final long serialVersionUID = 2280350615761032908L;
	private String search = "";
	private T result = null;
	private Home<T> home;
	private String message = null;
	
	public SearchModel(Home<T> home) {
		this.home = home;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	
	public void find() {
		try {
			this.setMessage(null);
			this.result = home.findByName(search);
		}
		catch(NoResultException e) {
			//TODO: Si no hay no es un error. Hay que diseñar mejor como se informa al usuario 
			setMessage("No existe un objeto para " + search); 
			this.result = null;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}