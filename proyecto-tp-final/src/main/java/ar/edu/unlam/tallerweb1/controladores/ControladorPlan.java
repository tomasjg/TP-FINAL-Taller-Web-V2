package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.*;

import ar.edu.unlam.tallerweb1.servicios.ServicioPacientes;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;

@Controller
public class ControladorPlan {

	@Inject
	private ServicioPlan servicioPlan;
	
	@Inject
	private ServicioPacientes servicioPacientes;
	
	@RequestMapping(path = "/verplan", method = RequestMethod.GET)
	public ModelAndView irAveplan() {
		ModelMap model = new ModelMap();
		Plan plan = new Plan();

		//obtenemos el id del Usuario directamente de la session
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		Long idPaciente = (Long) attr.getRequest().getSession().getAttribute("ID_PACIENTE");
		
		//con el id del paciente obtenemos el id del plan
		Long idPlan=servicioPacientes.getIdPlanByIdPaciente(idPaciente);

		//con el id del plan obtenemos el plan
		plan=servicioPlan.consultarPlan(idPlan);
		
		model.put("plan",plan);
		
		return new ModelAndView("verplan", model);
	}
	
}
