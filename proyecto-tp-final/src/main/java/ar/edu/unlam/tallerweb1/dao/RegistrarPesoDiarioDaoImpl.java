package ar.edu.unlam.tallerweb1.dao;


import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Paciente;
import ar.edu.unlam.tallerweb1.modelo.RegistrarPesoDiarioDTO;

@Repository("registrarPesoDiarioDao")
public class RegistrarPesoDiarioDaoImpl implements RegistrarPesoDiarioDao{
	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public RegistrarPesoDiarioDTO ConsultarRegistroFecha(int id, String fecha) {
		Long l = new Long(id);

		final Session session = sessionFactory.getCurrentSession();
		RegistrarPesoDiarioDTO resultado = (RegistrarPesoDiarioDTO) session.createCriteria(RegistrarPesoDiarioDTO.class)
											.add(Restrictions.eq("idPaciente", l))
											.add(Restrictions.eq("fecha", fecha))
											.uniqueResult();

		return resultado;
	}
	
	@Override
	public void RegistrarPesoDiario(RegistrarPesoDiarioDTO registrarPesoDiarioDTO) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(registrarPesoDiarioDTO);
		
	}
	
	@Override
	public List<RegistrarPesoDiarioDTO> ObtenerRegistros(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		List<RegistrarPesoDiarioDTO> resultado = 	session.createCriteria(RegistrarPesoDiarioDTO.class)
													.add(Restrictions.eq("idPaciente", id))
													.list();
		
		return resultado;
	}
	
	@Override
	public List<Paciente> ObtenerPacientes(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		List<Paciente> resultado = 	session.createCriteria(Paciente.class)
													.add(Restrictions.eq("medicoAsociado_id", id))
													.list();
		
		return resultado;
	}
}
