package ar.edu.unlam.tallerweb1.persistencia;
import static org.assertj.core.api.Assertions.*;

import java.text.ParseException;

import javax.inject.Inject;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dao.*;
import ar.edu.unlam.tallerweb1.modelo.Formula;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

public class UsuarioTest extends SpringTest{
   
    @Inject
    private UsuarioDao dao;
   
    @Test @Rollback @Transactional
    public void ProbarQueSeGuardeUsuarioTest(){
       
        Usuario usuario = new Usuario();
        usuario.setEmail("ariel@live.com");
        usuario.setPassword("1234");
       
        getSession().save(usuario);
       
//        Usuario resultado=
//                (Usuario)getSession().createCriteria(Usuario.class)
//                .add(Restrictions.eq("email", "ariel@live.com"))
//                .uniqueResult();
        
    /*   Formula formula = new Formula();
        System.out.println("INICIA FORMULA******************************************");
        formula.generarListaPesoIdeal("10/10/1987", 60, 80, (double) 500);*/
        
        Usuario resultado=dao.consultarUsuario(usuario);
       
        assertThat(resultado).isNotNull();
       
       
    }
}