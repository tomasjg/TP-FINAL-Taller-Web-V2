package ar.edu.unlam.tallerweb1.modelo;

public class Plan {
private String nombre;
private int calorias;
//private list de comida;

public Plan(String nombre, int calorias) {
	this.nombre = nombre;
	this.calorias = calorias;
}

public Plan() {
	
}

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public int getCalorias() {
	return calorias;
}
public void setCalorias(int calorias) {
	this.calorias = calorias;
}

}
