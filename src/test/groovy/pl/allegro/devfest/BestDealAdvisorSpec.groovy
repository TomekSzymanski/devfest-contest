package main.java.pl.allegro.devfest;

import spock.lang.Specification

import static GoodType.*

class BestDealAdvisorSpec extends Specification {

    def "should return best deal for given criteria"() {
        given:
        def auctions = [
            new Auction("boots", BOOTS, 55.55),
            new Auction("boots", BOOTS, 100),
            new Auction("shirt", SHIRT, 50),
            new Auction("shirt", SHIRT, 100),
        ]

        def bestDealAdvisor = new BestDealAdvisor(auctions)
        def dealRequirements = new DealCriteria(200, [BOOTS, SHIRT] as Set)

        when:
        def bestDeal = bestDealAdvisor.findBestDeal(dealRequirements)

        then:
        bestDeal.present
        bestDeal.get().totalPrice == 105.55
        bestDeal.get().auctions.find { it.goodType == BOOTS } != null
        bestDeal.get().auctions.find { it.goodType == SHIRT } != null
    }

    def "should return empty optional if all of required good types does not fit in the budget"() {
        given:
        def auctions = [
            new Auction("boots", BOOTS, 55.55),
            new Auction("boots", BOOTS, 100),
            new Auction("shirt", SHIRT, 50),
            new Auction("shirt", SHIRT, 100),
        ]

        def bestDealAdvisor = new BestDealAdvisor(auctions)
        def dealRequirements = new DealCriteria(100, [BOOTS, SHIRT] as Set)

        when:
        def bestDeal = bestDealAdvisor.findBestDeal(dealRequirements)

        then:
        !bestDeal.present
    }

    def "should return empty optional if any of required good types is not for sale (is impossible to buy)"() {
        given:
        def auctions = [
            new Auction("boots", BOOTS, 100),
            new Auction("boots", BOOTS, 100),
            new Auction("boots", BOOTS, 100),
        ]

        def bestDealAdvisor = new BestDealAdvisor(auctions)
        def dealRequirements = new DealCriteria(200, [SHIRT] as Set)

        when:
        def bestDeal = bestDealAdvisor.findBestDeal(dealRequirements)

        then:
        !bestDeal.present
    }
}
