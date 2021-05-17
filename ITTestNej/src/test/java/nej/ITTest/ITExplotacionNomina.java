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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ITExplotacionNomina extends ITGeneral {

	/**
	 * Seleccionamos el susbistema explotación Nomina
	 * 
	 * @throws IOException
	 */
	
	@BeforeClass
	public static void setUp() throws IOException {
		
		getSigleton().login();
	
		seleccionarSubsistema(ISeleniumConstantes.SUBSISTEMA_EXPLOTACION_NOMINAS);
		
		driver.findElement(By.id("SelectNominaAction_boton_seleccionar")).click();
	
		ITGeneral.esperarSingleton(2);
	}

	@Test
	public void test01CheckBotones() {
		WebElement element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_calcular']"));
		Assert.assertNotNull(element);
		
		element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_documentar']"));
		Assert.assertNotNull(element);
				
		element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_consolidar']"));
		Assert.assertNotNull(element);

		element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_consolidarRetMan']"));
		Assert.assertNotNull(element);

		//element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consolidarRetManFP']"));
		//Assert.assertNotNull(element);
		
		element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_documentarBancos']"));
		Assert.assertNotNull(element);
		
		element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_gestNominas']"));
		Assert.assertNotNull(element);
		

		element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_verDocu']"));
		Assert.assertNotNull(element);		
		
		element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_liqNegs']"));
		Assert.assertNotNull(element);		
	}

	
	@Test
	public void test02ConsultaProcesoCalculo() {
		WebElement element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_calcular']"));
		element.click();
		
		element = driver.findElement(By.xpath("//*[contains(@class, 'divNavegacion')]"));
		Assert.assertNotNull(element);
		
		Assert.assertEquals(element.getText(), "Menú de explotación >Consulta procesos de Cálculo");

		element = driver.findElement(By.xpath("//*[@id='CalcularAction_head_consultar']"));
		element.click();
		
		esperar(2);
		
		//Buscamos resultados
		
		int result = driver.findElements(By.xpath("//*[contains(@class, 'tabla')]/*/tr")).size();
		Assert.assertTrue(result>0);
	
		driver.navigate().back();
		driver.navigate().back();
	}
	
	@Test
	public void test03ConsultaProcesoDocumentacion() throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_documentar']"));
		element.click();
		
		esperar(2);
		
		element = driver.findElement(By.xpath("//*[contains(@class, 'divNavegacion')]"));
		Assert.assertNotNull(element);
		
		Assert.assertEquals(element.getText(), "Menú de explotación >Lanzamiento de Documentación");

		element = driver.findElement(By.xpath("//*[@id='DocumentarAction_head_consultar']"));
		element.click();
		
		esperar(2);
		
//		int result = driver.findElements(By.xpath("//*[contains(@class, 'tabla')]/*/tr")).size();
//		Assert.assertTrue(result>0);

//		esperar(1);


		driver.navigate().back();
		driver.navigate().back();
	}
	
	@Test
	public void test04LanzarProcesoConsolidacion() {
		
		WebElement element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_consolidar']"));
		element.click();
		
		esperar(2);
		
		comprobarDivNavegación("Menú de explotación >Lanzamiento de Consolidación");

//		element = driver.findElement(By.xpath("//*[@id='CalcularAction_head_consultar']"));
//		element.click();
//		
//		esperar(2);
		
		//Buscamos resultados
		
//		int result = driver.findElements(By.xpath("//*[contains(@class, 'tabla')]/*/tr")).size();
//		Assert.assertTrue(result>0);

		driver.navigate().back();
	}

	@Test
	public void test05ProcesoRetMan() {
		WebElement element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_consolidarRetMan']"));
		element.click();
		
		esperar(2);
		
		comprobarDivNavegación("Menú de explotación >Lanzamiento de consolidación");

		driver.navigate().back();
	}

	@Test
	public void test06ProcesoRetManFP() {
		WebElement element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consolidarRetManFP']"));
		//WebElement element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_consolidarRetManFP']"));
		element.click();
		
		esperar(2);
				
		comprobarDivNavegación("Menú de explotación >Lanzamiento de consolidación");

		driver.navigate().back();
		
	}

	@Test
	public void test07ProcesoDocumentarBancos() {
		WebElement element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_consulta_documentarBancos']"));
		element.click();
		
		esperar(2);
		
		comprobarDivNavegación("Menú de explotación >Lanzamiento de Documentación de Bancos");
		
		driver.navigate().back();
	}

	@Test
	public void test08GestionNominas() {
		WebElement element = driver.findElement(By.xpath("//*[@id='ExplotacionAction_explotacion_gestNominas']"));
		element.click();
		
		esperar(2);
		
		comprobarDivNavegación("Menú de explotación >Gestión de Nóminas");
		
		driver.navigate().back();
	}
	
	@AfterClass
	public static void tearmDown() {
		driver.quit();
	}
}