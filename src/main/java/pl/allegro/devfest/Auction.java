package main.java.pl.allegro.devfest;

import java.math.BigDecimal;

public class Auction {

    private final GoodType goodType;

    private final String name;

    private final BigDecimal price;

    public Auction(String name, GoodType goodType, BigDecimal price) {
        this.name = name;
        this.goodType = goodType;
        this.price = price;
    }

    public GoodType getGoodType() {
        return goodType;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
