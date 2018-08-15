package ar.edu.unq.sarmiento.epers;

import java.util.Random;

import ar.edu.unq.sarmiento.epers.model.Home;
import ar.edu.unq.sarmiento.epers.model.Item;
import ar.edu.unq.sarmiento.epers.model.Maguito;

public class MaguitoRandomHome implements Home<Maguito>{

	static MaguitoRandomHome instance = new MaguitoRandomHome();
	
	@Override
	public Maguito findByName(String name) {
		Maguito result = new Maguito();
		result.setNombre(name);
		result.setVida(new Random().nextInt(100));
		Item item = new Item();
		item.setNombre("primer item");
		item.setPeso(new Random().nextInt(100));
		result.addItem(item);
		item = new Item();
		item.setNombre("segundo item");
		item.setPeso(new Random().nextInt(100));
		result.addItem(item);
		return result;
	}

	public static MaguitoRandomHome getInstance() {
		return instance;
	}

	@Override
	public void insert(Maguito object) {
		
	}

	@Override
	public void update(Maguito object) {
		
	}

	@Override
	public void delete(Maguito object) {
		
	}

}
