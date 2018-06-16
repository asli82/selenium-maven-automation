package com.dice;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		//set up chrome driver path
		WebDriverManager.chromedriver().setup();
//selenium daki esiti
//System.setProperty("webdriver.chrome.driver", 
//	"C:\\Users\\a_tur\\Documents\\selenium dependencies\\drivers\\chromedriver.exe");
		//invoke selenium webdriver
		WebDriver driver = new ChromeDriver();
		//fullscreen
		//driver.manage().window().fullscreen();
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//navigate the web site
		String url = "https://dice.com";
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage successfully loaded");
		}else {
			System.out.println("Step FAIL. Dice homepage did not load successfully");	
			throw new RuntimeException("Step FAIL. Dice homepage did not load successfully");
		}
		
		String keyword = "java jobs";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "22102";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();  // or .submit();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		
		int countResult = Integer.parseInt(count.replaceAll(",", ""));
		System.out.println(countResult);
		
		if(countResult > 0) {
			System.out.println("Step PASS : Keyword : " + keyword + " search returned " +
					countResult + " results in " + location);
		}else {
			System.out.println("Step FAIL : Keyword : " + keyword + " search returned " +
					countResult + " results in " + location);
		}
		
		
		driver.close();
		System.out.println("TEST COMPLETED" + LocalTime.now());
	}

}
