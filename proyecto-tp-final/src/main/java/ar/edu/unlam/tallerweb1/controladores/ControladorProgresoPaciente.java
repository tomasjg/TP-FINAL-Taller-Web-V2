package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class ControladorProgresoPaciente {
	
	
	@RequestMapping(path = "/progresoPaciente", method = RequestMethod.POST)
	public ModelAndView verProgresoPaciente(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		
		Long longId = (Long) request.getSession().getAttribute("ID");
		int id = longId.intValue();
		
		
		return new ModelAndView("", model);
	}
}
