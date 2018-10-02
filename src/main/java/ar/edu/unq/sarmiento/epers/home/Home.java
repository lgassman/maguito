package ar.edu.unq.sarmiento.epers.home;

import java.io.Serializable;

import org.hibernate.Session;

public interface Home<T> extends Serializable {

	public Session getSession();
	public T findByName(String name);
	public T find(Integer id);
	public void saveOrUpdate(T object);
	public void delete(T object);

}
