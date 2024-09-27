package workshops.mock;


import workshops.domain.Company;
import workshops.domain.User;

import java.util.Arrays;
import java.util.List;


public class CompanyMockGenerator implements IGenerator {

  public List<Company> generate() {
	final UserMockGenerator userMockGenerator = new UserMockGenerator();
	final List<User> users = userMockGenerator.generate();

	return Arrays.asList(
			//1
			new Company("Fanta", Arrays.asList(users.get(0), users.get(1), users.get(2))),
			//2
			new Company("Sprite", Arrays.asList(users.get(3), users.get(4))),
			//3
			new Company("Nescafe", Arrays.asList(users.get(5), users.get(6), users.get(7), users.get(8))),
			//4
			new Company("Gerber", Arrays.asList(users.get(9), users.get(10), users.get(11))),
			//5
			new Company("Nestea", Arrays.asList(users.get(12))),
			//6
			new Company("Lays", Arrays.asList(users.get(13), users.get(14))),
			//7
			new Company("Pepsi", Arrays.asList(users.get(15), users.get(16), users.get(17))),
			//8
			new Company("Mirinda", Arrays.asList(users.get(18), users.get(19)))
	);
  }
}
