package ar.edu.unq.sarmiento.epers.home;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.sarmiento.epers.model.Persistible;

@Repository
@Transactional
public abstract class AbstractHome<T extends Persistible> implements Home<T> {

	private static final long serialVersionUID = -6234259370522061025L;

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Integer id) {
		Class<T> genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractHome.class);
		return getSession().get(genericType, id);
	}

	@Override
	public void saveOrUpdate(T object) {
		this.getSession().saveOrUpdate(object);
	}

	@Override
	public void delete(T object) {
		this.getSession().delete(object);
	}
	@Override
	public void attach(T result) {
		if(this.isDetached(result)) {
			this.getSession().lock(result, LockMode.NONE);
		}		
	}

	private boolean isDetached(T result) {
		return result != null && result.getId() != null;
	}

}
