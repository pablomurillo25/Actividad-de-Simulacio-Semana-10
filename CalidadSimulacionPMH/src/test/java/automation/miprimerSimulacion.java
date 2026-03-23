package automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class miprimerSimulacion {

	private WebDriver driver;

	@Before
	public void setUp() {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Driver/chromedriver.exe");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}

	@Test
	public void TC01_LoginExitoso() {

		WebElement txtUserName = driver.findElement(By.id("user-name"));
		WebElement txtPass = driver.findElement(By.id("password"));
		WebElement btnLogin = driver.findElement(By.id("login-button"));

		if (txtUserName.isDisplayed() && txtUserName.isEnabled()) {

			txtUserName.sendKeys("standard_user");
			txtPass.sendKeys("secret_sauce");
			btnLogin.click();

			if (driver.getCurrentUrl().contains("inventory")) {
				System.out.println("Login exitoso");
			} else {
				System.out.println("Error: no ingresó al sistema");
			}

		} else {
			System.out.println("Error: campos de login no disponibles");
		}
	}

	@Test
	public void TC02_CompraCompleta() {

		TC01_LoginExitoso();

		try {
			driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
			driver.findElement(By.className("shopping_cart_link")).click();
			driver.findElement(By.id("checkout")).click();

			driver.findElement(By.id("first-name")).sendKeys("Pablo");
			driver.findElement(By.id("last-name")).sendKeys("Murillo");
			driver.findElement(By.id("postal-code")).sendKeys("1234");

			driver.findElement(By.id("continue")).click();
			driver.findElement(By.id("finish")).click();

			System.out.println("Compra realizada correctamente");

		} catch (Exception e) {
			System.out.println("Error en la compra: " + e.getMessage());
		}
	}

	@Test
	public void TC03_Logout() {

		TC01_LoginExitoso();

		try {
			driver.findElement(By.id("react-burger-menu-btn")).click();
			driver.findElement(By.id("logout_sidebar_link")).click();

			if (driver.getCurrentUrl().contains("saucedemo")) {
				System.out.println("Logout ejecutado correctamente");
			} else {
				System.out.println("Error en logout");
			}

		} catch (Exception e) {
			System.out.println("Error en logout: " + e.getMessage());
		}
	}

	@Test
	public void TC04_ResetApp() {

		TC01_LoginExitoso();

		try {
			driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

			driver.findElement(By.id("react-burger-menu-btn")).click();
			driver.findElement(By.id("reset_sidebar_link")).click();

			System.out.println("Reset realizado correctamente");

		} catch (Exception e) {
			System.out.println("Error en reset: " + e.getMessage());
		}
	}

	@Test
	public void TC05_VerCarrito() {

		TC01_LoginExitoso();

		try {
			driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
			driver.findElement(By.className("shopping_cart_link")).click();

			System.out.println("Carrito visualizado correctamente");

		} catch (Exception e) {
			System.out.println("Error al visualizar carrito: " + e.getMessage());
		}
	}

	@Test
	public void TC06_AgregarEliminarProducto() {

		TC01_LoginExitoso();

		try {
			driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
			driver.findElement(By.id("remove-sauce-labs-backpack")).click();

			System.out.println("Producto agregado y eliminado correctamente");

		} catch (Exception e) {
			System.out.println("Error en gestión de producto: " + e.getMessage());
		}
	}

	@After
	public void after() {
		driver.quit();
	}
}
