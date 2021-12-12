package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import acme.testing.AcmeEndeavoursTest;

public class AdministratorShowDashboardTest extends AcmeEndeavoursTest{
	
	@Test
	@Order(30)
	public void administratoDashboardPositive() {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		
		By locatorNumberPublicTasks;
		locatorNumberPublicTasks = By.xpath("/html/body/div[2]/div/table/tbody/tr[1]/td");
		final WebElement numberPublicTasks = this.driver.findElement(locatorNumberPublicTasks);
		Assertions.assertEquals("9.00", numberPublicTasks.getText());

		final By locatorNumberPrivateTask;
		locatorNumberPrivateTask = By.xpath("/html/body/div[2]/div/table/tbody/tr[2]/td");
		final WebElement numberPrivateTask = this.driver.findElement(locatorNumberPrivateTask);
		Assertions.assertEquals("3.00", numberPrivateTask.getText());

		By locatorNumberFinalTask;
		locatorNumberFinalTask = By.xpath("/html/body/div[2]/div/table/tbody/tr[3]/td");
		final WebElement numberFinalTask = this.driver.findElement(locatorNumberFinalTask);
		Assertions.assertEquals("7.00", numberFinalTask.getText());

		final By locatorNumberNoFinalTask;
		locatorNumberNoFinalTask = By.xpath("/html/body/div[2]/div/table/tbody/tr[4]/td");
		final WebElement numberNoFinalTask = this.driver.findElement(locatorNumberNoFinalTask);
		Assertions.assertEquals("5.00", numberNoFinalTask.getText());

		final By locatorAverageDurationPeriodTasks;
		locatorAverageDurationPeriodTasks = By.xpath("/html/body/div[2]/div/table/tbody/tr[5]/td");
		final WebElement averageDurationPeriodTasks = this.driver.findElement(locatorAverageDurationPeriodTasks);
		Assertions.assertEquals("20,699.00", averageDurationPeriodTasks.getText());

		By locatorDeviationDurationPeriodTasks;
		locatorDeviationDurationPeriodTasks = By.xpath("/html/body/div[2]/div/table/tbody/tr[6]/td");
		final WebElement deviationDurationPeriodTasks = this.driver.findElement(locatorDeviationDurationPeriodTasks);
		Assertions.assertEquals("1,345.06", deviationDurationPeriodTasks.getText());

		By locatorMinimumDurationPeriodTasks;
		locatorMinimumDurationPeriodTasks = By.xpath("/html/body/div[2]/div/table/tbody/tr[7]/td");
		final WebElement minimumDurationPeriodTasks = this.driver.findElement(locatorMinimumDurationPeriodTasks);
		Assertions.assertEquals("1.00", minimumDurationPeriodTasks.getText());

		By locatorMaximumDurationPeriodTasks;
		locatorMaximumDurationPeriodTasks = By.xpath("/html/body/div[2]/div/table/tbody/tr[8]/td");
		final WebElement maximumDurationPeriodTasks = this.driver.findElement(locatorMaximumDurationPeriodTasks);
		Assertions.assertEquals("3,673.00", maximumDurationPeriodTasks.getText());

		By locatorAverageWorkloadTasks;
		locatorAverageWorkloadTasks = By.xpath("/html/body/div[2]/div/table/tbody/tr[9]/td");
		final WebElement averageWorkloadTasks = this.driver.findElement(locatorAverageWorkloadTasks);
		Assertions.assertEquals("3.60", averageWorkloadTasks.getText());

		By locatorDeviationWorkloadTasks;
		locatorDeviationWorkloadTasks = By.xpath("/html/body/div[2]/div/table/tbody/tr[10]/td");
		final WebElement deviationWorkloadTasks = this.driver.findElement(locatorDeviationWorkloadTasks);
		Assertions.assertEquals("4.14", deviationWorkloadTasks.getText());

		By locatorMinimumWorkloadTasks;
		locatorMinimumWorkloadTasks = By.xpath("/html/body/div[2]/div/table/tbody/tr[11]/td");
		final WebElement minimumWorkloadTasks = this.driver.findElement(locatorMinimumWorkloadTasks);
		Assertions.assertEquals("0.10", minimumWorkloadTasks.getText());

		final By locatorMaximumWorkloadTasks;
		locatorMaximumWorkloadTasks = By.xpath("/html/body/div[2]/div/table/tbody/tr[12]/td");
		final WebElement maximumWorkloadTasks = this.driver.findElement(locatorMaximumWorkloadTasks);
		Assertions.assertEquals("11.25", maximumWorkloadTasks.getText());
		
		final By locatorRatioEntidad1Important;
		locatorRatioEntidad1Important = By.xpath("/html/body/div[2]/div/table/tbody/tr[13]/td");
		final WebElement ratioEntidad1Important = this.driver.findElement(locatorRatioEntidad1Important);
		Assertions.assertEquals("0.57", ratioEntidad1Important.getText());
		
		final By locatorRatioOfShoutsBudget0;
		locatorRatioOfShoutsBudget0 = By.xpath("/html/body/div[2]/div/table/tbody/tr[14]/td");
		final WebElement ratioOfShoutsBudget0 = this.driver.findElement(locatorRatioOfShoutsBudget0);
		Assertions.assertEquals("0.00", ratioOfShoutsBudget0.getText());
		
		final By locatorAverageCurrency1;
		locatorAverageCurrency1 = By.xpath("/html/body/div[2]/div/table/tbody/tr[15]/td");
		final WebElement averageCurrency1 = this.driver.findElement(locatorAverageCurrency1);
		Assertions.assertEquals("403.25", averageCurrency1.getText());
		
		final By locatorAverageCurrency2;
		locatorAverageCurrency2 = By.xpath("/html/body/div[2]/div/table/tbody/tr[16]/td");
		final WebElement averageCurrency2 = this.driver.findElement(locatorAverageCurrency2);
		Assertions.assertEquals("15.00", averageCurrency2.getText());
		
		final By locatorDeviationCurrency1;
		locatorDeviationCurrency1 = By.xpath("/html/body/div[2]/div/table/tbody/tr[17]/td");
		final WebElement deviationCurrency1 = this.driver.findElement(locatorDeviationCurrency1);
		Assertions.assertEquals("390.43", deviationCurrency1.getText());
		
		final By locatorDeviationCurrency2;
		locatorDeviationCurrency2 = By.xpath("/html/body/div[2]/div/table/tbody/tr[18]/td");
		final WebElement deviationCurrency2 = this.driver.findElement(locatorDeviationCurrency2);
		Assertions.assertEquals("10.80", deviationCurrency2.getText());
		

	}
	/*
	 * Testeamos que ni un manager ni un empleado
	 * puedan acceder a la dashboard 
	 * del administrador mediante su url.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/dashboard-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void managerDashboardNegative(final String user, final String password) {

		super.signIn(user, password);

		super.navigate("/administrator/dashboard/show",null);
		super.checkPanicExists();

		super.signOut();
	}

}
