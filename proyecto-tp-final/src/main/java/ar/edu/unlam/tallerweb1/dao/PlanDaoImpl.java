package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Plan;

@Repository("planDao")
public class PlanDaoImpl implements PlanDao {

	@Inject
    private SessionFactory sessionFactory;

	@Override
	public Plan consultarPlan(int id) {

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
	public void insertarPlanesIniciales(){

		final Session session = sessionFactory.getCurrentSession();
		
		session.createSQLQuery("truncate table plan").executeUpdate();
		
		Plan planA = new Plan();
		planA.setNombre("Plan Vegetariano");
		planA.setAptoCeliaco(true);
		planA.setAptoHipertenso(true);
		planA.setSinCarne(true);
		planA.setSinLacteos(false);
		planA.setCaloriasDiarias(1200);
		planA.setIntensidad("Normal");
		planA.setListaComidasPorDia("Desayuno\r\n" + 
				"Infusión con ½ taza de leche descremada + 3 tostadas de gluten con ricota descremada + 1 huevo revuelto.\r\n" + 
				"Media mañana\r\n" + 
				"\r\n" + 
				"Yogur descremado con frutillas.\r\n" + 
				"\r\n" + 
				"Almuerzo\r\n" + 
				"\r\n" + 
				"Ensalada de lentejas, tomate, lechuga, pepino, cebolla y ají + 2 brochetes de banana, naranja y kiwi.\r\n" + 
				"\r\n" + 
				"Media tarde\r\n" + 
				"\r\n" + 
				"Gaseosa light + 2 bay biscuits.\r\n" + 
				"\r\n" + 
				"Merienda\r\n" + 
				"\r\n" + 
				"Infusión con ½ taza de leche descremada + 2 tostadas de pan integral con queso untable descremado.\r\n" + 
				"\r\n" + 
				"Cena\r\n" + 
				"\r\n" + 
				"Bife de lomo grillado + ensalada de lechuga, zanahoria, apio y clara de huevo duro + postre de leche light.");
		
		Plan planB = new Plan();
		planB.setNombre("Plan Intolerante Lactosa");
		planB.setAptoCeliaco(true);
		planB.setAptoHipertenso(false);
		planB.setSinCarne(false);
		planB.setSinLacteos(true);
		planB.setCaloriasDiarias(1500);
		planB.setIntensidad("Normal");
		planB.setListaComidasPorDia("Desayuno\r\n" + 
				" \r\n" + 
				"Infusión con ½ taza de leche descremada + 2 rodajas de pan integral con dos fetas de queso de máquina.\r\n" + 
				"\r\n" + 
				"Media mañana\r\n" + 
				"\r\n" + 
				"1 café cortado + 1 roll de masa light con queso blanco descremado y kiwi en trozos.\r\n" + 
				"\r\n" + 
				"Almuerzo\r\n" + 
				"\r\n" + 
				"Milanesa de berenjena a la napolitana con queso fresco light + ensalada primavera (arvejas, zanahoria y choclo) + gelatina light con trozos de frutas.\r\n" + 
				"\r\n" + 
				"Media tarde\r\n" + 
				"\r\n" + 
				"Infusión + 2 galletitas dulces light.\r\n" + 
				"\r\n" + 
				"Merienda\r\n" + 
				"\r\n" + 
				"Infusión con ½ taza de leche descremada + 2 tostadas de pan integral con manteca light.\r\n" + 
				"\r\n" + 
				"Cena\r\n" + 
				"\r\n" + 
				"Filete de merluza a la marinera light (al horno y con harina integral) + ensalada de hinojo, berro y tomates cherry + flan light con 2 nueces picadas.");
		
		Plan planC = new Plan();
		planC.setNombre("Plan Sin Gluten");
		planC.setAptoCeliaco(true);
		planC.setAptoHipertenso(true);
		planC.setSinCarne(false);
		planC.setSinLacteos(false);
		planC.setCaloriasDiarias(1800);
		planC.setIntensidad("Normal");
		planC.setListaComidasPorDia("Desayuno\r\n" + 
				"\r\n" + 
				"Infusión con ½ taza de leche descremada + 3 galletitas integrales con queso untable descremado.\r\n" + 
				"\r\n" + 
				"Media mañana\r\n" + 
				"\r\n" + 
				"1 leche fermentada + yogur descremado con cereales sin azúcar.\r\n" + 
				"\r\n" + 
				"Almuerzo\r\n" + 
				"\r\n" + 
				"Ensalada de arroz integral, lechuga, cebolla morada, tomate, repollo blanco y 1 huevo duro + 1 manzana asada a la canela.\r\n" + 
				"\r\n" + 
				"Media tarde\r\n" + 
				"\r\n" + 
				"1 vaso de jugo light + 1 casete de queso magro.\r\n" + 
				"\r\n" + 
				"Merienda\r\n" + 
				"\r\n" + 
				"Infusión con ½ taza de leche descremada + 3 galletitas de agua con mermelada light.\r\n" + 
				"\r\n" + 
				"Cena\r\n" + 
				"\r\n" + 
				"Suprema al limón + ensalada de espinaca, radicheta y champiñones + 1 taza de frutillas.");
		
		Plan planD = new Plan();
		planD.setNombre("Plan Vegetariano2");
		planD.setAptoCeliaco(true);
		planD.setAptoHipertenso(true);
		planD.setSinCarne(true);
		planD.setSinLacteos(false);
		planD.setCaloriasDiarias(1200);
		planD.setIntensidad("Normal");
		planD.setListaComidasPorDia("Desayuno\r\n" + 
				"Infusión con ½ taza de leche descremada + 3 tostadas de gluten con ricota descremada + 1 huevo revuelto.\r\n" + 
				"Media mañana\r\n" + 
				"\r\n" + 
				"Yogur descremado con frutillas.\r\n" + 
				"\r\n" + 
				"Almuerzo\r\n" + 
				"\r\n" + 
				"Ensalada de lentejas, tomate, lechuga, pepino, cebolla y ají + 2 brochetes de banana, naranja y kiwi.\r\n" + 
				"\r\n" + 
				"Media tarde\r\n" + 
				"\r\n" + 
				"Gaseosa light + 2 bay biscuits.\r\n" + 
				"\r\n" + 
				"Merienda\r\n" + 
				"\r\n" + 
				"Infusión con ½ taza de leche descremada + 2 tostadas de pan integral con queso untable descremado.\r\n" + 
				"\r\n" + 
				"Cena\r\n" + 
				"\r\n" + 
				"Bife de lomo grillado + ensalada de lechuga, zanahoria, apio y clara de huevo duro + postre de leche light.");
		
		session.save(planA);
		session.save(planB);
		session.save(planC);
		session.save(planD);
		
	}
	
	// Función para agregar restricciones al criteria SOLO si son true (Ej: enfermedades. Si aptoHipertenso es True se debe agregar al filtro,
	// pero si aptoHipertenso es False no es necesario agregarlo, ya que debe traer ambos tipos de dietas.
	private void agregarRestriccionSiEsTrue(Criteria criteria, String restriccion, boolean valor) {
	    if (valor) {
	        criteria.add(Restrictions.eq(restriccion, valor));
	    }
	}
}
