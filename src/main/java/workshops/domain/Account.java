package workshops.domain;

import java.math.BigDecimal;


public record Account(AccountType type, String number,
                      BigDecimal amount, Currency currency) {

}
