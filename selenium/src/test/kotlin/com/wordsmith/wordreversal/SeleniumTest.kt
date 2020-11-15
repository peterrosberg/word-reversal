package com.wordsmith.wordreversal

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class SeleniumTest {

    private val sentence = "Selenium test"
    private val reversedSentence = "muineleS tset"

    @BeforeEach
    fun setUp() {
        driver.get("https://wordreversal.herokuapp.com/")
    }

    @Test
    fun runTest() {
        val textInput: WebElement = driver.findElement(By.id("sentenceInput"))
        val submitButton: WebElement = driver.findElement(By.id("postSentence"))
        textInput.sendKeys(sentence)

        submitButton.click()

        val wait = WebDriverWait(driver, 2)
        wait.until(
                ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("result")), reversedSentence)
        )
    }

    companion object {
        private val driver: WebDriver = ChromeDriver()

        @AfterAll
        @JvmStatic
        fun destroy() {
            driver.quit()
        }
    }
}