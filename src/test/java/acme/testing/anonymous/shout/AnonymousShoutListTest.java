package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeEndeavoursTest;

public class AnonymousShoutListTest extends AcmeEndeavoursTest{

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void ListRecent(final int recordIndex,final String moment, final String author, final String text, final String info, final String atributo1, final String atributo2, final String atributo3, final String atributo4) {
		
		// Accedemos como anonimo
		super.clickOnMenu("Anonymous", "List recent shouts");
		// Comprobación de columna
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		super.checkColumnHasValue(recordIndex, 3, info);
		super.checkColumnHasValue(recordIndex, 4, atributo1);
		super.checkColumnHasValue(recordIndex, 5, atributo2);
		super.checkColumnHasValue(recordIndex, 6, atributo3);
		super.checkColumnHasValue(recordIndex, 7, atributo4);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/dashboard-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAnonymousNegative(final String user, final String password) {

		super.signIn(user, password);

		this.driver.get("localhost:8080/Acme-Planner/anonymous/shout/list-recent");
		super.checkNotPanicExists();

		super.signOut();
	}
	
}
