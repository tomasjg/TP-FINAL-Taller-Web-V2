package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Formula;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.servicios.ServicioPacientes;
import ar.edu.unlam.tallerweb1.servicios.ServicioRegistrarPesoDiario;


@Controller

public class ControladorProgresoPaciente {
	
	@Inject
	private ServicioPacientes servicioPacientes;
	
	@Inject
	private ServicioPacientes servicioPlan;
	
	@RequestMapping(path = "/progresoPaciente", method = RequestMethod.POST)
	public ModelAndView verProgresoPaciente(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		Long longId = (Long) request.getSession().getAttribute("ID");
		int id = longId.intValue();
		Paciente paciente = servicioPacientes.obtenerPaciente(longId);
		Plan plan = servicioPlan.consultarPlan(paciente.getPlanAsociado_id());
		Formula formula = new Formula();
		
		Double imc = formula.calcularIMC(paciente.getPeso(), paciente.getAltura());
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
		
		return new ModelAndView("", model);
	}
}
