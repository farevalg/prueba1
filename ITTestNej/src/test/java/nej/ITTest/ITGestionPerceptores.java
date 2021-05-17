package nej.ITTest;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import nej.ITTest.utils.ISeleniumConstantes;
import nej.ITTest.utils.ITGeneral;

/**
 * Test que prueba el Subsistema de Gestion de perceptores
 * 
 * @author francisco.arevalo
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ITGestionPerceptores extends ITGeneral {

	
	@BeforeClass
	public static void setUp() throws IOException {
		getSigleton().login();

		seleccionarSubsistema(ISeleniumConstantes.SUBSISTEMA_GESTION_PERCEPTORES);
		
		seleccionarNomina();
		
	}

	private void buscarPerceptor() {

			By elemento = By.id("ConsultaPersonaAction_personaId");
			ponerTexto(elemento, "43071088T");
		
			elemento =  By.id("managerSubmit");
			driver.findElement(elemento).click();
			
			elemento = By.id("BuscaPersonaAction_boton_detalle");
			driver.findElement(elemento).click();
	
				
			esperar(2);
	}
	
	@Test
	public void test01GestionPercertor() {
		buscarPerceptor();	       
	}
	
	@Test
	public void test02DatosBancarios() {
		By elemento = By.id("aCheckedDatosBanco");
		driver.findElement(elemento).click();
		esperar(1);
		
	}
	
	@Test
	public void test03IRPF() {
		By elemento = By.id("aCheckedIRPF");
		driver.findElement(elemento).click();
		esperar(1);
		
	}
	
	@Test
	public void test04DatosDescendientes() {
		By elemento = By.id("aCheckedDescen");
		driver.findElement(elemento).click();
		esperar(1);
		
	}
	
	@Test
	public void test05DatosAscendientes() {
		By elemento = By.id("aCheckedAscen");
		driver.findElement(elemento).click();
		esperar(1);
		
	}
	
	@Test
	public void test06DatosRetribu() {
		By elemento = By.id("aCheckedRetri");
		driver.findElement(elemento).click();
		esperar(1);
		
	}
	
	@Test
	public void test07DatosRetenju() {
		By elemento = By.id("aCheckedRedJud");
		driver.findElement(elemento).click();
		esperar(1);
		
	}
	
	@AfterClass
	public static void tearmDown() {
		driver.quit();
	}
}
