package workshops.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import workshops.domain.*;
import workshops.mock.HoldingMockGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkShopTest {

    private WorkShop workShop;
    @Mock
    private HoldingMockGenerator holdingMockGenerator;
    private Account account1 = new Account(mock(AccountType.class), "1", BigDecimal.ONE, Currency.PLN);
    private Account account2 = new Account(mock(AccountType.class), "2", BigDecimal.TWO, Currency.PLN);
    private Account account3 = new Account(mock(AccountType.class), "3", BigDecimal.valueOf(3.50), Currency.EUR);

    @BeforeEach
    void setUp(){
        var user1 = new User("TestName1", "TestLastName1", Sex.MAN, 20, List.of(account1), List.of());
        var user2 = new User("TestName2", "TestLastName2", Sex.WOMAN, 30, List.of(account2, account3), List.of());
        var user3 = new User("TestName3", "TestLastName3", Sex.OTHER, 40, List.of(), List.of());

        var company1 = new Company("TestCompany1", List.of(user1));
        var company2 = new Company("TestCompany2", List.of(user2));
        var company3 = new Company("TestCompany3", List.of(user3));

        var holding1 = new Holding("TestHolding1", List.of(company1));
        var holding2 = new Holding("TestHolding2", List.of(company2, company3));
        var holding3 = new Holding("TestHolding3", List.of());
        List<Holding> holdings = List.of(holding1, holding2, holding3);
        when(holdingMockGenerator.generate()).thenReturn(holdings);
        workShop = new WorkShop(holdingMockGenerator);
    }

    @Test
    void shouldReturnListOfHoldingsOnlyWithCompanies(){
        //given
        long expectedSize = 2;

        //when
        long actualSize = workShop.getHoldingsWhereAreCompanies();

        //then
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void shouldReturnAllHoldingNamesInListOfStrings(){
        //given
        List<String> expectedList = List.of("testholding1","testholding2", "testholding3");

        //when
        List<String> actualList = workShop.getHoldingNames();

        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void shouldReturnAllHoldingNamesAsString(){
        //given
        String expectedResult = ("(TestHolding1, TestHolding2, TestHolding3)");

        //when
        String actualResult = workShop.getHoldingNamesAsString();

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnAllCompaniesAmount(){
        //given
        long expectedAmount = 3;

        //when
        long actualAmount = workShop.getCompaniesAmount();

        //then
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldReturnAllUsersAmount(){
        //given
        long expectedAmount = 3;

        //when
        long actualAmount = workShop.getAllUserAmount();

        //then
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldReturnListOfAllCompaniesName(){
        //given
        List<String> expectedCompaniesName = List.of("TestCompany1","TestCompany2", "TestCompany3");

        //when
        List<String> actualCompaniesNames = workShop.getAllCompaniesNames();

        //then
        assertEquals(expectedCompaniesName, actualCompaniesNames);
    }

    @Test
    void shouldReturnLinkedListOfAllCompaniesName(){
        //given
        LinkedList<String> expectedCompaniesName = new LinkedList<>(List.of("TestCompany1","TestCompany2", "TestCompany3"));

        //when
        LinkedList<String> actualCompaniesNames = workShop.getAllCompaniesNamesAsLinkedList();

        //then
        assertEquals(expectedCompaniesName, actualCompaniesNames);
    }

    @Test
    void shouldReturnAllCompaniesNameAsString(){
        //given
        String expectedResult = "TestCompany1+TestCompany2+TestCompany3";

        //when
        String actualResult = workShop.getAllCompaniesNamesAsString();

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Disabled("Don't know how to check it method uses StringBuilder")
    @Test
    void shouldReturnAllCompaniesNamesAsStringUsingStringBuilder(){
        //given
        String expectedResult = "TestCompany1+TestCompany2+TestCompany3";

        //when
        String actualResult = workShop.getAllCompaniesNamesAsStringUsingStringBuilder();

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnAllUsersAccountAmount(){
        //given
        long expectedResult = 3;

        //when
        long actualAmount = workShop.getAllUserAccountsAmount();

        //then
        assertEquals(expectedResult, actualAmount);
    }

    @Test
    void shouldReturnAllCurrencies(){
        //given
        String expectedResult = "EUR PLN";

        //when
        String actualResult = workShop.getAllCurrencies();

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnListOfAllCurrencies(){
        //given
        List<Currency> expectedResult = List.of(Currency.EUR, Currency.PLN);

        //when
        List<Currency> actualResult = workShop.getCurrencyList();

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Disabled("Don't know how to check it generate is being used")
    @Test
    void shouldReturnAllCurrenciesUsingGenerate(){
    }

    @Test
    void shouldReturnAllWomenAmount(){
        //given
        long expectedResult = 1;

        //when
        long actualResult = workShop.getWomanAmount();

        //then
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void shouldReturnAccountAmountInPln(){
        //given
        BigDecimal expectedResult =  BigDecimal.valueOf(4.23 * 3.50).setScale(2, RoundingMode.HALF_UP);

        //when
        BigDecimal actualResult = workShop.getAccountAmountInPLN(account3);

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnTotalAmountInPlnOnAllAccounts(){
        //given
        List<Account> accounts = List.of(account1, account2, account3);
        BigDecimal expectedResult = BigDecimal.valueOf((4.23 * 3.50) + 3).setScale(2, RoundingMode.HALF_UP);

        ///when
        BigDecimal actualResult = workShop.getTotalCashInPLN(accounts);

        //then
        assertEquals(expectedResult, actualResult);
    }
}
