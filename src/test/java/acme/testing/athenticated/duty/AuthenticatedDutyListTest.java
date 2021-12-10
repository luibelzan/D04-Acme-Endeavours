package acme.testing.athenticated.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AuthenticatedDutyListTest extends AcmeEndeavoursTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/duty/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listPublicFinishedDuties(final int recordIndex, final String periodInitial, final String periodFinal, final String title, final String description, final String workload, final String link) {

		// Accedemos como manager
		super.signIn("officer1", "officer1");

		// Accedemos a la lista de sus tareas
		super.clickOnMenu("Authenticated", "List public and finished duties");

		// Comprobamos cada columna
		super.checkColumnHasValue(recordIndex, 0, periodInitial);
		super.checkColumnHasValue(recordIndex, 1, periodFinal);
		super.checkColumnHasValue(recordIndex, 2, title);
		super.checkColumnHasValue(recordIndex, 3, description);

		// Accedemos a un registro
		super.clickOnListingRecord(recordIndex);

		// Comprobamos cada valor
		super.checkInputBoxHasValue("periodInitial", periodInitial);
		super.checkInputBoxHasValue("periodFinal", periodFinal);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("workloadInHours", workload);
		super.checkInputBoxHasValue("link", link);

		// Cerramos sesion
		super.signOut();

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/duty/list-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listNegativeAuthenticated(final String user, final String password) {

		this.driver.get("localhost:8080/Acme-Planner/authenticated/duty/list-public-finished");
		
		super.checkNotPanicExists();

	}

}
