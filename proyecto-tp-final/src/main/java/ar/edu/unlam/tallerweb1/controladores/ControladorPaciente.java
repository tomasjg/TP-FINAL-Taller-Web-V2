package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Formula;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.Plan;


@Controller
public class ControladorPaciente {
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView irAPaciente() {
		ModelMap model = new ModelMap();
		Paciente paciente = new Paciente();
		model.put("paciente",paciente);
		
		return new ModelAndView("paciente", model);
	}
		
	@RequestMapping(path = "/planes", method = RequestMethod.POST)
	public ModelAndView crearPlanes(@ModelAttribute("paciente") Paciente paciente, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		Double imc;
		Double pesoIdeal;
		Double tmb;
		
		Formula formula = new Formula();
		
		imc = formula.calcularIMC(paciente.getPeso(), paciente.getAltura());
		pesoIdeal = formula.calcularPesoIdeal(paciente.getAltura(), paciente.getSexo());
		tmb = formula.calcularTMB(paciente.getPeso(), paciente.getAltura(), paciente.getEdad(), paciente.getSexo(), paciente.getEjercicio());
		
		model.put("imc",imc);
		model.put("pesoIdeal",pesoIdeal);
		model.put("tmb",tmb);
		model.put("paciente", paciente);
		
		List<Plan> planes = new ArrayList<Plan>();
		planes.add(new Plan("Plan Vegetariano",2000));
		planes.add(new Plan("Plan Vegetariano",2000));
		planes.add(new Plan("Plan Vegetariano",2000));
		planes.add(new Plan("Plan Vegetariano",2000));
		planes.add(new Plan("Plan Vegetariano",2000));
		planes.add(new Plan("Plan Vegetariano",2000));
		planes.add(new Plan("Plan Vegetariano",2000));
		
		model.put("planes", planes);
		
		Plan plan = new Plan();
		model.put("plan", plan);
		
		return new ModelAndView("planes", model);
	}

}