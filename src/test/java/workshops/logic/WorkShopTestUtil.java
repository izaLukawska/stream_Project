package workshops.logic;

import workshops.domain.*;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;

final class WorkShopTestUtil {
	private WorkShopTestUtil() {}

	static List<Holding> getFakeData() {
		var account2 = new Account(mock(AccountType.class), "2", BigDecimal.TWO, Currency.EUR);
		Account account = new Account(mock(AccountType.class), "1", BigDecimal.ONE, Currency.PLN);
		var account3 = new Account(mock(AccountType.class), "3", BigDecimal.TEN, Currency.CHF);
		var account4 = new Account(mock(AccountType.class), "4", BigDecimal.TEN, Currency.USD);

		var user1 = new User("TestName1", "TestLastName1", Sex.MAN, 20, List.of(account), List.of());
		var user2 = new User("TestName2", "TestLastName2", Sex.WOMAN, 30, List.of(account2, account3), List.of());
		var user3 = new User("TestName3", "TestLastName3", Sex.OTHER, 40, List.of(account4), List.of());

		var company1 = new Company("TestCompany1", List.of(user1));
		var company2 = new Company("TestCompany2", List.of(user2));
		var company3 = new Company("TestCompany3", List.of(user3));

		var holding1 = new Holding("TestHolding1", List.of(company1));
		var holding2 = new Holding("TestHolding2", List.of(company2, company3));
		var holding3 = new Holding("TestHolding3", List.of());

		return List.of(holding1, holding2, holding3);
	}
}
