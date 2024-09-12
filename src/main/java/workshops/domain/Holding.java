package workshops.domain;

import java.util.List;

public record Holding(String name, List<Company> companies) {}
