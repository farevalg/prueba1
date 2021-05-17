package nej.ITTest;

import java.io.IOException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import nej.ITTest.utils.ISeleniumConstantes;
import nej.ITTest.utils.ITGeneral;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ITConsultaPerceptoresDifSSNom extends ITGeneral {

	
	@BeforeClass
	public static void setUp() throws IOException {
		getSigleton().login();

		seleccionarSubsistema(ISeleniumConstantes.SUBSISTEMA_CONSULTA_PERCEPTORES_DIF_SS_NOM);
		
	}

	@Before
	public void limpiarBusquedaAnterior() {
		clickBotonLimpiar();
	}
	
	/**
	 * Realiza la busqueda a partir de los parametros que se le pasen
	 * 
	 * @param checkBoxName
	 * @param mesDesde
	 */
	private void buscarCheck(String checkBoxName, String mesDesde) {
		if (driver.findElement(By.name("action:ConsultaPerDifSSNomAction_limpiar")) != null) {
			driver.findElement(By.name("action:ConsultaPerDifSSNomAction_limpiar")).click();
		}
		ponerTexto(By.name("mesDesde"), mesDesde);
		marcarCheck(By.name(checkBoxName)); 	
		WebElement botonBuscar = null;
		try {
			botonBuscar = driver.findElement(By.id("ConsultaPerDifSSNomAction_boton_buscar"));
		} catch (Exception ex) {
			botonBuscar = driver.findElement(By.id("ConsultaPerDifSSNomAction_limpiar_boton_buscar"));
		}
		botonBuscar.click();
		esperar(2);
	}


	/**
	 * Realiza la busqueda
	 */
	private void clickBotonLimpiar() {
		if (driver.findElement(By.name("action:ConsultaPerDifSSNomAction_limpiar")) != null) {
			driver.findElement(By.name("action:ConsultaPerDifSSNomAction_limpiar")).click();
		} 	
	}
	
	/**
	 * Realiza la busqueda
	 */
	private void clickBotonBuscar() {
		WebElement botonBuscar = null;
		try {
			botonBuscar = driver.findElement(By.id("ConsultaPerDifSSNomAction_boton_buscar"));
		} catch (Exception ex) {
			botonBuscar = driver.findElement(By.id("ConsultaPerDifSSNomAction_limpiar_boton_buscar"));
		}
		botonBuscar.click();
		esperar(2);
	}
	
	@Test
	public void test01BuscarTodasDiferencias() {
		
		ponerTexto(By.name("mesDesde"), "01/2020");
		marcarCheck(By.name("indTodos"));	
		clickBotonBuscar();
		esperar(2);
		
		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		Assert.assertFalse(filas.isEmpty());
	}
	
	@Test
	public void test02BuscarReduccionesJornada() {
		
		buscarCheck("indRJ", "01/2020");
		
		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Reducciones de Jornada".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}

	@Test
	public void test03BasesFijas() {
		
		buscarCheck("indBF", "01/2020");
		
		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
			if (filas!=null) {
			for (int i=0; i<filas.size(); i++) {
				WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
				Assert.assertTrue(grupoDatos.getAttribute("value").contains("Bases Fijas"));
			}
		}
	}

	@Test
	public void test04IncapacidadesTemporalesCC() {

		buscarCheck("indTCC", "01/2020");

		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Inc. Temporales CC".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}

	}

	@Test
	public void test05Permisos() {
		buscarCheck("indP", "01/2010");

		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Permisos".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}
	
	@Test
	public void test06BuscarSuspensionEmpleo() {

		buscarCheck("indSE", "01/2010");

		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Suspensión de Empleo".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}
	
	@Test
	public void test07BuscarDatosCotizacion() {

		buscarCheck("indDC", "01/2010");

		
		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Datos de Cotización".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}

	@Test
	public void test08BuscarReduccionHoras() {

		buscarCheck("indRH", "01/2010");
		List<WebElement> h3 = driver
				.findElements(By.xpath("//h3[contains(text(), 'No existen resultados para la búsqueda.')]"));

		if (h3.isEmpty()) {
			WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
			if (tabla != null) {
				List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
				for (int i = 0; i < filas.size(); i++) {
					WebElement grupoDatos = filas.get(i)
							.findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));
					Assert.assertTrue("Reducción de Horas".equalsIgnoreCase(grupoDatos.getAttribute("value")));
				}
			}
		} else {
			Assert.assertTrue(true);
		}

	}

	@Test
	public void test09BuscarModificacionBases() {

		buscarCheck("indMB", "01/2010");
		List<WebElement> h3 = driver
				.findElements(By.xpath("//h3[contains(text(), 'No existen resultados para la búsqueda.')]"));
		if (h3.isEmpty()) {

			WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
			List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
			for (int i = 0; i < filas.size(); i++) {
				WebElement grupoDatos = filas.get(i)
						.findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));
				Assert.assertTrue("Modificaciones de Bases".equalsIgnoreCase(grupoDatos.getAttribute("value")));
			}

		} else {
			// No existen registros.
			Assert.assertEquals(h3.get(0).getText(), "No existen resultados para la búsqueda.");

		}
	}

	@Test
	public void test10BuscarIncTemporalesAT() {

		buscarCheck("indTAT", "01/2010");

		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Inc. Temporales AT".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}

	@Test
	public void test11BuscarLicencias() {

		buscarCheck("indL", "01/2010");

		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Licencias".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}

	@Test
	public void test12BuscarAusencias() {
		buscarCheck("indA", "01/2010");
		List<WebElement> h3 = driver
				.findElements(By.xpath("//h3[contains(text(), 'No existen resultados para la búsqueda.')]"));
		if (h3.isEmpty()) {

			WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
			List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
			for (int i = 0; i < filas.size(); i++) {
				WebElement grupoDatos = filas.get(i)
						.findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));
				Assert.assertTrue("Ausencias".equalsIgnoreCase(grupoDatos.getAttribute("value")));
			}
		} else {
			// No existen registros.
			Assert.assertEquals(h3.get(0).getText(), "No existen resultados para la búsqueda.");

		}
	}
	
	@Test
	public void test13PermisoSinCarencia() {

		buscarCheck("indPC", "01/2010");

		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Periodo sin Carencia".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}

	@Test
	public void test14BasesCondicionadas() {

		buscarCheck("indBC", "01/2019");

		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Bases Condicionadas".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}

	@Test
	public void test15ExcepcionesCotizacion() {

		buscarCheck("indEC", "01/2010");

		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Exenciones de Cotización".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}

	@Test
	public void test16ExcIncPermanente() {

		buscarCheck("indEIP", "01/2010");

		WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
		List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
		for (int i=0; i<filas.size(); i++) {
			WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
			Assert.assertTrue("Exp. de Inc. Permanente".equalsIgnoreCase(grupoDatos.getAttribute("value")));
		}
	}

	@Test
	public void test17Excedencias() {

		buscarCheck("indE", "01/2010");

		List<WebElement> h3 = driver
				.findElements(By.xpath("//h3[contains(text(), 'No existen resultados para la búsqueda.')]"));
		if (h3.isEmpty()) {

			
			WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
			if (tabla !=null) {
				List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
				for (int i=0; i<filas.size(); i++) {
					WebElement grupoDatos = filas.get(i).findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));					
					Assert.assertTrue("Excedencias	".equalsIgnoreCase(grupoDatos.getAttribute("value")));
				}
			}
		} else {
			//No existen registros.
			Assert.assertEquals(h3.get(0).getText(), "No existen resultados para la búsqueda.");
			
		}
	}
	
	@Test
	public void test17Maternidad() {

		buscarCheck("indSM", "01/2010");

		List<WebElement> h3 = driver
				.findElements(By.xpath("//h3[contains(text(), 'No existen resultados para la búsqueda.')]"));
		if (h3.isEmpty()) {

			WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
			List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
			for (int i = 0; i < filas.size(); i++) {
				WebElement grupoDatos = filas.get(i)
						.findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_descGrupoDatos']"));
				Assert.assertTrue("Situaciones de Maternidad".equalsIgnoreCase(grupoDatos.getAttribute("value")));
			}
		} else {
			// No existen registros.
			Assert.assertEquals(h3.get(0).getText(), "No existen resultados para la búsqueda.");

		}

	}
	
	@Test
	public void test18Identificador() {
		
		ponerTexto(By.name("mesDesde"), "01/2019");
		ponerTexto(By.name("personaId"), "43163025Y");
		marcarCheck(By.name("indTodos")); 	
		
		clickBotonBuscar();
		
		List<WebElement> h3 = driver
				.findElements(By.xpath("//h3[contains(text(), 'No existen resultados para la búsqueda.')]"));
		if (h3.isEmpty()) {

			WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
			List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
			for (int i = 0; i < filas.size(); i++) {	
				WebElement grupoDatos = filas.get(i)
						.findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_personaId']"));
				Assert.assertTrue("43163025Y".equalsIgnoreCase(grupoDatos.getAttribute("value")));
			}
		} else {
			// No existen registros.
			Assert.assertEquals(h3.get(0).getText(), "No existen resultados para la búsqueda.");

		}
	}
	
	@Test
	public void test19Nombre() {
		
		ponerTexto(By.name("mesDesde"), "01/2019");
		ponerTexto(By.name("nombre"), "sar");
		marcarCheck(By.name("indTodos")); 	

		clickBotonBuscar();
		
		List<WebElement> h3 = driver
				.findElements(By.xpath("//h3[contains(text(), 'No existen resultados para la búsqueda.')]"));
		if (h3.isEmpty()) {

			WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
			List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
			for (int i = 0; i < filas.size(); i++) {
				WebElement grupoDatos = filas.get(i)
						.findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_nombre']"));
				Assert.assertTrue(grupoDatos.getAttribute("value").toUpperCase().startsWith("SAR"));
			}
		} else {
			// No existen registros.
			Assert.assertEquals(h3.get(0).getText(), "No existen resultados para la búsqueda.");

		}
	}

	@Test
	public void test20Apellido1() {

		
		ponerTexto(By.name("mesDesde"), "01/2019");
		ponerTexto(By.name("apellido1"), "BAK");
		marcarCheck(By.name("indTodos")); 	
		
		clickBotonBuscar();
		
		List<WebElement> h3 = driver
				.findElements(By.xpath("//h3[contains(text(), 'No existen resultados para la búsqueda.')]"));
		if (h3.isEmpty()) {

			WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
			List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
			for (int i = 0; i < filas.size(); i++) {
				WebElement grupoDatos = filas.get(i)
						.findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_apellido1']"));
				Assert.assertTrue(grupoDatos.getAttribute("value").toUpperCase().startsWith("BAK"));
			}
		} else {
			// No existen registros.
			Assert.assertEquals(h3.get(0).getText(), "No existen resultados para la búsqueda.");

		}
	}

	@Test
	public void test21Apellido2() {

		ponerTexto(By.name("mesDesde"), "01/2019");
		ponerTexto(By.name("apellido2"), "ROD");
		marcarCheck(By.name("indTodos")); 	

		clickBotonBuscar();
		
		List<WebElement> h3 = driver
				.findElements(By.xpath("//h3[contains(text(), 'No existen resultados para la búsqueda.')]"));
		if (h3.isEmpty()) {

			WebElement tabla = driver.findElement(By.className(ISeleniumConstantes.WEB_CLASS_TABLA));
			List<WebElement> filas = tabla.findElements(By.tagName(ISeleniumConstantes.WEB_TAG_TR));
			for (int i = 0; i < filas.size(); i++) {
				WebElement grupoDatos = filas.get(i)
						.findElement(By.xpath("//*[@id='ConsultaPerDifSSNomAction_buscar_apellido2']"));
				Assert.assertTrue(grupoDatos.getAttribute("value").toUpperCase().startsWith("ROD"));
			}
		} else {
			// No existen registros.
			Assert.assertEquals(h3.get(0).getText(), "No existen resultados para la búsqueda.");

		}
	}
	
	@AfterClass
	public static void tearmDown() {
		driver.quit();
	}
}
