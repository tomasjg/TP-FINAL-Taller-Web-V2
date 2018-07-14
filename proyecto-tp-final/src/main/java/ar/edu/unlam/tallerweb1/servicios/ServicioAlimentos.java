package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.*;

public interface ServicioAlimentos {

	List<Alimento> obtenerListadoDeAlimentos();
	
	List<Alimento> obtenerListadoDeAlimentos(String tipo);
	
}
