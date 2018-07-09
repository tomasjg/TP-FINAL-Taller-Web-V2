package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.PacienteDTO;
import ar.edu.unlam.tallerweb1.modelo.Plan;

// Interface que define los metodos del Servicio de Pacientes.
public interface ServicioPacientes {

	Plan consultarPlan(Long id);

	void insertarPlanesIniciales();
	
	List<Plan> obtenerPlanesFiltrados(String intensidad, boolean aptoCeliaco, boolean aptoHipertenso, boolean sinCarne, boolean sinLacteos);

	Plan generarPlanSugerido(PacienteDTO pacienteDTO);
	
	void registrarPaciente(Paciente paciente);

	Paciente obtenerPaciente(Long id);
	
	List<Paciente> obtenerListadoPacientes();

	Long getIdPlanByIdPaciente(Long idPaciente);

}
