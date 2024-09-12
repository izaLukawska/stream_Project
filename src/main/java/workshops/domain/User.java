package workshops.domain;

import java.util.List;


public record User(String firstName, String lastName, Sex sex, int age,
                   List<Account> accounts, List<Permit> permits) {}
