package workshops.logic;

import org.junit.jupiter.api.*;
import workshops.domain.Account;
import workshops.domain.AccountType;
import workshops.domain.Currency;
import workshops.domain.User;
import workshops.mock.AccountMockGenerator;
import workshops.mock.UserMockGenerator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class WorkShopTest {

    private final WorkShop workShop = new WorkShop();

    @Test
    void shouldReturnCountOfOnlyHoldingsThatIncludeCompanies(){
        //given
        long expectedResult = 3;

        //when
        long actualResult = workShop.getHoldingsWhereAreCompanies();

        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnAllHoldingNamesInListOfStrings(){
        //given
        int expectedSize = 3;

        //when
        List<String> holdings = workShop.getHoldingNames();

        //then
        assertAll(() -> {
            assertFalse(holdings.isEmpty());
            assertEquals(expectedSize, holdings.size());
        });

    }

    @Test
    void shouldReturnAllHoldingNamesInStringPattern(){
        //given
        Pattern pattern = Pattern.compile("\\(\\s*\\w+(-\\w+)?(\\s*,\\s*\\w+(-\\w+)?)*\\s*\\)");

        //when
        String holdingNamesResult = workShop.getHoldingNamesAsString();
        Matcher matcher = pattern.matcher(holdingNamesResult);

        //then
        assertTrue(matcher.matches());
    }

    @Test
    void shouldReturnAllCompaniesAmount(){
        //given
        long expectedAmount = 8;

        //when
        long actualAmount = workShop.getCompaniesAmount();

        //then
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldReturnAllUsersAmount(){
        //given
        long expectedAmount = 20;

        //when
        long actualAmount = workShop.getAllUserAmount();

        //then
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldReturnAllCompaniesNamesInListOfStrings(){
        //given
        int expectedSize = 8;

        //when
        List<String> result = workShop.getAllCompaniesNames();

        //then
        assertAll(() -> {
            assertEquals(expectedSize, result.size());
            assertFalse(result.isEmpty());
        });
    }

    @Test
    void shouldReturnAllCompaniesNamesInLinkedListOfStrings(){
        //given
        int expectedSize = 8;

        //when
        LinkedList<String> result = workShop.getAllCompaniesNamesAsLinkedList();

        //then
        assertAll(() -> {
            assertEquals(expectedSize, result.size());
            assertFalse(result.isEmpty());
        });
    }

    @Test
    void shouldReturnAllCompaniesNamesInStringPattern(){
        //given
        Pattern pattern = Pattern.compile("[^-+]+(\\s*[-+\\s]*[^-+]+)*");

        //when
        String companies = workShop.getAllCompaniesNamesAsString();
        Matcher matcher = pattern.matcher(companies);

        //then
        assertAll(() -> {
            assertFalse(companies.isEmpty());
            assertTrue(matcher.matches());
        });
    }

    @Disabled
    @Test
    void shouldReturnAllCompaniesNamesInStringUsingStringBuilder(){
        //given

        //when

        //then
    }
    @Test
    void ShouldReturnAllUserAccountAmount(){
        //given
        long expectedAmount = 35;

        //when
        long actualAmount = workShop.getAllUserAccountsAmount();

        //then
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldReturnAllCurrenciesInAStringPattern(){
        //given
        Pattern pattern = Pattern.compile("\\w+( \\w+)*");

        //when
        String result = workShop.getAllCurrencies();
        Matcher matcher = pattern.matcher(result);

        //then
        assertAll(() ->{
            assertFalse(result.isEmpty());
            assertTrue(matcher.matches());
        });
    }

    @Test
    void shouldReturnCurrencyList(){
        //given
        int expectedSize = 4;

        //when
        List<Currency> currencyList = workShop.getCurrencyList();

        //then
        assertAll(() -> {
            assertEquals(expectedSize, currencyList.size());
            assertFalse(currencyList.isEmpty());
        });
    }

    @Disabled
    @Test
    void shouldReturnAllCurrenciesStringUsingGenerateMethod(){
        //given

        //when

        //then
    }

    @Test
    void ShouldReturn4Women(){
        //given
        long expectedAmount = 4;

        //when
        long actualAmount = workShop.getWomanAmount();

        //then
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void shouldReturnAccountAmountInPln(){
        //given
        AccountMockGenerator accountMockGenerator = new AccountMockGenerator();
        List<Account> accountsList = accountMockGenerator.generate();
        var account = accountsList.get(2);
        BigDecimal expectedResult = BigDecimal.valueOf(1000 * 4.23).setScale(2, RoundingMode.HALF_UP);

        //when
        BigDecimal result = workShop.getAccountAmountInPLN(account);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnTotalAmountInAllAccountsInPLN(){
        //given
        List<Account> accountsList = new AccountMockGenerator().generate();
        BigDecimal expectedResult = BigDecimal.valueOf(27947933.5225).setScale(2, RoundingMode.HALF_UP);

        //when
        BigDecimal result = workShop.getTotalCashInPLN(accountsList);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnUsersFirstNameOlderThanAge(){
        //given
        Predicate<User> predicate = user -> (user.age() > 30);
        int expectedSize = 10;

        //when
        int result = workShop.getUsersForPredicate(predicate).size();

        //then
        assertEquals(expectedSize, result);
    }

    @Test
    void shouldReturnWomenFirstNameOlderThanAgeList(){
        //given
        int age = 30;
        int expectedSize = 3;

        //when
        int result = workShop.getOldWoman(age).size();

        //then
        assertEquals(expectedSize, result);
    }

    @Disabled("Don't know how to validate that this method is being used")

    @Test
    void shouldExecuteMethodForEachCompany(){
        //given

        //when

        //then
    }

    @Test
    void shouldReturnRichestWoman(){
        //given
        var expectedResult =  new UserMockGenerator().generate().get(4);

        //when
        var result = workShop.getRichestWoman();

        //then
        assertAll(() ->{
            assertTrue(result.isPresent());
            assertEquals(expectedResult, result.get());
        });
    }

    @Test
    void shouldReturnFirstNCompanies(){
        //given
        int expectedSize = 3;
        int amount = 3;

        //when
        int result = workShop.getFirstNCompany(amount).size();

        //then
        assertEquals(expectedSize,result);
    }

    @Disabled
    @Test
    void shouldReturnMostPopularAccountType(){
        //given
        var accountType = AccountType.RO1;

        //when
        var expectedAccountType = workShop.getMostPopularAccountType();

        //then
        assertEquals(accountType, expectedAccountType);
    }

    @Disabled
    @Test
    void shouldThrowIllegalStateExceptionIfNoAccountTypeFound(){

    }


    //What if there are many users??
    @Test
    void shouldReturnUserForPredicate(){
        //given
        var expectedUser = new UserMockGenerator().generate().get(0);
        Predicate<User> predicate = user -> (user.age() == 17);

        //when
        var result = workShop.getUser(predicate);

        //then
        assertEquals(expectedUser, result);
    }

    @Disabled
    @Test
    void shouldReturnFirmMapWithUsersPerCompany(){
    }

    @Disabled
    @Test
    void shouldReturnFirmMapWithUsersFirstNameAndLastNamePerCompany(){
    }

    @Disabled
    @Test
    void shouldReturnFirmMapWithUsersPerCompanyAsTObjectType(){
    }
}
