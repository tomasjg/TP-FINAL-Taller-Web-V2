package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlimentoDao;
import ar.edu.unlam.tallerweb1.modelo.*;

@Service("servicioAlimentos")
@Transactional
public class ServicioAlimentosImpl implements ServicioAlimentos{

	@Inject
	private AlimentoDao alimentoDao;
	
	@Override
	public List<Alimento> obtenerListadoDeAlimentos(){
		return alimentoDao.obtenerListadoDeAlimentos();
	}

	@Override
	public List<Alimento> obtenerListadoDeAlimentos(String tipo) {
		return alimentoDao.obtenerListadoDeAlimentos(tipo);
	}
}
