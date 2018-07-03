package ar.edu.unlam.tallerweb1.dao;


import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
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
	
}