package acme.framework.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public abstract class WaitConditions {

	// Constructors -------------------------------------------------------

	private WaitConditions() {
	}

	// Business methods -----------------------------------------------------

	public static ExpectedCondition<Boolean> stalenessOf(final WebElement element, final By locator) {
		assert element instanceof RemoteWebElement;
		assert locator != null;
		
		ExpectedCondition<Boolean> result;
		
		result = (final WebDriver driver) -> {
			boolean answer;
			WebElement target;
			String oldId, newId, readyState;
			
			target = driver.findElement(locator);
			assert target instanceof RemoteWebElement;
			oldId = ((RemoteWebElement)element).getId();
			newId = ((RemoteWebElement)target).getId();
			readyState = (String)((JavascriptExecutor)driver).executeScript("return document.readyState;");
			answer = !oldId.equals(newId) && "complete".equals(readyState);
			
			return answer;
		};
		
		
		return result;
	}

	public static ExpectedCondition<Boolean> documentComplete() {
		ExpectedCondition<Boolean> result;
		
		result = (final WebDriver driver) -> {
			boolean answer;
			String readyState;
			
			readyState = (String)((JavascriptExecutor)driver).executeScript("return document.readyState;");
			answer = "complete".equals(readyState);
			
			return answer;		
		};
		
		return result;
	}

}
