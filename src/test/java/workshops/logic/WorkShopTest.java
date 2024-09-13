package workshops.logic;

import org.junit.jupiter.api.*;
import workshops.domain.Currency;

import java.util.LinkedList;
import java.util.List;
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
    void ShouldReturnWomanAmount(){
        //given
        long expectedAmount = 4;

        //when
        long actualAmount = workShop.getWomanAmount();

        //then
        assertEquals(expectedAmount, actualAmount);
    }
}
