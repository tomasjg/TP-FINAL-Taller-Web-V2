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
		planes.add(new Plan("Plan Intolerantes lactosa",1900));
		planes.add(new Plan("Plan Alergicos al huevo",1800));
		planes.add(new Plan("Plan Bajo en Grasas, Azucares",1700));
		planes.add(new Plan("Plan Bajo en sodio",1600));
		planes.add(new Plan("Plan Libre de Glutem",1500));
		planes.add(new Plan("Plan Rico en calcio",1400));
		planes.add(new Plan("Plan Ovo-Vegetariano",1300));
		planes.add(new Plan("Plan Lacto-Vegetariano",1200));
		planes.add(new Plan("Plan Ovo-Lacto-Vegetariano",1200));
		
		model.put("planes", planes);
		
		Plan plan = new Plan();
		model.put("plan", plan);
		
		/*String prueba = request.getParameter("prueba");
		model.put("prueba", prueba);*/
		
		return new ModelAndView("planes", model);
	}

	@RequestMapping(path = "/final", method = RequestMethod.POST)
	public ModelAndView crearGraficoCalorias(@ModelAttribute("plan") Plan plan, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		model.put("plan", plan);
		
		Double peso = Double.parseDouble(request.getParameter("peso"));
		Double tmb = Double.parseDouble(request.getParameter("tmb"));
		Double pesoIdeal = Double.parseDouble(request.getParameter("pesoIdeal"));
		Double pesoAPerder;
		Double caloriasPerdidasPorDia;
		int diasObjetivo;
		
		pesoAPerder = peso - pesoIdeal;
		caloriasPerdidasPorDia = tmb - plan.getCalorias();
		diasObjetivo = (int)(((pesoAPerder * 1000) * 7) / caloriasPerdidasPorDia);
		
		model.put("peso", peso);
		model.put("tmb", tmb);
		model.put("pesoIdeal", pesoIdeal);
		model.put("pesoAPerder", pesoAPerder);
		model.put("caloriasPerdidasPorDia", caloriasPerdidasPorDia);
		model.put("diasObjetivo", diasObjetivo);
		
		return new ModelAndView("final", model);
	}
}