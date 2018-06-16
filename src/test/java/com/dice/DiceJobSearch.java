package com.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
/*
	pass each item to search box and print accordingly.
modify your arraylist 

java-1234

2) Store all keywords into a text file 
read the text file and  repeat above steps.

store keyword and results count into an arraylist.
----

after closing browser.
print contents of arraylist that was updated each time 
we looped.
https://github.com/CybertekSchool/selenium-maven-automation
Job_List.txt
	*/
	public static void main(String[] args) throws NumberFormatException, IOException {
		List<String> arr = new ArrayList<>();
		
		FileReader fr = new FileReader("myList.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String line = "";
		while((line = br.readLine()) != null) {
			String addingElement = "";
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
		
		String keyword = line;
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
		addingElement = keyword + "-" + countResult;
		arr.add(addingElement);
		driver.close();
		System.out.println("TEST COMPLETED" + LocalTime.now());
		}
		System.out.println(arr.toString());
		/*
		HOMEWORK
		1)Create arraylist of keywords.
add 20 different keywords
list.add("java");

pass each item to search box and print accordingly.
modify your arraylist 

java-1234

2) Store all keywords into a text file 
read the text file and  repeat above steps.

store keyword and results count into an arraylist.
----

after closing browser.
print contents of arraylist that was updated each time 
we looped.
https://github.com/CybertekSchool/selenium-maven-automation
Job_List.txt
		
		*/
	}

}
