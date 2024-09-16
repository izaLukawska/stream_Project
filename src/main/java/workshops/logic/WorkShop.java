package workshops.logic;

import workshops.domain.*;
import workshops.domain.Currency;
import workshops.mock.HoldingMockGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class WorkShop {
    /**
     * Lista holdingów wczytana z mocka.
     */
    private final List<Holding> holdings;

    WorkShop(HoldingMockGenerator holdingMockGenerator) {
        holdings = holdingMockGenerator.generate();
    }

    /**
     * Metoda zwraca liczbę holdingów w których jest przynajmniej jedna firma.
     */
    long getHoldingsWhereAreCompanies() {
        return holdings.stream().filter(holding -> !holding.companies().isEmpty()).count();
    }

    /**
     * Zwraca nazwy wszystkich holdingów pisane z małej litery w formie listy.
     */
    List<String> getHoldingNames() {
        return holdings.stream().map(holding -> holding.name().toLowerCase()).collect(toList());
    }

    /**
     * Zwraca nazwy wszystkich holdingów sklejone w jeden string i posortowane.
     * String ma postać: (Coca-Cola, Nestle, Pepsico)
     */
    String getHoldingNamesAsString() {
        return holdings.stream().map(Holding::name).sorted().collect(joining(", ", "(", ")"));
    }

    /**
     * Zwraca liczbę firm we wszystkich holdingach.
     */
    long getCompaniesAmount() {
        return holdings.stream().mapToLong(holding -> holding.companies().size()).sum();
    }

    /**
     * Zwraca liczbę wszystkich pracowników we wszystkich firmach.
     */
    long getAllUserAmount() {
        return holdings.stream().flatMap(h-> h.companies().stream())
                .mapToLong(c->c.users().size()).sum();
    }

    /**
     * Zwraca listę wszystkich nazw firm w formie listy. Tworzenie strumienia firm umieść w osobnej metodzie którą
     * później będziesz wykorzystywać.
     */
    List<String> getAllCompaniesNames() {
        return holdings.stream().flatMap(holding -> holding.companies().stream()).map(Company::name)
                .collect(toList());
    }

    /**
     * Zwraca listę wszystkich firm jako listę, której implementacja to LinkedList. Obiektów nie przepisujemy
     * po zakończeniu działania strumienia.
     */
    LinkedList<String> getAllCompaniesNamesAsLinkedList() {
        return holdings.stream().flatMap(holding -> holding.companies().stream())
                .map(Company::name)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Zwraca listę firm jako String gdzie poszczególne firmy są oddzielone od siebie znakiem "+"
     */
    String getAllCompaniesNamesAsString() {
        return holdings.stream().flatMap(h->h.companies().stream()).map(Company::name).collect(joining("+"));
    }

    /**
     * Zwraca listę firm jako string gdzie poszczególne firmy są oddzielone od siebie znakiem "+".
     * Używamy collect i StringBuilder.
     * <p>
     * UWAGA: Zadanie z gwiazdką. Nie używamy zmiennych.
     */
    String getAllCompaniesNamesAsStringUsingStringBuilder() {
        return holdings.stream()
                .flatMap(h -> h.companies().stream())
                .map(Company::name)
                .reduce(new StringBuilder(), ((stringBuilder, companyName) -> {
                    if(!stringBuilder.isEmpty()){
                        stringBuilder.append("+");
                    }
                    stringBuilder.append(companyName);
                    return stringBuilder;
                }),(sb1, sb2) -> {
                    sb1.append(sb2);
                    return sb1;
                }).toString();
    }

    /**
     * Zwraca liczbę wszystkich rachunków, użytkowników we wszystkich firmach.
     */
    long getAllUserAccountsAmount() {
        return holdings.stream()
                .flatMap(holding -> holding.companies().stream())
                .flatMap(c->c.users().stream())
                .mapToLong(u->u.accounts().size())
                .sum();
    }

    /**
     * Zwraca listę wszystkich walut w jakich są rachunki jako string, w którym wartości
     * występują bez powtórzeń i są posortowane.
     */
    String getAllCurrencies() {
        return holdings.stream().flatMap(h->h.companies().stream()).flatMap(c->c.users().stream())
                .flatMap(user -> user.accounts().stream())
                .map(a->a.currency().name()).distinct().sorted().collect(Collectors.joining(" "));
    }

    List<Currency> getCurrencyList(){
        return holdings.stream().flatMap(holding -> holding.companies().stream())
                .flatMap(company -> company.users().stream())
                .flatMap(u->u.accounts().stream())
                .map(Account::currency).sorted(Comparator.comparing(Enum::name)).distinct().toList();
    }

    /**
     * Metoda zwraca analogiczne dane jak getAllCurrencies, jednak na utworzonym zbiorze nie uruchamiaj metody
     * stream, tylko skorzystaj z  Stream.generate. Wspólny kod wynieś do osobnej metody.
     *
     * @see #getAllCurrencies()
     */
    String getAllCurrenciesUsingGenerate() {
        return Stream.generate(() -> getCurrencyList().iterator().next())
                .limit(getCurrencyList().size())
                .map(Enum::name)
                .collect(Collectors.joining(" "));
    }

    /**
     * Zwraca liczbę kobiet we wszystkich firmach. Powtarzający się fragment kodu tworzący strumień użytkowników umieść
     * w osobnej metodzie. Predicate określający czy mamy do czynienia z kobietą niech będzie polem statycznym w klasie.
     */

    private static Predicate<User> isWoman = u->u.sex().equals(Sex.WOMAN);
    long getWomanAmount() {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(c->c.users().stream())
                .filter(isWoman)
                .count();
    }

    /**
     * Przelicza kwotę na rachunku na złotówki za pomocą kursu określonego w enum Currency.
     */
    BigDecimal getAccountAmountInPLN(final Account account) {
        return account.amount().multiply(BigDecimal.valueOf(account.currency().rate)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Przelicza kwotę na podanych rachunkach na złotówki za pomocą kursu określonego w enum Currency i sumuje ją.
     */
    BigDecimal getTotalCashInPLN(final List<Account> accounts) {
        return accounts.stream()
                .map(a->a.amount().multiply(BigDecimal.valueOf(a.currency().rate).setScale(2, RoundingMode.HALF_UP)))
                .reduce((currentAmount, sum)-> sum.add(currentAmount)).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Zwraca imiona użytkowników w formie zbioru, którzy spełniają podany warunek.
     */

    Set<String> getUsersForPredicate(final Predicate<User> userPredicate) {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(c->c.users().stream())
                .filter(userPredicate)
                .map(User::firstName)
                .collect(Collectors.toSet());
    }

    /**
     * Metoda filtruje użytkowników starszych niż podany jako parametr wiek, wyświetla ich na konsoli, odrzuca mężczyzn
     * i zwraca ich imiona w formie listy.
     */
    List<String> getOldWoman(final int age) {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(c->c.users().stream())
                .filter(user -> user.sex().equals(Sex.WOMAN) && user.age() >= age)
                .map(User::firstName)
                .peek(System.out::println)
                .toList();
    }

    /**
     * Dla każdej firmy uruchamia przekazaną metodę.
     */
    void executeForEachCompany(final Consumer<Company> consumer) {
        holdings.stream().flatMap(h->h.companies().stream()).forEach(consumer);

    }

    /**
     * Wyszukuje najbogatsza kobietę i zwraca ją. Metoda musi uzwględniać to że rachunki są w różnych walutach.
     */
    //pomoc w rozwiązaniu problemu w zadaniu: https://stackoverflow.com/a/55052733/9360524
    Optional<User> getRichestWoman() {
        return holdings.stream().flatMap(h->h.companies().stream()).flatMap(c->c.users().stream())
                .filter(user -> user.sex().equals(Sex.WOMAN))
                .collect(Collectors.toMap(Function.identity(),
                        u->u.accounts().stream()
                                .map(a->a.amount().multiply(BigDecimal.valueOf(a.currency().rate).setScale(2, RoundingMode.HALF_UP)))
                                .reduce((currentAmount, sum) -> sum.add(currentAmount)).orElse(BigDecimal.ZERO)))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
        // entrySet - aby uzyskać dostęp do value i key
    }

    /**
     * Zwraca nazwy pierwszych N firm. Kolejność nie ma znaczenia.
     */
    Set<String> getFirstNCompany(final int n) {
        return holdings.stream().flatMap(h->h.companies().stream())
                .map(Company::name)
                .limit(n).collect(Collectors.toSet());
    }

    /**
     * Metoda zwraca jaki rodzaj rachunku jest najpopularniejszy. Stwórz pomocniczą metodę getAccountStream.
     * Jeżeli nie udało się znaleźć najpopularniejszego rachunku metoda ma wyrzucić wyjątek IllegalStateException.
     * Pierwsza instrukcja metody to return.
     */
    AccountType getMostPopularAccountType() {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(company -> company.users().stream())
                .flatMap(user -> user.accounts().stream())
                .map(Account::type)
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Zwraca pierwszego z brzegu użytkownika dla podanego warunku. W przypadku kiedy nie znajdzie użytkownika wyrzuca
     * wyjątek IllegalArgumentException.
     */
    User getUser(final Predicate<User> predicate) {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(c->c.users().stream())
                .filter(predicate)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Zwraca mapę firm, gdzie kluczem jest jej nazwa a wartością lista pracowników.
     */
    Map<String, List<User>> getUserPerCompany() {
        return holdings.stream().flatMap(holding -> holding.companies().stream())
                .collect(toMap(Company::name, Company::users));
    }


    /**
     * Zwraca mapę firm, gdzie kluczem jest jej nazwa a wartością lista pracowników przechowywanych jako String
     * składający się z imienia i nazwiska. Podpowiedź:  Możesz skorzystać z metody entrySet.
     */
    Map<String, List<String>> getUserPerCompanyAsString() {
        return holdings.stream().flatMap(holding -> holding.companies().stream())
                .collect(toMap(Company::name,
                        c->c.users().stream().map(user -> user.firstName() + " " +  user.lastName()).toList()));
    }

    /**
     * Zwraca mapę firm, gdzie kluczem jest jej nazwa a wartością lista pracowników przechowywanych jako obiekty
     * typu T, tworzonych za pomocą przekazanej funkcji.
     */
    //pomoc w rozwiązaniu problemu w zadaniu: https://stackoverflow.com/a/54969615/9360524
    <T> Map<String, List<T>> getUserPerCompany(final Function<User, T> converter) {
        return  holdings.stream().flatMap(holding -> holding.companies().stream())
                .collect(toMap(Company::name, c->c.users().stream().map(converter).collect(toList())));
    }

    /**
     * Zwraca mapę gdzie kluczem jest flaga mówiąca o tym czy mamy do czynienia z mężczyzną, czy z kobietą.
     * Osoby "innej" płci mają zostać zignorowane. Wartością jest natomiast zbiór nazwisk tych osób.
     */
    Map<Boolean, Set<String>> getUserBySex() {
        return  holdings.stream().flatMap(holding -> holding.companies().stream())
                .flatMap(company -> company.users().stream())
                .filter(u->u.sex().equals(Sex.WOMAN) || u.sex().equals(Sex.MAN))
                .collect(groupingBy(u->u.sex().equals(Sex.MAN), mapping(User::lastName, toSet())));
    }

    /**
     * Zwraca mapę rachunków, gdzie kluczem jest numer rachunku, a wartością ten rachunek.
     */
    Map<String, Account> createAccountsMap() {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(company -> company.users().stream())
                .flatMap(user -> user.accounts().stream())
                .collect(toMap(Account::number, Function.identity()));
    }

    /**
     * Zwraca listę wszystkich imion w postaci Stringa, gdzie imiona oddzielone są spacją i nie zawierają powtórzeń.
     */
    String getUserNames() {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(c->c.users().stream())
                .distinct()
                .map(User::firstName)
                .collect(joining(" "));
    }

    /**
     * Zwraca zbiór wszystkich użytkowników. Jeżeli jest ich więcej niż 10 to obcina ich ilość do 10.
     */
    Set<User> getUsers() {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(c->c.users().stream())
                .limit(10)
                .collect(toSet());
    }

    /**
     * Zapisuje listę numerów rachunków w pliku na dysku, gdzie w każda linijka wygląda następująco:
     * NUMER_RACHUNKU|KWOTA|WALUTA
     * <p>
     * Skorzystaj z strumieni i try-resources.
     */
    void saveAccountsInFile(final String fileName) {
        holdings.stream().flatMap(holding -> holding.companies().stream())
                .flatMap(company -> company.users().stream())
                .flatMap(user -> user.accounts().stream())
                .forEach( account -> {
                    String input = String.format("%s | %s | %s", account.number(), account.amount().toString(), account.currency().toString());
                    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){
                        bufferedWriter.write(input);
                    } catch(IOException e){
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * Zwraca użytkownika, który spełnia podany warunek.
     */
    Optional<User> findUser(final Predicate<User> userPredicate) {
        return holdings.stream().flatMap(holding -> holding.companies().stream())
                .flatMap(company -> company.users().stream())
                .filter(userPredicate)
                .findFirst();
    }

    /**
     * Dla podanego użytkownika zwraca informacje o tym ile ma lat w formie:
     * IMIE NAZWISKO ma lat X. Jeżeli użytkownik nie istnieje to zwraca text: Brak użytkownika.
     * <p>
     * Uwaga: W prawdziwym kodzie nie przekazuj Optionali jako parametrów.
     */
    String getAdultantStatus(final Optional<User> userOptional) {
        return userOptional.map(u->String.format("%s %s is %d years old." ,
                                u.firstName(), u.lastName(), u.age()))
                .orElse("No user found.");
    }

    /**
     * Metoda wypisuje na ekranie wszystkich użytkowników (imię, nazwisko) posortowanych od z do a.
     * Zosia Psikuta, Zenon Kucowski, Zenek Jawowy ... Alfred Pasibrzuch, Adam Wojcik
     */
    void showAllUser() {
        holdings.stream().flatMap(holding -> holding.companies().stream())
                .flatMap(company -> company.users().stream())
                .sorted((u1, u2) -> u2.firstName().compareTo(u1.firstName()))
                .forEach(u-> System.out.println(u.firstName() + " " + u.lastName()));
    }

    /**
     * Zwraca mapę, gdzie kluczem jest typ rachunku a wartością kwota wszystkich środków na rachunkach tego typu
     * przeliczona na złotówki.
     */
    Map<AccountType, BigDecimal> getMoneyOnAccounts() {
        return holdings.stream().flatMap(holding -> holding.companies().stream())
                .flatMap(company -> company.users().stream())
                .flatMap(user -> user.accounts().stream())
                .collect(groupingBy(Account::type,
                        Collectors.mapping(account -> account.amount().multiply(BigDecimal.valueOf(account.currency().rate).setScale(2, RoundingMode.HALF_UP)),
                                reducing(new BigDecimal(0), (currentAmount, sum) -> sum.add(currentAmount)))));
    }

    /**
     * Zwraca sumę kwadratów wieków wszystkich użytkowników.
     */
    int getAgeSquaresSum() {
        return holdings.stream().flatMap(holding -> holding.companies().stream())
                .flatMap(company -> company.users().stream())
                .mapToInt(u-> (int) Math.sqrt(u.age())).sum();
    }

    /**
     * Metoda zwraca N losowych użytkowników (liczba jest stała). Skorzystaj z metody generate. Użytkownicy nie mogą się
     * powtarzać, wszystkie zmienną muszą być final. Jeżeli podano liczbę większą niż liczba użytkowników należy
     * wyrzucić wyjątek (bez zmiany sygnatury metody).
     */
    List<User> getRandomUsers(final int n) {
        return Stream.generate(() -> {
            if (holdings.stream().flatMap(holding -> holding.companies().stream())
                    .mapToLong(company -> company.users().size()).sum() < n) {
                throw new RuntimeException();
            }
            return holdings.stream().flatMap(holding -> holding.companies().stream())
                    .flatMap(company -> company.users().stream()).iterator().next();
        }).limit(n).collect(toList());
    }

    /**
     * Zwraca zbiór walut w jakich są rachunki.
     */
    private Set<Currency> getCurenciesSet() {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(c->c.users().stream())
                .flatMap(u->u.accounts().stream())
                .map(Account::currency).collect(toSet());
    }

    /**
     * 38.
     * Stwórz mapę gdzie kluczem jest typ rachunku a wartością mapa mężczyzn posiadających ten rachunek, gdzie kluczem
     * jest obiekt User a wartością suma pieniędzy na rachunku danego typu przeliczona na złotkówki.
     */
    Map<Stream<AccountType>, Map<User, BigDecimal>> getMapWithAccountTypeKeyAndSumMoneyForManInPLN() {
        return holdings.stream().flatMap(h->h.companies().stream()).flatMap(c->c.users().stream())
                .filter(user -> user.sex().equals(Sex.MAN))
                .collect(groupingBy(user -> user.accounts().stream().map(Account::type), Collectors.toMap(user->user,
                        u->u.accounts().stream()
                                .map(a->a.amount().multiply(BigDecimal.valueOf(a.currency().rate).setScale(2, RoundingMode.HALF_UP)))
                                .reduce((currentAmount, sum) -> sum.add(currentAmount)).orElse(BigDecimal.ZERO))));
    }

    private Map<User, BigDecimal> manWithSumMoneyOnAccounts(final Company company) {
        return company.users().stream().filter(u->u.sex().equals(Sex.MAN))
                .collect(Collectors.toMap(Function.identity(),
                        u->u.accounts().stream()
                                .map(a->a.amount().multiply(BigDecimal.valueOf(a.currency().rate).setScale(2, RoundingMode.HALF_UP)))
                                .reduce((currentAmount, sum) -> sum.add(currentAmount)).orElse(BigDecimal.ZERO)));
    }

    private BigDecimal getSumUserAmountInPLN(final User user) {
        return user.accounts().stream().map(a->a.amount().multiply(BigDecimal.valueOf(a.currency().rate).setScale(2, RoundingMode.HALF_UP)))
                .reduce((currentAmount, sum) -> sum.add(currentAmount)).orElse(BigDecimal.ZERO);
    }

    /**
     * 39. Policz ile pieniędzy w złotówkach jest na kontach osób które nie są ani kobietą ani mężczyzną.
     */
    BigDecimal getSumMoneyOnAccountsForPeopleOtherInPLN() {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(c->c.users().stream())
                .filter(u->u.sex().equals(Sex.OTHER))
                .flatMap(a->a.accounts().stream())
                .map(a->a.amount().multiply(BigDecimal.valueOf(a.currency().rate).setScale(2, RoundingMode.HALF_UP)))
                .reduce((currentAmount, sum) -> sum.add(currentAmount)).orElse(BigDecimal.ZERO);
    }

    /**
     * 40.
     * Policz ile osób pełnoletnich posiada rachunek oraz ile osób niepełnoletnich posiada rachunek. Zwróć mapę
     * przyjmując klucz True dla osób pełnoletnich i klucz False dla osób niepełnoletnich. Osoba pełnoletnia to osoba
     * która ma więcej lub równo 18 lat
     */
    Map<Boolean, Long> divideIntoAdultsAndNonAdults() {
        return holdings.stream().flatMap(h->h.companies().stream())
                .flatMap(company -> company.users().stream())
                .collect(Collectors.groupingBy((user -> user.age() >= 18), counting()));
    }
}
