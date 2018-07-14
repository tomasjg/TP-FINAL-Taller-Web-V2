package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alimento;

public interface AlimentoDao {

	List<Alimento> obtenerListadoDeAlimentos();

	List<Alimento> obtenerListadoDeAlimentos(String tipo);

}
