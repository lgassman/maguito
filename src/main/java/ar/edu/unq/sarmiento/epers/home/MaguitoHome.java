package ar.edu.unq.sarmiento.epers.home;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.sarmiento.epers.model.Maguito;

@Repository
@Transactional
public class MaguitoHome extends AbstractHome<Maguito> {

	private static final long serialVersionUID = 4775910097257163038L;

	@Override
	public Maguito findByName(String name) {
		return this.getSession().createQuery("FROM Maguito WHERE nombre = :name", Maguito.class)
				.setParameter("name", name).getSingleResult();
	}

}
