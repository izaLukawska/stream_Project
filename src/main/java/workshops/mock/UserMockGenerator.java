package workshops.mock;

import workshops.domain.Account;
import workshops.domain.Permit;
import workshops.domain.Sex;
import workshops.domain.User;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserMockGenerator implements IGenerator {
    private AccountMockGenerator accountMockGenerator = new AccountMockGenerator();

    public List<User> generate() {
        final List<Account> accounts = accountMockGenerator.generate();
        final List<User> result = new LinkedList<>();

        result.add( //1
                new User("Adam", "Wojcik", Sex.MAN, 17,
                        Arrays.asList(accounts.get(0), accounts.get(1)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER)));

        result.add( //2
                new User("Mateusz", "Kowalski", Sex.MAN, 33,
                        Arrays.asList(accounts.get(2)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN))
        );

        result.add( //3
                new User("Bartek", "Pasibrzuch", Sex.OTHER, 18,
                        Arrays.asList(accounts.get(3)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //4
                new User("Jan", "Bazuka", Sex.MAN, 46,
                        Arrays.asList(accounts.get(4), accounts.get(5), accounts.get(6)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN))
        );

        result.add( //5
                new User("Zosia", "Psikuta", Sex.WOMAN, 67,
                        Arrays.asList(accounts.get(7), accounts.get(8), accounts.get(9)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //6
                new User("Magdalena", "Warszawska", Sex.WOMAN, 33,
                        Arrays.asList(accounts.get(10), accounts.get(11)),
                        Arrays.asList(Permit.DEPOSIT, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //7
                new User("Amadeusz", "Mocarz", Sex.MAN, 29,
                        Arrays.asList(accounts.get(12)),
                        Arrays.asList(Permit.ORDER_HISTORY))
        );

        result.add( //8
                new User("Filip", "Flirciczart", Sex.MAN, 33,
                        Arrays.asList(accounts.get(13)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //9
                new User("Zenon", "Kucowski", Sex.OTHER, 21,
                        Arrays.asList(),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //10
                new User("Zenek", "Biednapalka", Sex.MAN, 18,
                        Arrays.asList(),
                        Arrays.asList())
        );

        result.add( //11
                new User("Mariusz", "Dreh", Sex.OTHER, 50,
                        Arrays.asList(accounts.get(14)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //12
                new User("Marcin", "Marcinowicz", Sex.MAN, 37,
                        Arrays.asList(accounts.get(15), accounts.get(16), accounts.get(17), accounts.get(18),
                                accounts.get(19), accounts.get(20), accounts.get(21)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //13
                new User("Jan", "Nowicki", Sex.MAN, 45,
                        Arrays.asList(accounts.get(22)),
                        Arrays.asList(Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //14
                new User("Kasia", "Nawalka", Sex.WOMAN, 29,
                        Arrays.asList(accounts.get(23), accounts.get(24)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN))
        );

        result.add( //15
                new User("Martin", "Prawicowy", Sex.MAN, 29,
                        Arrays.asList(accounts.get(25), accounts.get(26)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //16
                new User("Karol", "Romanowicz", Sex.MAN, 64,
                        Arrays.asList(accounts.get(27), accounts.get(28)),
                        Arrays.asList(Permit.LOAN))
        );

        result.add( //17
                new User("Marta", "Wialkibuz", Sex.WOMAN, 33,
                        Arrays.asList(accounts.get(29)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //18
                new User("Patryk", "Piwny", Sex.MAN, 28,
                        Arrays.asList(accounts.get(30)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        result.add( //19
                new User("Zenek", "Jawowy", Sex.MAN, 22,
                        Arrays.asList(accounts.get(31)),
                        Arrays.asList())
        );

        result.add( //20
                new User("Alfred", "Pasibrzuch", Sex.MAN, 40,
                        Arrays.asList(accounts.get(32), accounts.get(33), accounts.get(34)),
                        Arrays.asList(Permit.DEPOSIT, Permit.TRANSFER, Permit.LOAN, Permit.ORDER_HISTORY))
        );

        return result;
    }
}
