package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private Double peso;
	private Double altura;
	private String sexo;
	private int edad;
	private int ejercicio;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	private Plan planAsociado;
		
	public Paciente() {
	}
	
	public Paciente(Double peso, Double altura) {
		this.peso = peso;
		this.altura = altura;
	}
	
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
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(int ejercicio) {
		this.ejercicio = ejercicio;
	}
	
	public Plan getPlanAsociado() {
		return planAsociado;
	}

	public void setPlanAsociado(Plan planAsociado) {
		this.planAsociado = planAsociado;
	}

	public Double calcularImc(){
		Formula formula= new Formula();
		return formula.calcularIMC(this.peso, this.altura);
	}

	
}
