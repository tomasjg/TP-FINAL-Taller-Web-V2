package ar.edu.unlam.tallerweb1.persistencia;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dao.PacienteDao;
import ar.edu.unlam.tallerweb1.modelo.Paciente;

import static org.assertj.core.api.Assertions.assertThat;

public class PacienteDaoTest extends SpringTest{
	
	@Inject
	PacienteDao pacienteDao;
	
	@Test @Transactional @Rollback
	public void obtenetPacienteDesdeLaBd(){
		
		Paciente paciente = new Paciente(80d, 170d);
		paciente.setId(777L);
		getSession().save(paciente);
		
		Paciente guardado=
		pacienteDao.getPacienteById( paciente.getId());
		
		assertThat(guardado).isNotNull();
	}

}
