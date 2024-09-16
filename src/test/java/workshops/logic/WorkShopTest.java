package workshops.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import workshops.domain.Company;
import workshops.domain.Holding;
import workshops.mock.HoldingMockGenerator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkShopTest {

    //@InjectMocks
    private WorkShop workShop;
    @Mock
    private HoldingMockGenerator holdingMockGenerator;

    @BeforeEach
    void setUp(){
        Holding holding1 = new Holding("TestHolding", List.of(mock(Company.class)));
        Holding holding2 = new Holding("TestHolding2", List.of(mock(Company.class)));
        Holding holding3 = new Holding("TestHolding3", List.of());
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
        List<String> expectedList = List.of("testholding","testholding2", "testholding3");

        //when
        List<String> actualList = workShop.getHoldingNames();

        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void shouldReturnAllHoldingNamesAsString(){
        //given
        String expectedResult = ("(TestHolding, TestHolding2, TestHolding3)");

        //when
        String actualResult = workShop.getHoldingNamesAsString();

        //then
        assertEquals(expectedResult, actualResult);
    }
}
