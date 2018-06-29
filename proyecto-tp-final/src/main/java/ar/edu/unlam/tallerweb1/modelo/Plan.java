package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String intensidad;
	private boolean sinCarne;
	private boolean sinLacteos;
	private boolean aptoHipertenso;
	private boolean aptoCeliaco;
	private int caloriasDiarias;
	//private List<ComidasPorDia> listaComidasPorDia; //ManyToOne? OneToMany? Ver esto después
	@Column(columnDefinition = "LONGTEXT")
	private String listaComidasPorDia; // Por ahora. Esto despues se va a borrar y reemplazar por el listado de arriba
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIntensidad() {
		return intensidad;
	}
	public void setIntensidad(String intensidad) {
		this.intensidad = intensidad;
	}
	public boolean isSinCarne() {
		return sinCarne;
	}
	public void setSinCarne(boolean sinCarne) {
		this.sinCarne = sinCarne;
	}
	public boolean isSinLacteos() {
		return sinLacteos;
	}
	public void setSinLacteos(boolean sinLacteos) {
		this.sinLacteos = sinLacteos;
	}
	public boolean isAptoHipertenso() {
		return aptoHipertenso;
	}
	public void setAptoHipertenso(boolean aptoHipertenso) {
		this.aptoHipertenso = aptoHipertenso;
	}
	public boolean isAptoCeliaco() {
		return aptoCeliaco;
	}
	public void setAptoCeliaco(boolean aptoCeliaco) {
		this.aptoCeliaco = aptoCeliaco;
	}
	public int getCaloriasDiarias() {
		return caloriasDiarias;
	}
	public void setCaloriasDiarias(int caloriasDiarias) {
		this.caloriasDiarias = caloriasDiarias;
	}
//	public List<ComidasPorDia> getListaComidasPorDia() {
//		return listaComidasPorDia;
//	}
//	public void setListaComidaPorDia(List<ComidasPorDia> listaComidasPorDia) {
//		this.listaComidasPorDia = listaComidasPorDia;
//	}
	public String getListaComidasPorDia() {
		return listaComidasPorDia;
	}
	public void setListaComidasPorDia(String listaComidasPorDia) {
		this.listaComidasPorDia = listaComidasPorDia;
	}
	
	
}
