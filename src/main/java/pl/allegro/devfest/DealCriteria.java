package main.java.pl.allegro.devfest;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

public class DealCriteria {

    private final BigDecimal budget;

    private final Set<GoodType> goodTypes;

    public DealCriteria(BigDecimal budget, Set<GoodType> goodTypes) {
        this.budget = budget;
        this.goodTypes = Collections.unmodifiableSet(goodTypes);
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public Set<GoodType> getGoodTypes() {
        return Collections.unmodifiableSet(goodTypes);
    }

}
