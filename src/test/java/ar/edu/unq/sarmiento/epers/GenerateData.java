package ar.edu.unq.sarmiento.epers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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

			Maguito gandalf = new Maguito("Gandalf", 90);
			gandalf.addItem(new Item("baculo", 7));
			gandalf.addItem(new Item("sombrero", 2));

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

	protected static void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

		try {
			SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
			throw e;
		}
	}

}
