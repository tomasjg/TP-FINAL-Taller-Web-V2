package ar.edu.unlam.tallerweb1.servicios;


import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.RegistrarPesoDiarioDTO;

public interface ServicioRegistrarPesoDiario {

	RegistrarPesoDiarioDTO ConsultarRegistroFecha(int id, String fecha);
	void RegistrarPesoDiario(RegistrarPesoDiarioDTO registrarPesoDiarioDTO);
	public List<RegistrarPesoDiarioDTO> ObtenerRegistros(Long id);
}
