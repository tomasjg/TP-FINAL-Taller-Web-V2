package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.PacienteDTO;
import ar.edu.unlam.tallerweb1.modelo.Plan;

@Repository("planDao")
public class PlanDaoImpl implements PlanDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public Plan consultarPlan(Long id) {

		final Session session = sessionFactory.getCurrentSession();
		return (Plan) session.createCriteria(Plan.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
	}
	
	@Override
	public List<Plan> obtenerPlanesFiltrados(String intensidad, boolean aptoCeliaco, boolean aptoHipertenso, boolean sinCarne, boolean sinLacteos) {

		final Session session = sessionFactory.getCurrentSession();
		List<Plan> planesFiltrados = new ArrayList<Plan>();
		//planesFiltrados 
		Criteria criteria = session.createCriteria(Plan.class)
				.add(Restrictions.eq("intensidad", intensidad));

		// Agrega restricciones opcionales
		agregarRestriccionSiEsTrue(criteria, "aptoCeliaco", aptoCeliaco);
		agregarRestriccionSiEsTrue(criteria, "aptoHipertenso", aptoHipertenso);
		agregarRestriccionSiEsTrue(criteria, "sinCarne", sinCarne);
		agregarRestriccionSiEsTrue(criteria, "sinLacteos", sinLacteos);
		
		planesFiltrados = criteria.list();
		return planesFiltrados;
	}
	
