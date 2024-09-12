package workshops.domain;

import java.util.List;


public record Company(String name, List<User> users) {}
