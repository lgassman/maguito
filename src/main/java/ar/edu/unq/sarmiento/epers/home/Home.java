package ar.edu.unq.sarmiento.epers.home;

import java.io.Serializable;

import org.hibernate.Session;

import ar.edu.unq.sarmiento.epers.model.Persistible;

public interface Home<T extends Persistible> extends Serializable {

	public Session getSession();
	public T findByName(String name);
	public T find(Integer id);
	public void saveOrUpdate(T object);
	public void delete(T object);
	public void attach(T result);

}