	@Override
	public Plan generarPlanSugerido(PacienteDTO pacienteDTO) {

		final Session session = sessionFactory.getCurrentSession();
		
		String dummyPlan="<b>Desayuno:</b><br>"+ 
				"Infusión con ½ taza de leche descremada + 3 tostadas de gluten con ricota descremada + 1 huevo revuelto.<br>" + 
				"<b>Media mañana:</b><br>" +
				"Yogur descremado con frutillas.<br>" +  
				"<b>Almuerzo:</b><br>" +
				"Ensalada de lentejas, tomate, lechuga, pepino, cebolla y ají + 2 brochetes de banana, naranja y kiwi.<br>" +
				"<b>Media tarde:</b><br>" +
				"Gaseosa light + 2 bay biscuits.<br>" +
				"<b>Merienda:</b><br>" +
				"Infusión con ½ taza de leche descremada + 2 tostadas de pan integral con queso untable descremado.<br>" +
				"<b>Cena:</b><br>" +
				"Bife de lomo grillado + ensalada de lechuga, zanahoria, apio y clara de huevo duro + postre de leche light.<br>";
		
		Plan planBase = new Plan();
		planBase.setNombre("Plan ");
		//seteamos las calorias del plan segun la intensidad
		switch(pacienteDTO.getIntensidad() ) {
		case "Normal":
			planBase.setCaloriasDiarias(1500);
			if(pacienteDTO.getIMC()<18.5){
				planBase.setCaloriasDiarias(3000);
			}
			planBase.setIntensidad("Normal");
			
			break;
		case "Intenso": 
			planBase.setCaloriasDiarias(1000);
			if(pacienteDTO.getIMC()<18.5){
				planBase.setCaloriasDiarias(3500);
			}
			
			planBase.setIntensidad("Intenso");
			break;
		}

		
		//nombre base para el plan segun exclusiones
		if(pacienteDTO.getIMC()<18.5){
			planBase.setNombre(planBase.getNombre()+"Alto en Calorias ");
		}
		
		if(pacienteDTO.isExcluirCarne()==false && pacienteDTO.isExcluirLacteos()==false ) {
			planBase.setNombre(planBase.getNombre()+"Bajo en grasas y Azucares");
			planBase.setSinCarne(false);
			planBase.setSinLacteos(false);
		}
		if(pacienteDTO.isExcluirCarne()==false && pacienteDTO.isExcluirLacteos()==true ) {
			planBase.setNombre(planBase.getNombre()+"Intolerante Lactosa");
			planBase.setSinCarne(false);
			planBase.setSinLacteos(true);
			
		}
		if(pacienteDTO.isExcluirCarne()==true && pacienteDTO.isExcluirLacteos()==false ) {
			planBase.setNombre(planBase.getNombre()+"Lacto-Vegetariano");
			planBase.setSinCarne(true);
			planBase.setSinLacteos(false);
		}
		if(pacienteDTO.isExcluirCarne()==true && pacienteDTO.isExcluirLacteos()==true ) {
			planBase.setNombre(planBase.getNombre()+"Vegetariano");
			planBase.setSinCarne(true);
			planBase.setSinLacteos(true);
		}
		//especificaciones segun enfermedades
		if(pacienteDTO.isAptoCeliaco()==false && pacienteDTO.isAptoHipertenso()==true ) {
			planBase.setNombre(planBase.getNombre()+"(bajo en sodio)");
		}
		if(pacienteDTO.isAptoCeliaco()==true && pacienteDTO.isAptoHipertenso()==false ) {
			planBase.setNombre(planBase.getNombre()+"(sin glutem)");
		}
		if(pacienteDTO.isAptoCeliaco()==true && pacienteDTO.isAptoHipertenso()==true ) {
			planBase.setNombre(planBase.getNombre()+"(bajo en sodio sin glutem)");
		}

		
		//seteamos el plan Dummy
		planBase.setListaComidasPorDia(dummyPlan);
		
		session.save(planBase);
		
		return planBase;
	}
	
	
	@Override
	public void insertarPlanesIniciales(){

		final Session session = sessionFactory.getCurrentSession();
		
		session.createSQLQuery("truncate table plan").executeUpdate();
		
		String dummyPlan="<b>Desayuno:</b><br>"+ 
				"Infusión con ½ taza de leche descremada + 3 tostadas de gluten con ricota descremada + 1 huevo revuelto.<br>" + 
				"<b>Media mañana:</b><br>" +
				"Yogur descremado con frutillas.<br>" +  
				"<b>Almuerzo:</b><br>" +
				"Ensalada de lentejas, tomate, lechuga, pepino, cebolla y ají + 2 brochetes de banana, naranja y kiwi.<br>" +
				"<b>Media tarde:</b><br>" +
				"Gaseosa light + 2 bay biscuits.<br>" +
				"<b>Merienda:</b><br>" +
				"Infusión con ½ taza de leche descremada + 2 tostadas de pan integral con queso untable descremado.<br>" +
				"<b>Cena:</b><br>" +
				"Bife de lomo grillado + ensalada de lechuga, zanahoria, apio y clara de huevo duro + postre de leche light.<br>";
		
		String desayuno="1 yogur descremado con cereal<br>1 fruta";
		String colacion1="1 vaso grande de jugo light<br>1 yogur descremado";
		String almuerzo="1 plato grande de ensalada<br>2 mitades de tomate redondo relleno con arroz integral, atún, aceituna y 2 cditas. de mayonesa light<br>1 helado de agua";
		String merienda="1 latita de gaseosa light<br>1 barrita de cereal light";
		String colacion2="1 vaso de licuado de durazno con leche descremada<br>1 figazza árabe de salvado con queso fresco descremado y tomate (tostada, tipo pizzeta)";
		String cena="1 plato de ensalada<br>1 plato de verduras cocidas a elección<br>1 milanesa de soja<br>1 postre light";

		
		Plan plan0000N = new Plan();
		plan0000N.setNombre("Plan Bajo en grasas y azucares");
		plan0000N.setSinCarne(false);
		plan0000N.setSinLacteos(false);
		plan0000N.setAptoHipertenso(false);
		plan0000N.setAptoCeliaco(false);
		plan0000N.setCaloriasDiarias(1500);
		plan0000N.setIntensidad("Normal");
		plan0000N.setListaComidasPorDia(dummyPlan);
		plan0000N.setDesayuno(desayuno);
		plan0000N.setColacion1(colacion1);
		plan0000N.setAlmuerzo(almuerzo);
		plan0000N.setMerienda(merienda);
		plan0000N.setColacion2(colacion2);
		plan0000N.setCena(cena);
		
		
		Plan plan1000N = new Plan();
		plan1000N.setNombre("Plan Lacto-Vegetariano");
		plan1000N.setSinCarne(true);
		plan1000N.setSinLacteos(false);
		plan1000N.setAptoHipertenso(false);
		plan1000N.setAptoCeliaco(false);
		plan1000N.setCaloriasDiarias(1500);
		plan1000N.setIntensidad("Normal");
		plan1000N.setListaComidasPorDia(dummyPlan);
		plan1000N.setDesayuno(desayuno);
		plan1000N.setColacion1(colacion1);
		plan1000N.setAlmuerzo(almuerzo);
		plan1000N.setMerienda(merienda);
		plan1000N.setColacion2(colacion2);
		plan1000N.setCena(cena);
		
		Plan plan1100N = new Plan();
		plan1100N.setNombre("Plan Vegetariano");
		plan1100N.setSinCarne(true);
		plan1100N.setSinLacteos(true);
		plan1100N.setAptoHipertenso(false);
		plan1100N.setAptoCeliaco(false);
		plan1100N.setCaloriasDiarias(1500);
		plan1100N.setIntensidad("Normal");
		plan1100N.setListaComidasPorDia(dummyPlan);
		plan1100N.setDesayuno(desayuno);
		plan1100N.setColacion1(colacion1);
		plan1100N.setAlmuerzo(almuerzo);
		plan1100N.setMerienda(merienda);
		plan1100N.setColacion2(colacion2);
		plan1100N.setCena(cena);
		
		Plan plan0100N = new Plan();
		plan0100N.setNombre("Plan Intolerante Lactosa");
		plan0100N.setSinCarne(false);
		plan0100N.setSinLacteos(true);
		plan0100N.setAptoHipertenso(false);
		plan0100N.setAptoCeliaco(false);
		plan0100N.setCaloriasDiarias(1500);
		plan0100N.setIntensidad("Normal");
		plan0100N.setListaComidasPorDia(dummyPlan);
		plan0100N.setDesayuno(desayuno);
		plan0100N.setColacion1(colacion1);
		plan0100N.setAlmuerzo(almuerzo);
		plan0100N.setMerienda(merienda);
		plan0100N.setColacion2(colacion2);
		plan0100N.setCena(cena);
		
		Plan plan0100SP = new Plan();
		plan0100SP.setNombre("Plan Alto en Carbohidratos");
		plan0100SP.setSinCarne(false);
		plan0100SP.setSinLacteos(false);
		plan0100SP.setAptoHipertenso(false);
		plan0100SP.setAptoCeliaco(false);
		plan0100SP.setCaloriasDiarias(3000);
		plan0100SP.setIntensidad("Normal");
		plan0100SP.setListaComidasPorDia(dummyPlan);
		plan0100SP.setDesayuno(desayuno);
		plan0100SP.setColacion1(colacion1);
		plan0100SP.setAlmuerzo(almuerzo);
		plan0100SP.setMerienda(merienda);
		plan0100SP.setColacion2(colacion2);
		plan0100SP.setCena(cena);
		

		session.save(plan0000N);
		session.save(plan0100N);
		session.save(plan1100N);
		session.save(plan0100N);
		session.save(plan0100SP);
		
	}
	
	// Función para agregar restricciones al criteria SOLO si son true (Ej: enfermedades. Si aptoHipertenso es True se debe agregar al filtro,
	// pero si aptoHipertenso es False no es necesario agregarlo, ya que debe traer ambos tipos de dietas.
	private void agregarRestriccionSiEsTrue(Criteria criteria, String restriccion, boolean valor) {
	    if (valor) {
	        criteria.add(Restrictions.eq(restriccion, valor));
	    }
	}
}
