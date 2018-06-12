package ar.edu.unlam.tallerweb1.modelo;

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
}
