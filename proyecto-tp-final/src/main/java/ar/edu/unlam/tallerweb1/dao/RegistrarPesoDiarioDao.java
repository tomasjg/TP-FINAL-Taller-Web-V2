package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.RegistrarPesoDiarioDTO;

public interface RegistrarPesoDiarioDao {
RegistrarPesoDiarioDTO ConsultarRegistroFecha(int id, String fecha);

void RegistrarPesoDiario(RegistrarPesoDiarioDTO registrarPesoDiarioDTO);
public List<RegistrarPesoDiarioDTO> ObtenerRegistros(Long id);
public List<Paciente> ObtenerPacientes(Long id);
}
