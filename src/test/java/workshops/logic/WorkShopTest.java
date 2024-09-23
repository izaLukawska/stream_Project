package workshops.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import workshops.domain.Currency;
import workshops.domain.User;
import workshops.mock.HoldingMockGenerator;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkShopTest {

	private WorkShop workShop;
	@Mock
	private HoldingMockGenerator holdingMockGenerator;

	@BeforeEach
	void setUp() {
		when(holdingMockGenerator.generate()).thenReturn(WorkShopTestUtil.getFakeData());
		workShop = new WorkShop(holdingMockGenerator);
	}

	@Test
	void shouldReturnListOfHoldingsOnlyWithCompanies() {
		//given
		long expectedSize = 2;

		//when
		long actualSize = workShop.getHoldingsWhereAreCompanies();

		//then
		assertEquals(expectedSize, actualSize);
	}

	@Test
	void shouldReturnAllHoldingNamesInListOfStrings() {
		//given
		List<String> expectedList = List.of("testholding1", "testholding2", "testholding3");

		//when
		List<String> actualList = workShop.getHoldingNames();

		//then
		assertEquals(expectedList, actualList);
	}

	@Test
	void shouldReturnAllHoldingNamesAsString() {
		//given
		String expectedResult = ("(TestHolding1, TestHolding2, TestHolding3)");

		//when
		String actualResult = workShop.getHoldingNamesAsString();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnAllCompaniesAmount() {
		//given
		long expectedAmount = 3;

		//when
		long actualAmount = workShop.getCompaniesAmount();

		//then
		assertEquals(expectedAmount, actualAmount);
	}

	@Test
	void shouldReturnAllUsersAmount() {
		//given
		long expectedAmount = 3;

		//when
		long actualAmount = workShop.getAllUserAmount();

		//then
		assertEquals(expectedAmount, actualAmount);
	}

	@Test
	void shouldReturnListOfAllCompaniesName() {
		//given
		List<String> expectedCompaniesName = List.of("TestCompany1", "TestCompany2", "TestCompany3");

		//when
		List<String> actualCompaniesNames = workShop.getAllCompaniesNames();

		//then
		assertEquals(expectedCompaniesName, actualCompaniesNames);
	}

	@Test
	void shouldReturnLinkedListOfAllCompaniesName() {
		//given
		LinkedList<String> expectedCompaniesName = new LinkedList<>(List.of("TestCompany1", "TestCompany2", "TestCompany3"));

		//when
		LinkedList<String> actualCompaniesNames = workShop.getAllCompaniesNamesAsLinkedList();

		//then
		assertEquals(expectedCompaniesName, actualCompaniesNames);
	}

	@Test
	void shouldReturnAllCompaniesNameAsString() {
		//given
		String expectedResult = "TestCompany1+TestCompany2+TestCompany3";

		//when
		String actualResult = workShop.getAllCompaniesNamesAsString();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnAllCompaniesNamesAsStringUsingStringBuilder() {
		//given
		StringBuilder stringBuilder = spy(StringBuilder.class);
		String expectedResult = "TestCompany1+TestCompany2+TestCompany3";

		//when
		String actualResult = workShop.getAllCompaniesNamesAsStringUsingStringBuilder(stringBuilder);

		//then
		assertEquals(expectedResult, actualResult);
		verify(stringBuilder, atLeastOnce()).append(anyString());
	}

	@Test
	void shouldReturnAllUsersAccountAmount() {
		//given
		long expectedResult = 3;

		//when
		long actualAmount = workShop.getAllUserAccountsAmount();

		//then
		assertEquals(expectedResult, actualAmount);
	}

	@Test
	void shouldReturnAllCurrencies() {
		//given
		String expectedResult = "CHF EUR PLN USD";

		//when
		String actualResult = workShop.getAllCurrencies();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnListOfAllCurrencies() {
		//given
		List<Currency> expectedResult = List.of(Currency.CHF, Currency.EUR, Currency.PLN, Currency.USD);

		//when
		List<Currency> actualResult = workShop.getCurrencyList();

		//then
		assertEquals(expectedResult, actualResult);
	}


	@Test
	void shouldReturnAllWomenAmount() {
		//given
		long expectedResult = 1;

		//when
		long actualResult = workShop.getWomanAmount();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnUsersFirstNameForPredicate() {
		//given
		Set<String> expectedNames = Set.of("TestName2", "TestName3");
		Predicate<User> condition = user -> user.age() > 25;

		//when
		Set<String> actualNames = workShop.getUsersForPredicate(condition);

		//then
		assertEquals(expectedNames, actualNames);
	}

	@Test
	void shouldReturnListOfWomanOlderThanAge() {
		//given
		int age = 25;
		List<String> expectedNames = List.of("TestName2");

		//when
		List<String> actualNames = workShop.getOldWoman(age);

		//then
		assertEquals(expectedNames, actualNames);
	}
}