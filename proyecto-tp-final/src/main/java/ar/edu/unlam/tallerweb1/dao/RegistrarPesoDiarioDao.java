package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.RegistrarPesoDiarioDTO;

public interface RegistrarPesoDiarioDao {
RegistrarPesoDiarioDTO ConsultarRegistroFecha(int id, String fecha);

void RegistrarPesoDiario(RegistrarPesoDiarioDTO registrarPesoDiarioDTO);
}
