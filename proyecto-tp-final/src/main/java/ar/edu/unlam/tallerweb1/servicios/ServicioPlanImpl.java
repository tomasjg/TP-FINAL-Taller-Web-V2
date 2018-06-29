package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.PlanDao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Plan;

@Service("servicioPlan")
@Transactional
public class ServicioPlanImpl implements ServicioPlan{
	
	@Inject
	private PlanDao servicioPlanDao;

	@Override
	public Plan consultarPlan(Long id) {
		// TODO Auto-generated method stub
		return servicioPlanDao.consultarPlan(id);
	}
	
	

}
