package acme.testing.officer.duty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class OfficerDutyDeleteTest extends AcmeEndeavoursTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/delete.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteDutyPositive(final int recordIndex, final String title, final String description, final String periodInitial, final String periodFinal, final String workloadInHours, final String link, final Boolean isPublic) {

		// Se logea con el usuario manager
		super.signIn("officer1", "officer1");

		// Clica en el menú para acceder a las tareas del manager
		super.clickOnMenu("Officer", "List duties");

		// Checkea que se cumple que las columnas coinciden con los valores que indicamos
		super.checkColumnHasValue(recordIndex, 0, periodInitial);
		super.checkColumnHasValue(recordIndex, 1, periodFinal);
		super.checkColumnHasValue(recordIndex, 2, title);

		// Recorre cada uno de los valores
		super.clickOnListingRecord(recordIndex);

		//Comprobar el valor del atributo
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("periodInitial", periodInitial);
		super.checkInputBoxHasValue("periodFinal", periodFinal);
		super.checkInputBoxHasValue("workloadInHours", workloadInHours);
		super.checkInputBoxHasValue("link", link);
		super.clickOnSubmitButton("Delete");
		
		super.signOut();

	}
	
	
	//Test delete negativo de anonymous
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/deleteNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteTasksNegativeAnonymous(final int recordIndex, final String title, final String description, final String periodInitial, final String periodFinal, final String workloadInHours, final String link, final Boolean isPublic) {

		// Clica en el menú para acceder a las tareas del manager
		this.driver.get("localhost:8080/Acme-Endeavours/officer/duty/delete?id=20");
		
		super.checkPanicExists();

	}
	
	//Test delete negativo de administrador
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/deleteNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteTasksNegativeAdministrator(final int recordIndex, final String title, final String description, final String periodInitial, final String periodFinal, final String workloadInHours, final String link, final Boolean isPublic) {

		// Se logea con el usuario manager
		super.signIn("administrator", "administrator");

		// Clica en el menú para acceder a las tareas del manager
		this.driver.get("localhost:8080/Acme-Endeavours/officer/duty/delete?id=20");
		
		super.checkPanicExists();
		
		super.signOut();

	}

	
	@ParameterizedTest
	@CsvFileSource(resources = "/officer/duty/deleteNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deleteTasksNegativeOfficer(final int recordIndex, final String title, final String description, final String periodInitial, final String periodFinal, final String workloadInHours, final String link, final Boolean isPublic) {

		// Se logea con el usuario manager
		super.signIn("officer1", "officer1");

		// Clica en el menú para acceder a las tareas del manager
		this.driver.get("localhost:8080/Acme-Endeavours/officer/duty/delete?id=100");
		
		super.checkPanicExists();
		
		super.signOut();
	}

}
