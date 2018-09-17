package ar.edu.unq.sarmiento.epers.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Maguito extends Persistible {

	private static final long serialVersionUID = -786414214144659508L;
	private String nombre = "";
	private int vida = 0;
	private int experiencia;
	
	@OneToMany(mappedBy = "maguito",  cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Item> items = new ArrayList<Item>();

	public Maguito() {
	}

	public Maguito(String nombre, int vida) {
		this.setNombre(nombre);
		this.setVida(vida);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public void addVida() {
		vida++;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		this.getItems().add(item);
		if(item.getMaguito() != null) {
			item.getMaguito().removeItem(item);
		}
		item.setMaguito(this);
	}
	
	public void removeItem(Item item) {
		this.getItems().remove(item);
		item.setMaguito(null);
	}
	
	public int peso() {
		return this.getItems().stream().map((item) -> item.getPeso()).reduce( (a,b) -> a + b).orElse(0) ; 
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
}
