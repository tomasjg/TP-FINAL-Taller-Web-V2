package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Paciente;

@Repository("pacienteDao")
public class PacienteDaoImpl implements PacienteDao{
	
	@Inject
    private SessionFactory sessionFactory;
	final Session session = sessionFactory.getCurrentSession();
	
	@Override
	public Paciente getPacienteById (Long id) {
		
			Paciente resultado = 
			(Paciente) session.createCriteria(Paciente.class)
			.add(Restrictions.eq("id", id ))
			.uniqueResult();
				
			return resultado;
	}
	
	@Override
	public Long savePacienteInBd (Paciente paciente) {
		
		session.save(paciente);
		
		return paciente.getId();
	}
	
	@Override
	public Double getPesoPaciente(Long id){
		
		Paciente paciente=
		(Paciente) session.createCriteria(Paciente.class)
		.add(Restrictions.eq("id", id))
		.uniqueResult();
		
		return paciente.getPeso();	
	}
	
}
