package ar.edu.unq.sarmiento.epers;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ar.edu.unq.sarmiento.epers.hibernate.MaguitoHome;
import ar.edu.unq.sarmiento.epers.hibernate.SessionFactoryContainer;
import ar.edu.unq.sarmiento.epers.model.Item;
import ar.edu.unq.sarmiento.epers.model.Maguito;

public class GenerateData {

	public static void main(String[] args) {

		SessionFactoryContainer.buildSessionFactory(true);
		Session s = SessionFactoryContainer.getSessionFactory().getCurrentSession();

		Transaction transaction = s.beginTransaction();

		try {
			Maguito harry = new Maguito("Harry", 100);
			harry.addItem(new Item("varita", 1));
			harry.addItem(new Item("capa", 3));
			harry.setExperiencia(10);

			Maguito gandalf = new Maguito("Gandalf", 90);
			gandalf.addItem(new Item("baculo", 7));
			gandalf.addItem(new Item("sombrero", 2));
			gandalf.setExperiencia(200);

			MaguitoHome.getInstance().insert(harry);
			MaguitoHome.getInstance().insert(gandalf);

			transaction.commit();
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		} finally {
			s.close();
			SessionFactoryContainer.getSessionFactory().close();
		}

	}

}
