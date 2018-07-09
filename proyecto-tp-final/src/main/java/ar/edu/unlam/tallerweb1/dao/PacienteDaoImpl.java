package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

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
	
	@Override
	public Paciente getPacienteById (Long id) {
		Session session = sessionFactory.getCurrentSession();
		Paciente resultado = 
		(Paciente) session.createCriteria(Paciente.class)
		.add(Restrictions.eq("id", id ))
		.uniqueResult();
			
		return resultado;
	}
	
	@Override
	public Long savePacienteInBd (Paciente paciente) {
		Session session = sessionFactory.getCurrentSession();
		session.save(paciente);
		
		return paciente.getId();
	}
	
	@Override
	public Double getPesoPaciente(Long id){
		Session session = sessionFactory.getCurrentSession();
		Paciente paciente=
		(Paciente) session.createCriteria(Paciente.class)
		.add(Restrictions.eq("id", id))
		.uniqueResult();
		
		return paciente.getPeso();	
	}
	
	@Override
	public Paciente obtenerPaciente(Long id){

		final Session session = sessionFactory.getCurrentSession();
		Paciente resultado = (Paciente) session.createCriteria(Paciente.class)
											.add(Restrictions.eq("idUsuario", id))
											.uniqueResult();

		return resultado;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> obtenerListadoPacientes(){

		final Session session = sessionFactory.getCurrentSession();
		List<Paciente> resultado = (List<Paciente>) session.createCriteria(Paciente.class)
											.list();
		return resultado;
		
	}

	@Override
	public Long getIdPlanByIdPaciente(Long id) {
		
		final Session session = sessionFactory.getCurrentSession();
		Paciente paciente = (Paciente) session.createCriteria(Paciente.class)
							.add(Restrictions.eq("idUsuario", id))
							.uniqueResult();
		
		return paciente.getPlanAsociado_id();
	}
	
}
