package acme.testing.officer.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class OfficerDutyListTest extends AcmeEndeavoursTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listDutyPositive(final int recordIndex, final String title, final String description, final String periodInitial, final String periodFinal, final String workloadInHours, final String Link) {

		// Accedemos como manager
		super.signIn("officer1", "officer1");

		// Accedemos a la vista de sus tareas
		super.clickOnMenu("Officer", "List duties");

		// Comprobamos cada columna
		super.checkColumnHasValue(recordIndex, 0, periodInitial);
		super.checkColumnHasValue(recordIndex, 1, periodFinal);
		super.checkColumnHasValue(recordIndex, 2, title);

		// Accedemos a un registro
		super.clickOnListingRecord(recordIndex);

		// Comprobamos cada valor
		super.checkInputBoxHasValue("periodInitial", periodInitial);
		super.checkInputBoxHasValue("periodFinal", periodFinal);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("workloadInHours", workloadInHours);
		super.checkInputBoxHasValue("link", Link);

		// Cerramos sesion
		super.signOut();
	}

	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/list-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listDutyNegative(final String user, final String password) {

		super.signIn(user, password);

		this.driver.get("localhost:8080/Acme-Endeavours/officer/duty/list");
		super.checkPanicExists();

		super.signOut();
	}

}
