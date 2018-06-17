package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PlanDao;
import ar.edu.unlam.tallerweb1.modelo.Plan;

@Service("servicioPacientes")
@Transactional
public class ServicioPacientesImpl implements ServicioPacientes {

	@Inject
	private PlanDao planDao;

	@Override
	public Plan consultarPlan (int id) {
		return planDao.consultarPlan(id);
	}

	@Override
	public void insertarPlanesIniciales () {
		 planDao.insertarPlanesIniciales();
	}
	
	@Override
	public List<Plan> obtenerPlanesFiltrados(String intensidad, boolean aptoCeliaco, boolean aptoHipertenso, boolean sinCarne, boolean sinLacteos) {
		return planDao.obtenerPlanesFiltrados(intensidad, aptoCeliaco, aptoHipertenso, sinCarne, sinLacteos);
	}

}
