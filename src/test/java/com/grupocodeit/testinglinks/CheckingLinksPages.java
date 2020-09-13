package com.grupocodeit.testinglinks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingLinksPages {
	private WebDriver driver;

	public CheckingLinksPages(WebDriver driver) {
		this.driver = driver;
	}

	public boolean checkingPagesLinks() {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		String url = "";

		// Listas para almacenar los links de las paginas
		List<String> brokenLinks = new ArrayList<String>();
		List<String> okLinks = new ArrayList<String>();

		// Clase HttpURLConnection, que permite realizar coneciones HTTP
		HttpURLConnection httpConection = null;

		// para guardar el codigo de estado de la conexion de la pagina
		int responseCode = 200;

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {
			url = it.next().getAttribute("href");
			if (url == null || url.isEmpty() || url.isBlank()) {
				System.out.println(url + " URL no esta configurada o esta vacia");
			}

			// se establece la conexion y se hace un cast para convertir lo que regresa a
			// httpurlconection
			try {
				httpConection = (HttpURLConnection) (new URL(url).openConnection());
				httpConection.setRequestMethod("HEAD");
				httpConection.connect();
				responseCode = httpConection.getResponseCode();

				if (responseCode >= 400) {
					System.out.println("ERROR BROKEN LINK: --> " + url);
					brokenLinks.add(url);
				} else {
					System.out.println("VALID LINK: " + url);
					okLinks.add(url);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("VALID LINKS: " + okLinks.size());
		System.out.println("INVALID LINKS: " + brokenLinks.size());

		// Imprimen los links fallidos
		if (brokenLinks.size() > 0) {
			System.out.println("=-=-=-= ERROR - BROKEN LINKS =-=-=-=");
			for (int i = 0; i < brokenLinks.size(); i++) {
				System.out.println(brokenLinks.get(i));
			}
			return false;
		} else {
			return true;
		}

	}
	
}
