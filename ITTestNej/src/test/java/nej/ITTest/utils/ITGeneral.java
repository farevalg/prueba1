package nej.ITTest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Clase que implementa las funciones comunes a todos los test
 * 
 * @author francisco.arevalo
 */
public class ITGeneral {
	
	protected static WebDriver driver;
	protected static String URL_NEJ = "http://localhost:8080/nej/";
	protected static JavascriptExecutor js;
	protected static Properties properties = new Properties();
	
	private static ITGeneral instancia = null;
	
	/**
	 * Implementa el patrón Sigleton
	 * 
	 * @return
	 */
	public static ITGeneral getSigleton() {
		if (instancia==null) {
			instancia = new ITGeneral();
		}
		
		return instancia;
	}

	public void loadProperties() throws IOException {
		InputStream is = null;
		is = this.getClass().getResourceAsStream("/nej/ITconfiguration/nej.properties");
		properties.load(is);

	}

	protected static void inicializarCromeDriver() throws IOException {
	   
     	System.setProperty("webdriver.chrome.driver", properties.getProperty("URLCromeDriver"));
     	System.setProperty("webdriver.chrome.whitelistedIps", "");
     	  
//     	ChromeOptions options = new ChromeOptions();
//     	options.addArguments(" --whitelist-ip $*");
//     	options.addArguments("--allowed-ips");
    	driver = new ChromeDriver();

		js = (JavascriptExecutor) driver;

	}

		
	public void login() throws IOException {
		final String webElementUsuario = "usuario";
		final String webElementPassword = "password";
		loadProperties();

		String usuario = properties.getProperty("Selenium_usuario");
		String password = properties.getProperty("Selenium_password");
		
		inicializarCromeDriver();
		URL_NEJ = properties.getProperty("URLNej");
		driver.get(URL_NEJ);
		// 2 | setWindowSize | 1051x806 |
		driver.manage().window().setSize(new Dimension(1051, 806));
		// 3 | click | name=submit |
		driver.findElement(By.name(webElementUsuario)).click();
		driver.findElement(By.name(webElementUsuario)).clear();
		driver.findElement(By.name(webElementUsuario)).sendKeys(usuario);
		driver.findElement(By.name(webElementPassword)).click();
		driver.findElement(By.name(webElementPassword)).clear();
		driver.findElement(By.name(webElementPassword)).sendKeys(password);

		driver.findElement(By.id("LoginAction_boton_aceptar")).click();
		try {
			driver.wait(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ok");
	}

	public void login(String usuario, String password) throws IOException {
		loadProperties();

		inicializarCromeDriver();
		URL_NEJ = properties.getProperty("URLNej");
		driver.get(URL_NEJ);
		// 2 | setWindowSize | 1051x806 |
		driver.manage().window().setSize(new Dimension(1051, 806));
		// 3 | click | name=submit |
		driver.findElement(By.name("usuario")).click();
		driver.findElement(By.name("usuario")).clear();
		driver.findElement(By.name("usuario")).sendKeys(usuario);
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.id("LoginAction_boton_aceptar")).click();
		try {
			driver.wait(100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok");
	}

	/**
	 * Selecciona Subsistemam
	 */
	protected static void seleccionarSubsistemaDefecto() {
		driver.findElement(By.id("SelectOrgSubAction_boton_aceptar")).click();
	}
	
	/**
	 * Selecciona Subsistema
	 */
	protected static void seleccionarSubsistema(String subsistema) {
		WebElement webElement = driver.findElement(By.id("subsistema"));
	    Select selectSubSistema = new Select(webElement);
	    selectSubSistema.selectByVisibleText(subsistema);
		driver.findElement(By.id("SelectOrgSubAction_boton_aceptar")).click();
	}
	
	
	protected static void seleccionarNomina() {
		driver.findElement(By.id("SelectNominaAction_boton_seleccionar")).click();
	}

	protected static String getAlertMensaje() {
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * Cierra el mensaje de alert
	 */
	protected static void cerrarAlert() {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * Envia un texto como si se teclease por el usuario
	 * 
	 * @param element objeto web a rellenar
	 * @param texto 
	 */
	protected void ponerTexto(By element, String texto) {
		driver.findElement(element).click();
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(texto);

	}

	protected void marcarCheck(By element) {
		driver.findElement(element).click();
	}

	/**
	 * Espera un numero determinado se segundos antes de continuar la ejecucion
	 * @param segundos
	 */
	protected void esperar(int segundos) {
		driver.manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
	}

	protected static void esperarSingleton(int segundos) {
		driver.manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
	}

	/**
	 * Comprueba el contenido de la capa de navegación. (Migas de pan)
	 * 
	 * @param texto
	 */
	protected void comprobarDivNavegación(String texto) {
		WebElement element = driver.findElement(By.xpath("//*[contains(@class, 'divNavegacion')]"));
		Assert.assertNotNull(element);
		
		texto.equalsIgnoreCase(element.getText());

	}

	/**
	 * Devuelve el numero de filas de una tabla HTML a partir de su css
	 * 
	 * @param className
	 * @return
	 */
	protected int contarFilasTabla(String className) {

		return driver.findElements(By.xpath("//table[class='" + className + "')]/tr")).size();
		
	}
}