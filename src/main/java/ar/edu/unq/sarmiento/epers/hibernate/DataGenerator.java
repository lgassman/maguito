package ar.edu.unq.sarmiento.epers.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.sarmiento.epers.model.Home;
import ar.edu.unq.sarmiento.epers.model.Item;
import ar.edu.unq.sarmiento.epers.model.Maguito;

@Component
@Transactional
public class DataGenerator {

	@Autowired
	private Home<Maguito> maguitoHome;

	protected void generate() {
		Maguito harry = new Maguito("Harry", 100);
		harry.addItem(new Item("varita", 1));
		harry.addItem(new Item("capa", 3));
		harry.setExperiencia(10);

		Maguito gandalf = new Maguito("Gandalf", 90);
		gandalf.addItem(new Item("baculo", 7));
		gandalf.addItem(new Item("sombrero", 2));
		gandalf.setExperiencia(200);

		maguitoHome.insert(harry);
		maguitoHome.insert(gandalf);
	}
}
