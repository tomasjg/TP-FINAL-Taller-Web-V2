package ar.edu.unlam.tallerweb1.dao;


import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.*;

@Repository("AlimentoDao")
public class AlimentoDaoImpl implements AlimentoDao{

	@Inject
    private SessionFactory sessionFactory;
	
	@Override @SuppressWarnings("unchecked")
	public List<Alimento> obtenerListadoDeAlimentos(String tipo) {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Alimento> listaDeAlimentos = 
		(List<Alimento>) session.createCriteria(Alimento.class)
		.add(Restrictions.eq("tipo", tipo))
		.list();
		
		return listaDeAlimentos;
	}

	@Override @SuppressWarnings("unchecked")
	public List<Alimento> obtenerListadoDeAlimentos() {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Alimento> listaDeAlimentos = 
		(List<Alimento>) session.createCriteria(Alimento.class)
		.list();
		
		return listaDeAlimentos;
	}

}
