package ar.edu.unq.sarmiento.epers.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.sarmiento.epers.model.Home;
import ar.edu.unq.sarmiento.epers.model.Maguito;

@Repository
@Transactional
public class MaguitoHome implements Home<Maguito> {

	private static final long serialVersionUID = 4775910097257163038L;

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Maguito findByName(String name) {
		return this.getSession().createQuery("FROM Maguito WHERE nombre = :name", Maguito.class)
				.setParameter("name", name).getSingleResult();

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

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

}
