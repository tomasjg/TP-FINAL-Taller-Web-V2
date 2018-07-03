package ar.edu.unlam.tallerweb1.servicios;


import ar.edu.unlam.tallerweb1.modelo.RegistrarPesoDiarioDTO;

public interface ServicioRegistrarPesoDiario {

	RegistrarPesoDiarioDTO ConsultarRegistroFecha(int id, String fecha);
	void RegistrarPesoDiario(RegistrarPesoDiarioDTO registrarPesoDiarioDTO);
}
