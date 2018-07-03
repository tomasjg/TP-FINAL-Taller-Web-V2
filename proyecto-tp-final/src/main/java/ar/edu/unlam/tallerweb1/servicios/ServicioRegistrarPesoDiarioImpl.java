package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.RegistrarPesoDiarioDao;
import ar.edu.unlam.tallerweb1.modelo.RegistrarPesoDiarioDTO;



@Service("servicioRegistrarPesoDiario")
@Transactional
public class ServicioRegistrarPesoDiarioImpl implements ServicioRegistrarPesoDiario{
	@Inject
	private RegistrarPesoDiarioDao registrarPesoDiarioDao;
	@Override
	public RegistrarPesoDiarioDTO ConsultarRegistroFecha(int id, String fecha){
		return registrarPesoDiarioDao.ConsultarRegistroFecha(id, fecha);
	}
	@Override
	public void RegistrarPesoDiario(RegistrarPesoDiarioDTO registrarPesoDiarioDTO) {
		registrarPesoDiarioDao.RegistrarPesoDiario(registrarPesoDiarioDTO);
	}
}
