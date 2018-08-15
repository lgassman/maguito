package ar.edu.unq.sarmiento.epers.hibernate;

import javax.persistence.NoResultException;

import org.hibernate.Session;

import ar.edu.unq.sarmiento.epers.model.Home;
import ar.edu.unq.sarmiento.epers.model.Maguito;

public class MaguitoHome implements Home<Maguito> {

	private static final long serialVersionUID = 4775910097257163038L;

	static private MaguitoHome instance = new MaguitoHome();
	
	public static MaguitoHome getInstance() {
		return instance;
	}
	
	private Session getSession() {
		return SessionFactoryContainer.getSessionFactory().getCurrentSession();
	}

	@Override
	public Maguito findByName(String name) {
		return this.getSession()
				.createQuery("FROM Maguito WHERE nombre = :name", Maguito.class)
				.setParameter("name", name)
				.getSingleResult();
		
	}

	@Override
	public void insert(Maguito object) {
		this.getSession().save(object);
	}

	@Override
	public void update(Maguito object) {
		this.getSession().update(object);

	}

	@Override
	public void delete(Maguito object) {
		this.getSession().delete(object);
	}

}
