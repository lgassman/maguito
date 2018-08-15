package ar.edu.unq.sarmiento.epers.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Item extends Persistible{

	private String nombre;
	private int peso;
	
	@ManyToOne
	private Maguito maguito;

	public Item() {
	}

	public Item(String nombre, int peso) {
		this.setNombre(nombre);
		this.setPeso(peso);
	}

	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Maguito getMaguito() {
		return maguito;
	}

	public void setMaguito(Maguito maguito) {
		this.maguito = maguito;
	}


}
