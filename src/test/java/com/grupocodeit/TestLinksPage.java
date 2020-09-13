package com.grupocodeit;

import org.testng.annotations.Test;

import com.grupocodeit.testinglinks.CheckingLinksPages;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class TestLinksPage {
	WebDriver driver;
	CheckingLinksPages page;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./src/test/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		page = new CheckingLinksPages(driver);		
		driver.get("AQUILAURLAPROBAR");
	}

	@Test
	public void f() {
		assertTrue(page.checkingPagesLinks(), "Hay links rotos");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
