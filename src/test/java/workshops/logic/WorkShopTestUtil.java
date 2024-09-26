package workshops.logic;

import workshops.domain.*;

import java.math.BigDecimal;
import java.util.List;

final class WorkShopTestUtil {
	private WorkShopTestUtil() {}

	static Account account1 = new Account(AccountType.LO1, "1", BigDecimal.ONE, Currency.PLN);
	static Account account2 = new Account(AccountType.RO1, "2", BigDecimal.TWO, Currency.EUR);
	static Account account3 = new Account(AccountType.RO2, "3", BigDecimal.TEN, Currency.CHF);
	static Account account4 = new Account(AccountType.LO2, "4", BigDecimal.TEN, Currency.USD);
	static User user1 = new User("TestFirstName1", "TestLastName1", Sex.MAN, 20, List.of(account1), List.of(Permit.DEPOSIT));
	static User user2 = new User("TestFirstName2", "TestLastName2", Sex.WOMAN, 30, List.of(account2, account3), List.of(Permit.LOAN));
	static User user3 = new User("TestFirstName3", "TestLastName3", Sex.OTHER, 40, List.of(account4), List.of(Permit.ORDER_HISTORY));
	static Company company1 = new Company("TestCompany1", List.of(user1));
	static Company company2 = new Company("TestCompany2", List.of(user2));
	static Company company3 = new Company("TestCompany3", List.of(user3));

	static List<Holding> getFakeData() {
		Holding holding1 = new Holding("TestHolding1", List.of(company1));
		Holding holding2 = new Holding("TestHolding2", List.of(company2, company3));
		Holding holding3 = new Holding("TestHolding3", List.of());

		return List.of(holding1, holding2, holding3);
	}

	static List<User> getUsers() {
		return List.of(user1, user2, user3);
	}

	static List<Account> getAccounts() {
		return List.of(account1, account2, account3, account4);
	}
}
