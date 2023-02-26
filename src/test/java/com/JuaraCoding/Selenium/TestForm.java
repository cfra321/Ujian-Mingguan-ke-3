package com.JuaraCoding.Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestForm {
        WebDriver driver;
        @BeforeClass
        public void setUp(){
            System.setProperty("webdriver.chrome.driver", "C:\\juaracoding\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Test(priority=1)
        public void TestUrl(){
            // Go to https://shop.demoqa.com/
            String url = "https://shop.demoqa.com/";
            driver.get(url);

            // print url https://shop.demoqa.com/
            System.out.println("Get URL"+url);
            driver.manage().window().maximize();

            // Click on the "My Account" link
            driver.findElement(By.xpath("//a[@class='woocommerce-store-notice__dismiss-link']")).click();
            driver.findElement(By.xpath("//a[normalize-space()='My Account']")).click();
            Assert.assertEquals(url,"https://shop.demoqa.com/");
        }

        @Test(priority = 2)
        public void testLogin(){
            //scroll to fine button register and login
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1800)");

            // Enter the usser name , email address and password
            WebElement usernameInput = driver.findElement(By.id("username"));
            WebElement passwordInput = driver.findElement(By.id("password"));
            usernameInput.sendKeys("kukuh");
            passwordInput.sendKeys("Nasiuduk123");

            //click register
            driver.findElement(By.id("rememberme")).click();
            driver.findElement(By.xpath("(//button[normalize-space()='Log in'])[1]")).click();

            //jika berhasil login
            String register = driver.findElement(By.xpath("(//strong[contains(text(),'kukuh')])[1]")).getText();
            Assert.assertTrue(register.contains("kukuh"));
            System.out.println("berhasil login dengan akun " + register);
        }

        @Test(priority = 3)
        public void testAddProduc(){
            // Go to https://shop.demoqa.com/
            String url = "https://shop.demoqa.com/";
            driver.get(url);

            //scroll to find product
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1800)");

            //add product to chart
            String finditem = driver.findElement(By.xpath("(//a[normalize-space()='Black Cross Back Maxi Dress'])[1]")).getText();
            System.out.println("add product to chart = "+finditem);
            driver.findElement(By.xpath("(//a[normalize-space()='Black Cross Back Maxi Dress'])[1]")).click();

            //select color product
            WebElement selectColor = driver.findElement(By.id("pa_color"));
            Select color = new Select(selectColor);
            color.selectByValue("black");
            System.out.println("select color = Black");
            delay(2);

            //selet size product
            WebElement selectSize = driver.findElement(By.id("pa_size"));
            Select Size = new Select(selectSize);
            Size.selectByValue("medium");
            System.out.println("select size = medium");
            delay(2);

            //add to chart
           driver.findElement(By.xpath("(//button[normalize-space()='Add to cart'])[1]")).click();
           String hasbeenadd = driver.findElement(By.xpath("(//div[@role='alert'])[1]")).getText();
           Assert.assertTrue(hasbeenadd.contains("has been added to your cart."));

        }
        @AfterClass
        public void tearDown() {
            delay(2);
            // Close the browser
            driver.quit();
        }

        public static void delay(long detik){
            try {
                    Thread.sleep(detik*1000);
             } catch (InterruptedException e) {
                throw new RuntimeException(e);
             }
            }

}
