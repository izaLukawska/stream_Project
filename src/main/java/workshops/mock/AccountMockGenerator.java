package workshops.mock;

import workshops.domain.Account;
import workshops.domain.AccountType;
import workshops.domain.Currency;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AccountMockGenerator implements IGenerator {

  public List<Account> generate() {
	return Arrays.asList(
			//1
			new Account(AccountType.LO1, "6754", new BigDecimal("10.50"), Currency.PLN),
			//2
			new Account(AccountType.ROR1, "1178", new BigDecimal("999.50"), Currency.USD),
			//3
			new Account(AccountType.ROR1, "8967", new BigDecimal("1000"), Currency.EUR),
			//4
			new Account(AccountType.ROR1, "112221", new BigDecimal("1667.22"), Currency.PLN),
			//5
			new Account(AccountType.ROR1, "1234", new BigDecimal("1888822.1"), Currency.EUR),
			//6
			new Account(AccountType.ROR1, "2346", new BigDecimal("8236626.12"), Currency.PLN),
			//7
			new Account(AccountType.ROR1, "7676", new BigDecimal("1230.00"), Currency.EUR),
			//8
			new Account(AccountType.ROR1, "0192", new BigDecimal("88890.00"), Currency.PLN),
			//9
			new Account(AccountType.ROR1, "8474", new BigDecimal("10000.60"), Currency.CHF),
			//10
			new Account(AccountType.RO1, "3892", new BigDecimal("70998.8"), Currency.EUR),
			//11
			new Account(AccountType.RO1, "65423", new BigDecimal("800.99"), Currency.PLN),
			//12
			new Account(AccountType.RO2, "87631", new BigDecimal("100"), Currency.CHF),
			//13
			new Account(AccountType.LO1, "1235478", new BigDecimal("1"), Currency.PLN),
			//14
			new Account(AccountType.RO1, "72446", new BigDecimal("0.01"), Currency.CHF),
			//15
			new Account(AccountType.RO1, "90753", new BigDecimal("0"), Currency.CHF),
			//16
			new Account(AccountType.RO2, "865423", new BigDecimal("0"), Currency.CHF),
			//17
			new Account(AccountType.LO2, "9612541", new BigDecimal("23500.86"), Currency.USD),
			//18
			new Account(AccountType.RO1, "971561", new BigDecimal("9999"), Currency.USD),
			//19
			new Account(AccountType.LO2, "97156221", new BigDecimal("10.00"), Currency.CHF),
			//20
			new Account(AccountType.RO1, "867151", new BigDecimal("109823.00"), Currency.PLN),
			//21
			new Account(AccountType.RO1, "862252", new BigDecimal("123771"), Currency.CHF),
			//22
			new Account(AccountType.RO2, "872562", new BigDecimal("1234"), Currency.PLN),
			//23
			new Account(AccountType.LO1, "86252", new BigDecimal("7332"), Currency.PLN),
			//24
			new Account(AccountType.RO2, "34563", new BigDecimal("2346"), Currency.PLN),
			//25
			new Account(AccountType.RO2, "1122", new BigDecimal("100"), Currency.USD),
			//26
			new Account(AccountType.RO1, "62222", new BigDecimal("1009"), Currency.PLN),
			//27
			new Account(AccountType.RO2, "73344", new BigDecimal("0"), Currency.PLN),
			//28
			new Account(AccountType.LO1, "8723212", new BigDecimal("2435"), Currency.USD),
			//29
			new Account(AccountType.RO2, "3457117", new BigDecimal("10000984"), Currency.PLN),
			//30
			new Account(AccountType.ROR2, "45218", new BigDecimal("108987.0"), Currency.CHF),
			//31
			new Account(AccountType.LO1, "24578", new BigDecimal("13873"), Currency.CHF),
			//32
			new Account(AccountType.LO1, "0000064", new BigDecimal("9766"), Currency.USD),
			//33
			new Account(AccountType.ROR2, "2322255", new BigDecimal("1000"), Currency.CHF),
			//34
			new Account(AccountType.ROR2, "666622", new BigDecimal("287"), Currency.CHF),
			//35
			new Account(AccountType.ROR2, "998292", new BigDecimal("1467"), Currency.CHF),
			//36
			new Account(AccountType.LO1, "938383", new BigDecimal("1600"), Currency.EUR),
			//37
			new Account(AccountType.LO1, "2018543", new BigDecimal("23000.86"), Currency.PLN));
  }
}