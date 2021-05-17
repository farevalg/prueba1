package nej.ITTest;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import nej.ITTest.utils.ISeleniumConstantes;
import nej.ITTest.utils.ITGeneral;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ITConsultaDocumentacion extends ITGeneral {

	
	@BeforeClass
	public static void setUp() throws IOException {
		getSigleton().login();

		seleccionarSubsistema(ISeleniumConstantes.SUBSISTEMA_CONSULTA_DOCUMENTACION);
				
	}


	@Test
	public void test01Buscar() {
		driver.findElement(By.name("coMeses")).sendKeys("Enero");
		driver.findElement(By.name("coEjercicio")).sendKeys("2020");
		driver.findElement(By.name("action:ConsultaDocuAction_buscar")).click();
		esperar(1);
		comprobarDivNavegación("Consulta de Documentación");
	}
	

	@Test
	public void test02SeleccionarNomina() {
		WebElement tabla = driver.findElement(By.className("tabla"));
		List<WebElement> filas = tabla.findElements(By.tagName("tr"));
		Assert.assertFalse(filas.isEmpty());
		if (!filas.isEmpty()) {
			//Seleccionamos nomina.
			WebElement boton =  tabla.findElements(By.id("ConsultaDocuAction_buscar_boton_seleccionar")).get(0);
			boton.click();
			esperar(1);
		}
		
	}
	

	@Test
	public void test03ComprobarListado() {
		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		Assert.assertFalse(filas.isEmpty());
		if (!filas.isEmpty()) {
			//Seleccionamos nomina.
			List<WebElement> registros =  tabla.findElements(By.id("ConsultaDocuAction_buscarNomina_boton_abrir"));
			Assert.assertTrue(!registros.isEmpty());
		}
	}
	
	

	@AfterClass
	public static void tearmDown() {
		driver.quit();
	}
}
