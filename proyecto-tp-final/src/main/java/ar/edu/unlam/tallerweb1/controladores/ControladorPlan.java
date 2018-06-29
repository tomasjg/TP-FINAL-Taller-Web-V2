package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.*;

import ar.edu.unlam.tallerweb1.servicios.ServicioPacientes;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlan;

@Controller
public class ControladorPlan {

	@Inject
	private ServicioPlan servicioPlan;
	
	@RequestMapping(path = "/verplan", method = RequestMethod.GET)
	public ModelAndView irAveplan() {
		ModelMap model = new ModelMap();
		Plan plan = new Plan();

		plan=servicioPlan.consultarPlan(1L);
		
		model.put("plan",plan);
		
		return new ModelAndView("verplan", model);
	}
	
}
