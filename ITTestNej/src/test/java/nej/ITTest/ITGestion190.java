package nej.ITTest;

import java.io.IOException;

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

/**
 * Test de integración para el subsistema Gestión del 190
 *
 * @author francisco.arevalo
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ITGestion190 extends ITGeneral {

	@SuppressWarnings("static-access")
	@BeforeClass
	public static void setUp() throws IOException {
		
		getSigleton().login();
	
		seleccionarSubsistema(ISeleniumConstantes.SUBSISTEMA_GESTION_190);
		
		driver.findElement(By.id("Ejercicio190Action_boton_aceptar")).click();
			
		getSigleton().esperarSingleton(2);
	}


	/**
	 * Test que comprueba qaue los botones de la pantalla estén
	 */
	@Test
	public void test01Gestion190() {
		WebElement element = driver.findElement(By.xpath("//*[@id='Explotacion190Action_explotacion190_proceso']"));
		Assert.assertNotNull(element);
		
		element = driver.findElement(By.xpath("//*[@id='Explotacion190Action_explotacion190_ficheroRespuesta']"));
		Assert.assertNotNull(element);
				
		element = driver.findElement(By.xpath("//*[@id='Explotacion190Action_explotacion190_certificado']"));
		Assert.assertNotNull(element);
		
		element = driver.findElement(By.xpath("//*[@id='Explotacion190Action_explotacion190_certificado_descargar']"));
		Assert.assertNotNull(element);
	}
	
	/**
	 * Test que compueba la pantalla de navegacion al proceso 190
	 */
	@Test
	public void test02Gestion190() {
		WebElement element = driver.findElement(By.xpath("//*[@id='Explotacion190Action_explotacion190_proceso']"));
		element.click();
		
		comprobarDivNavegación("Selección de Ejercicio 190 > Menú de Explotación de 190 >Proceso del 190");
		
		//Volvemos a atras
		element = driver.findElement(By.xpath("//*[contains(@class, 'divNavegacion')]/a[2]"));
		element.click();
	
	}

	@Test
	public void test03Gestion190_CargarFicheroRespuesta() {
		WebElement element = driver.findElement(By.xpath("//*[@id='Explotacion190Action_explotacion190_ficheroRespuesta']"));
		element.click();
		
		comprobarDivNavegación("Selección de Ejercicio 190 > Menú de Explotación de 190 >Carga Fichero Respuesta del 190");
		
		//Volvemos a atras
		element = driver.findElement(By.xpath("//*[contains(@class, 'divNavegacion')]/a[2]"));
		element.click();
	
	}

	@Test
	public void test04Gestion190_GenerarCertificado() {
		WebElement element = driver.findElement(By.xpath("//*[@id='Explotacion190Action_explotacion190_certificado']"));
		element.click();
		
		comprobarDivNavegación("Selección de Ejercicio 190 > Menú de Explotación de 190 >Generación Certificado del 190");
		
		//Volvemos a atras
		element = driver.findElement(By.xpath("//*[contains(@class, 'divNavegacion')]/a[2]"));
		element.click();
	
	}

	@AfterClass
	public static void tearmDown() {
		driver.quit();
	}
}
