package ar.edu.unlam.tallerweb1.modelo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Formula {

	public Double calcularIMC(Double peso, Double altura) {
		Double resultado;
		Double metros = altura / 100;
		resultado = (peso / (Math.pow(metros,2)));
		return resultado;
	}
	
	public Double calcularPesoIdeal(Double altura, String sexo) {
		Double resultado;
		if(sexo.equals("Hombre"))
			resultado = ((altura - 100) * 0.90);
		else
			resultado = ((altura - 100)) * 0.85;
			
		return resultado;
	}
	
	public Double calcularTMB(Double peso, Double altura, int edad, String sexo, int ejercicio) {
		Double resultado;
		if(sexo.equals("Hombre")) {
			resultado = (10 * peso) + (6.25 * altura) - (5 * edad) + 5;
		}
		else {
			resultado = (10 * peso) + (6.25 * altura) - (5 * edad) - 161;
		}
		
		switch (ejercicio) {
        case 1:
        		resultado = resultado * 1.2;
        		break;
        case 2:
				resultado = resultado * 1.375;
				break;
        case 3:
				resultado = resultado * 1.55;
				break;
        case 4:
				resultado = resultado * 1.725;
				break;
        case 5:
				resultado = resultado * 1.9;
				break;
		}
		
		return resultado;
	}
	
	public List<CompararProgresoDTO> generarListaComparacion(int calorias, List<RegistrarPesoDiarioDTO> pesoRegistrado) {
		List<CompararProgresoDTO> resultado = new ArrayList<CompararProgresoDTO>();
		
		
		
		return resultado;
		
	}
	
	public List<ProgresoPesoIdeal> generarListaPesoIdeal(String fecha, int dias, int peso, Double caloriasPG){
		
		List<ProgresoPesoIdeal> resultado = new ArrayList<ProgresoPesoIdeal>();
		Float calPorDia;
		String f = fecha;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(f));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		calPorDia = (float) (caloriasPG / 7);
		Float p = (float) peso*1000;
		
		resultado.add(new ProgresoPesoIdeal(f,p));
		
		for(int i = 0;i<dias;i++) {
			
			c.add(Calendar.DATE, 1);
			f = format.format(c.getTime());;
			p = p - calPorDia;
			resultado.add(new ProgresoPesoIdeal(f,p));
		}
		
		/*for(ProgresoPesoIdeal res : resultado){
			System.out.println("Fecha: "+res.getFecha()+" Peso: "+res.getPeso());
		}*/
		
		return resultado;
	}
	
}
