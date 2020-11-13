package com.pepp.wordreversal

import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class SeleniumTest {

    @Test
    fun runTest() {

        // Create an instance of the driver
        val driver: WebDriver = ChromeDriver()

        // Navigate to a web page
        driver.get("https://wordreversal.herokuapp.com/")

        // Perform actions on HTML elements, entering text and submitting the form
        val textInput: WebElement = driver.findElement(By.name("w3review"))
        textInput.sendKeys("Selenium test")

        //passwordElement.submit(); // submit by text input element
        //formElement.submit() // submit by form element

        // Anticipate web browser response, with an explicit wait
        val wait = WebDriverWait(driver, 20)
        val messageElement: WebElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginResponse"))
        )

        // Run a test
        val message: String = messageElement.getText()

        // Conclude a test
        driver.quit()
    }
}