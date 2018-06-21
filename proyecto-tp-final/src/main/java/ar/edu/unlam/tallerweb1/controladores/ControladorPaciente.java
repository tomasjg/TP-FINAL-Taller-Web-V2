package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Formula;
import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.PacienteDTO;
import ar.edu.unlam.tallerweb1.modelo.Plan;
import ar.edu.unlam.tallerweb1.servicios.ServicioPacientes;


@Controller
public class ControladorPaciente {
	
	@Inject
	private ServicioPacientes ServicioPacientes;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView irAPaciente() {
		ModelMap model = new ModelMap();
		PacienteDTO pacienteDTO = new PacienteDTO();
		Paciente paciente = new Paciente();
		pacienteDTO.setPaciente(paciente);
		model.put("pacienteDTO",pacienteDTO);
		
		return new ModelAndView("paciente", model);
	}
	
	@RequestMapping(path = "/exclusiones", method = RequestMethod.POST)
	public ModelAndView elegirExclusiones(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		model.put("pacienteDTO", pacienteDTO);
		
		return new ModelAndView("exclusiones", model);
	}

	@RequestMapping(path = "/planes", method = RequestMethod.POST)
	public ModelAndView elegirPlanes(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		String[] alimentosExcluidos = pacienteDTO.getAlimentosExcluidos();
		String[] enfermedadesPadecidas = pacienteDTO.getEnfermedadesPadecidas();
		
		// Setear Exclusiones
		for (String item : alimentosExcluidos) {
		    if (item.equals("Carne")) {
		    	pacienteDTO.setExcluirCarne(true);
		    }
		    if (item.equals("Lacteos")) {
		    	pacienteDTO.setExcluirLacteos(true);
		    }
		}
		
		for (String item : enfermedadesPadecidas) {
		    if (item.equals("Hipertension")) {
		    	pacienteDTO.setAptoHipertenso(true);
		    }
		    if (item.equals("Celiaquia")) {
		    	pacienteDTO.setAptoCeliaco(true);
		    }
		}
		
		// TO DO: Sacar estas líneas para dummies
		// Llama al servicio que inserta los planes iniciales hasta que desarrollemos el ABM de planes
		ServicioPacientes.insertarPlanesIniciales();
		
		List<Plan> planesSugeridos = ServicioPacientes.obtenerPlanesFiltrados(pacienteDTO.getIntensidad(), pacienteDTO.isAptoCeliaco(), 
				pacienteDTO.isAptoHipertenso(), pacienteDTO.isExcluirCarne(), pacienteDTO.isExcluirLacteos());

		model.put("planesSugeridos", planesSugeridos);
		model.put("pacienteDTO", pacienteDTO);
		
		//generar el plan especifico
		Plan planSugerido=new Plan();
		planSugerido=ServicioPacientes.generarPlanSugerido(pacienteDTO);
		model.put("planSugerido", planSugerido);
		
		return new ModelAndView("planes", model);
	}
	
	@RequestMapping(path = "/final", method = RequestMethod.POST)
	public ModelAndView crearGraficoCalorias2(@ModelAttribute("pacienteDTO") PacienteDTO pacienteDTO, HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		// Aca completa con los datos del plan elegido, haciendo un llamado a la BD mediante el ID
		Plan planElegido = ServicioPacientes.consultarPlan(pacienteDTO.getPlan().getId());
		pacienteDTO.setPlan(planElegido);
		
		Paciente paciente = pacienteDTO.getPaciente();
		
		Double caloriasPGPorDia;
		Double pesoAPerderOGanar;
		Double peso = paciente.getPeso();
		Double altura = paciente.getAltura();
		String sexo = paciente.getSexo();
		int edad = paciente.getEdad();
		int ejercicio = paciente.getEjercicio();
		
		Formula formula = new Formula();
		Double imc = formula.calcularIMC(peso, altura);
		Double pesoIdeal = formula.calcularPesoIdeal(altura, sexo);
		Double tmb = formula.calcularTMB(peso, altura, edad, sexo, ejercicio);
		
		if(peso > pesoIdeal){
		pesoAPerderOGanar = peso - pesoIdeal;}
		else{
		pesoAPerderOGanar = pesoIdeal - peso;}
		
		if(tmb > pacienteDTO.getPlan().getCaloriasDiarias()) {
		caloriasPGPorDia = tmb - pacienteDTO.getPlan().getCaloriasDiarias();}
		else {
		caloriasPGPorDia = pacienteDTO.getPlan().getCaloriasDiarias() - tmb;
		}
		
		int diasObjetivo = (int)(((pesoAPerderOGanar * 1000) * 7) / caloriasPGPorDia);
		
		model.put("peso", peso);
		model.put("tmb", tmb);
		model.put("imc", imc);
		model.put("pesoIdeal", pesoIdeal);
		model.put("pesoAPerderOGanar", pesoAPerderOGanar);
		model.put("caloriasPGPorDia", caloriasPGPorDia);
		model.put("diasObjetivo", diasObjetivo);
		
		model.put("pacienteDTO",pacienteDTO);
		
		return new ModelAndView("final", model);
	}
}