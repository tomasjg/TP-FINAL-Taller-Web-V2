package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.PacienteDTO;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorLogin {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface ServicioLogin, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	@Inject
	private ServicioLogin servicioLogin;

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("login", modelo);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			
			if(usuarioBuscado.getRol().equals("paciente")){
				request.getSession().setAttribute("idUsuario", usuarioBuscado.getId() );
				request.getSession().setAttribute("APELLIDO_PACIENTE", usuario.getApellido() );
				request.getSession().setAttribute("NOMBRE_PACIENTE", usuario.getNombre() );
			}
			request.getSession().setAttribute("EMAIL", usuarioBuscado.getEmail());
			request.getSession().setAttribute("ID", usuarioBuscado.getId());
			request.getSession().setAttribute("APELLIDO", usuarioBuscado.getApellido());
			return new ModelAndView("redirect:/home");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home");
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		//esta es una cuenta dummy para facilitar las pruebas
		Usuario usuario = new Usuario();
		usuario.setEmail("root@root.com");
		usuario.setApellido("Dr X");
		usuario.setPassword("");
		usuario.setRol("medico");
		servicioLogin.crearUsuario(usuario);
		
		return new ModelAndView("redirect:/login");
	}
	
	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/registrarUsuario si la misma es invocada por metodo http GET
	@RequestMapping(path ="/registrarusuario", method = RequestMethod.GET)
	public ModelAndView registarUsuario() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("registrarusuario", modelo);
	}
	
	// Este metodo escucha la URL crear-usuario siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/crear-usuario", method = RequestMethod.POST)
	public ModelAndView crearUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		if (usuario != null) {
			// invoca el m�todo crearUsuario del servicio
			// SE COMENTA PORQUE TODAVIA NO ESTA CREADO EL SERVICIO
			if(servicioLogin.crearUsuario(usuario)){
				model.put("usuario", usuario);
				PacienteDTO pacienteDTO = new PacienteDTO();
				pacienteDTO.setUsuario(usuario);
				request.getSession().setAttribute("idUsuario", usuario.getId());
				request.getSession().setAttribute("APELLIDO_PACIENTE", usuario.getApellido() );
				request.getSession().setAttribute("NOMBRE_PACIENTE", usuario.getNombre() );
				//pacienteDTO.setEdad(usuario.getEdad());
				model.put("pacienteDTO", pacienteDTO);
				return new ModelAndView("paciente", model);
			}
			else {
				model.put("error", "El E-mail Ingresado ya esta Registrado. Por Favor ingrese otro E-mail");
				return new ModelAndView("registrarusuario", model);
			}
			
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("registrarusuario", model);
	}
	
}
