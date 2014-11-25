package main.java.pl.allegro.devfest;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class BestDealAdvisor {

    private final Collection<Auction> auctions;

    public BestDealAdvisor(Collection<Auction> auctions) {
        this.auctions = Collections.unmodifiableCollection(auctions);
    }

    public Optional<BestDeal> findBestDeal(DealCriteria dealRequirements) {
        BigDecimal budget = dealRequirements.getBudget();
        Set<GoodType> goodsToBuy = dealRequirements.getGoodTypes();

        Map<GoodType, Optional<Auction>> cheapestPerGoodTypeMap = auctions.stream().filter(a -> goodsToBuy.contains(a.getGoodType())) // we iterate only once over all auctions
                .collect(
                    Collectors.groupingBy(Auction::getGoodType,
                            Collectors.minBy(Comparator.comparing(Auction::getPrice))
                    )
                ); // becasue of collection method there is no possibility that any value Optional is empty

        // dirty, only to unbox from Optional
        List<Auction> cheapestPerGodTypeList = cheapestPerGoodTypeMap.values().stream().map(Optional::get).collect(Collectors.toList());

        // have to contain all required goods
        if (goodsToBuy.stream().allMatch(cheapestPerGoodTypeMap::containsKey)) {
            return Collections.singletonList(new BestDeal(cheapestPerGodTypeList)).stream().filter(d->d.getTotalPrice().compareTo(budget) <= 0).findAny();
        } else {
            return Optional.empty();
        }
    }

}
