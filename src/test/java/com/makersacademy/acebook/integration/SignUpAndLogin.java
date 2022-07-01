package com.makersacademy.acebook.integration;

import org.junit.Test;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignUpAndLogin {   

@Ignore @Test   

public void SignUpAndLogin() throws InterruptedException {     

  // Optional. If not specified, WebDriver searches the PATH for chromedriver.      
  System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");           
  WebDriver driver = new ChromeDriver(); 

  driver.get("http://www.google.com/");    

  Thread.sleep(5000);  // Let the user actually see something!     

  WebElement searchBox = driver.findElement(By.name("q"));

  searchBox.sendKeys("ChromeDriver");     

  searchBox.submit();    

  Thread.sleep(5000);  // Let the user actually see something!     

  driver.quit();  

 } 

}