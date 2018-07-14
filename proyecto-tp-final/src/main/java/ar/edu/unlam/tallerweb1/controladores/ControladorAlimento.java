package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimentos;

@Controller
public class ControladorAlimento {
	@Inject
	private ServicioAlimentos servicioAlimentos;

	@RequestMapping(path = "/registrarConsumoAlimento", method = RequestMethod.GET)
	public ModelAndView irAregistrarConsumoAlimento() {
		
		ModelMap model = new ModelMap();
		
		model.put("alimento", new Alimento());
		
		List<Alimento> listadoAlimentos = servicioAlimentos.obtenerListadoDeAlimentos("comida");
		model.put("listadoAlimentos",listadoAlimentos);
		
		List<Alimento> listadoBebidas = servicioAlimentos.obtenerListadoDeAlimentos("bebida");
		model.put("listadoBebidas",listadoBebidas);
		
		return new ModelAndView("registrarConsumoAlimento", model);
	}
	
	@RequestMapping(path = "/ConsumoAlimento", method = RequestMethod.POST)
	public ModelAndView ConsumoAlimento(@ModelAttribute("alimento") Usuario alimento, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		model.put("alimento",alimento);
		
		return new ModelAndView("registrarConsumoAlimento", model);
	}
	
}
