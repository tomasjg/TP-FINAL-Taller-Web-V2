package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.CompararProgresoDTO;
import ar.edu.unlam.tallerweb1.modelo.Formula;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.modelo.ProgresoPesoIdeal;
import ar.edu.unlam.tallerweb1.servicios.ServicioPacientes;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistrarPesoDiario;


@Controller

public class ControladorProgresoPaciente {
	
	@Inject
	private ServicioPacientes servicioPacientes;
	
	@Inject
	private ServicioPacientes servicioPlan;
	
	@Inject
	private ServicioRegistrarPesoDiario servicioRegistrarPeso;
	
	@RequestMapping(path = "/progresoPaciente", method = RequestMethod.GET)
	public ModelAndView verProgresoPaciente(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		Long longId = (Long) request.getSession().getAttribute("ID");
		//int id = longId.intValue();
				
		Paciente paciente = servicioPacientes.obtenerPaciente(longId);
		
		if(paciente == null) {
			
			model.put("error", "ERROR: El Usuario no tiene Registrado ningun Plan. Por favor Registre un plan");
			return new ModelAndView("home", model);
		}
		
		Plan plan = servicioPlan.consultarPlan(paciente.getPlanAsociado_id());
		Formula formula = new Formula();
		
		Double pesoIdeal = formula.calcularPesoIdeal(paciente.getAltura(), paciente.getSexo());
		Double tmb = formula.calcularTMB(paciente.getPeso(), paciente.getAltura(), paciente.getEdad(), paciente.getSexo(), paciente.getEjercicio());
		
		double pesoAPerderOGanar;
		if(paciente.getPeso() > pesoIdeal){
		pesoAPerderOGanar = paciente.getPeso() - pesoIdeal;}
		else{
		pesoAPerderOGanar = pesoIdeal - paciente.getPeso();}
		
		double caloriasPGPorDia;
		if(tmb > plan.getCaloriasDiarias()) {
		caloriasPGPorDia = tmb - plan.getCaloriasDiarias();}
		else {
		caloriasPGPorDia = plan.getCaloriasDiarias() - tmb;
		}
		
		int diasObjetivo = (int)(((pesoAPerderOGanar * 1000) * 7) / caloriasPGPorDia);
		
		List<ProgresoPesoIdeal> listaPesoIdeal = formula.generarListaPesoIdeal(paciente.getFecha_inicio(), diasObjetivo, paciente.getPeso(), caloriasPGPorDia);

		List<CompararProgresoDTO> listaComparacion = formula.generarListaComparacion(servicioRegistrarPeso.ObtenerRegistros(longId), listaPesoIdeal);
		
		model.put("Lista", listaComparacion);
		
		return new ModelAndView("progresoPaciente", model);
	}
}
