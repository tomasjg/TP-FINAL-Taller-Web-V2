package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class UsuarioDaoTest extends SpringTest {

	@Inject
	UsuarioDao usuarioDao;
	
	@Test @Transactional @Rollback(true)
	public void buscarUsuarioExistenteDeberiaEncontrarUnUsuario(){
		
		Usuario usuario = new Usuario();
		usuario.setEmail("ariel@live.com");
		usuario.setPassword("1234");
		
		getSession().save(usuario);
		
		Usuario encontrado=
		usuarioDao.consultarUsuario(usuario);
		
		assertThat(encontrado).isNotNull();
		
	}
}
