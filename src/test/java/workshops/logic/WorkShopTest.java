package workshops.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import workshops.domain.Currency;
import workshops.domain.*;
import workshops.mock.HoldingMockGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
		long expectedResult = 4;

		//when
		long actualAmount = workShop.getAllUserAccountsAmount();

		//then
		assertEquals(expectedResult, actualAmount);
	}

	@Test
	void shouldReturnAllCurrenciesString() {
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
	void shouldReturnAmountOnAccountInPln() {
		//given
		Account account = WorkShopTestUtil.getAccount(1);
		BigDecimal expectedResult = account.amount().multiply(BigDecimal.valueOf(Currency.EUR.rate))
				.setScale(2, RoundingMode.HALF_UP);
		//when
		BigDecimal actualResult = workShop.getAccountAmountInPLN(account);

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnAmountOnAccountsListInPln() {
		//given
		List<Account> accounts = List.of(WorkShopTestUtil.getAccount(0));
		BigDecimal expectedResult = BigDecimal.valueOf(1).setScale(2, RoundingMode.HALF_UP);

		//when
		BigDecimal actualResult = workShop.getTotalCashInPLN(accounts);

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnUsersFirstNameForPredicate() {
		//given
		Set<String> expectedNames = Set.of("TestFirstName2", "TestFirstName3");
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
		List<String> expectedNames = List.of("TestFirstName2");

		//when
		List<String> actualNames = workShop.getOldWoman(age);

		//then
		assertEquals(expectedNames, actualNames);
	}

	@Test
	void shouldReturnRichestWoman() {
		//given
		Optional<User> expectedResult = Optional.of(WorkShopTestUtil.getUser(1));

		//when
		Optional<User> actualResult = workShop.getRichestWoman();

		//then
		assertEquals(expectedResult, actualResult);

	}

	@Test
	void shouldReturnFirstNCompanies() {
		//given
		int amount = 1;
		Set<String> expectedResult = Set.of(WorkShopTestUtil.getCompany(0).name());

		//when
		Set<String> actualResult = workShop.getFirstNCompany(amount);

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Disabled("Method does not work correctly in WorkShop.class")
	@Test
	void shouldThrowIllegalStateException() {
		//given
		AccountType customAccountType = AccountType.LO1;
		//when
		AccountType accountType = workShop.getMostPopularAccountType();
		//then
		assertEquals(customAccountType, accountType);
	}

	@Test
	void shouldThrowIllegalStateExceptionIfNoUserFound() {
		//given
		int age = 16;
		Predicate<User> condition = user -> user.age() == age;

		//then
		assertThrows(IllegalArgumentException.class, () -> workShop.getUser(condition));
	}

	@Test
	void shouldReturnUserForCondition() {
		//given
		String firstName = "TestFirstName1";
		Predicate<User> condition = user -> user.firstName().equals(firstName);
		User expectedUser = WorkShopTestUtil.getUser(0);

		//when
		User actualUser = workShop.getUser(condition);

		//then
		assertEquals(expectedUser, actualUser);
	}

	@Test
	void shouldReturnMapOfCompanyNamesWithUserList() {
		//given
		List<User> firstCompanyUsers = List.of(WorkShopTestUtil.getUser(0));
		List<User> secondCompanyUsers = List.of(WorkShopTestUtil.getUser(1));
		List<User> thirdCompanyUsers = List.of(WorkShopTestUtil.getUser(2));
		Map<String, List<User>> expectedResult = Map.of("TestCompany1", firstCompanyUsers
				, "TestCompany2", secondCompanyUsers,
				"TestCompany3", thirdCompanyUsers);

		//when
		Map<String, List<User>> actualResult = workShop.getUserPerCompany();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnMapOfCompanyNamesWithUserFirstAndLastName() {
		//given
		String firstUserData = "TestFirstName1 TestLastName1";
		String secondUserData = "TestFirstName2 TestLastName2";
		String thirdUserData = "TestFirstName3 TestLastName3";
		Map<String, List<String>> expectedResult = Map.of("TestCompany1", List.of(firstUserData)
				, "TestCompany2", List.of(secondUserData)
				, "TestCompany3", List.of(thirdUserData));

		//when
		Map<String, List<String>> actualResult = workShop.getUserPerCompanyAsString();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Disabled("dnot know how to finish code")
	@Test
	void shouldReturnMapOfCompanyNamesWithUserListUsingConverter() {
		//given
		Function<User, String> function = User::firstName;
		Function<User, String> spyFunction = spy(Function.class);
		List<String> firstCompanyUsers = List.of(WorkShopTestUtil.getUser(0).firstName());
		List<String> secondCompanyUsers = List.of(WorkShopTestUtil.getUser(1).firstName());
		List<String> thirdCompanyUsers = List.of(WorkShopTestUtil.getUser(2).firstName());
		final Map<String, List<String>> expectedResult = Map.of("TestCompany1", firstCompanyUsers
				, "TestCompany2", secondCompanyUsers,
				"TestCompany3", thirdCompanyUsers);

		//when
		Map<String, List<String>> actualResult = workShop.getUserPerCompany(function);

		//then
		assertEquals(expectedResult, actualResult);
		verify(spyFunction, atLeastOnce()).apply(any());
	}

	@Test
	void shouldReturnMapWithUserLastNameBySex() {
		//given
		Map<Boolean, Set<String>> expectedResult = Map.of(true, Set.of("TestLastName1"),
				false, Set.of("TestLastName2"));

		//when
		Map<Boolean, Set<String>> actualResult = workShop.getUserBySex();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnMapOfAccountNumberAsKeyAndAccountAsValue() {
		//given
		Map<String, Account> expectedResult = Map.of(
				"1", WorkShopTestUtil.getAccount(0),
				"2", WorkShopTestUtil.getAccount(1),
				"3", WorkShopTestUtil.getAccount(2),
				"4", WorkShopTestUtil.getAccount(3));

		//when
		Map<String, Account> actualResult = workShop.createAccountsMap();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnAllUsersNames() {
		//given
		String expectedResult = "TestFirstName1 TestFirstName2 TestFirstName3";

		//when
		String actualResult = workShop.getUserNames();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnAllUsersSet() {
		//given
		Set<User> expectedResult = Set.of(
				WorkShopTestUtil.getUser(0),
				WorkShopTestUtil.getUser(1),
				WorkShopTestUtil.getUser(2));

		//when
		Set<User> actualResult = workShop.getUsers();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnOptionalUserForCondition() {
		//given
		Predicate<User> condition = user -> user.sex().equals(Sex.OTHER);
		Optional<User> expectedUser = Optional.of(WorkShopTestUtil.getUser(2));

		//when
		Optional<User> actualUser = workShop.findUser(condition);

		//then
		assertEquals(expectedUser, actualUser);
	}

	@Test
	void shouldReturnUserAgeInFormat() {
		//given
		Optional<User> customUser = Optional.of(WorkShopTestUtil.getUser(0));
		String expectedResult = "TestFirstName1 TestLastName1 is 20 years old.";

		//when
		String actualResult = workShop.getAdultantStatus(customUser);

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnMoneyOnAccounts() {
		//given
		Map<AccountType, BigDecimal> expectedResult = Map.of(
				AccountType.LO2, BigDecimal.valueOf(10 * Currency.USD.rate).setScale(2, RoundingMode.HALF_UP),
				AccountType.RO2, BigDecimal.valueOf(10 * Currency.CHF.rate).setScale(2, RoundingMode.HALF_UP),
				AccountType.RO1, BigDecimal.valueOf(2 * Currency.EUR.rate).setScale(2, RoundingMode.HALF_UP),
				AccountType.LO1, BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP));

		//when
		Map<AccountType, BigDecimal> actualResult = workShop.getMoneyOnAccounts();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnAgeSquaresSum() {
		//given
		int expectedResult = 2900;

		//when
		int actualResult = workShop.getAgeSquaresSum();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Disabled("Method in Workshops is not working")
	@Test
	void shouldReturnSpecificNumberOfRandomUsers() {
		//given
		int usersCount = 2;
		List<User> expectedResult = List.of(mock(User.class), mock(User.class));

		//when
		List<User> actualResult = workShop.getRandomUsers(usersCount);

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnRuntimeExceptionIfAmountOfUsersIsTooBig() {
		//given
		int userCount = 60;

		//then
		assertThrows(RuntimeException.class, () -> workShop.getRandomUsers(userCount));
	}

	@Test
	void shouldReturnAllCurrenciesSet() {
		//given
		Set<Currency> expectedResult = Set.of(Currency.CHF, Currency.PLN, Currency.USD, Currency.EUR);

		//when
		Set<Currency> actualResult = workShop.getCurenciesSet();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnManWithSumMoneyOnAccountsForCompany() {
		//given
		Company customCompany = WorkShopTestUtil.getCompany(0);
		Map<User, BigDecimal> expectedResult = Map.of(WorkShopTestUtil.getUser(0), BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP));

		//when
		Map<User, BigDecimal> actualResult = workShop.manWithSumMoneyOnAccounts(customCompany);

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldReturnSumAmountInPlnForUser() {
		//given
		User customUser = WorkShopTestUtil.getUser(1);
		BigDecimal expectedAmount = BigDecimal.valueOf(2 * Currency.EUR.rate).setScale(2, RoundingMode.HALF_UP).add(BigDecimal.valueOf(10 * Currency.CHF.rate).setScale(2, RoundingMode.HALF_UP));

		//when
		BigDecimal actualAmount = workShop.getSumUserAmountInPLN(customUser);

		//then
		assertEquals(expectedAmount, actualAmount);
	}

	@Test
	void shouldReturnSumAmountInPlnOnAccountsForPeopleOther() {
		//given
		BigDecimal expectedResult = BigDecimal.valueOf(10 * Currency.USD.rate).setScale(2, RoundingMode.HALF_UP);

		//when
		BigDecimal actualResult = workShop.getSumMoneyOnAccountsForPeopleOtherInPLN();

		//then
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void shouldDivideUsersAmountIntoAdultsAndNonAdults() {
		//given
		Map<Boolean, Long> expectedResult = Map.of(true, 3L);

		//when
		Map<Boolean, Long> actualResult = workShop.divideIntoAdultsAndNonAdults();

		//then
		assertEquals(expectedResult, actualResult);
	}
}