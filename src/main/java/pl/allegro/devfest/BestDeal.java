package main.java.pl.allegro.devfest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

public class BestDeal {

    private final Collection<Auction> auctions;

    public BestDeal(Collection<Auction> auctions) {
        this.auctions = Collections.unmodifiableCollection(auctions);
    }

    public Collection<Auction> getAuctions() {
        return Collections.unmodifiableCollection(auctions);
    }

    public BigDecimal getTotalPrice() {
        return auctions
            .stream()
            .map(auction -> auction.getPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
