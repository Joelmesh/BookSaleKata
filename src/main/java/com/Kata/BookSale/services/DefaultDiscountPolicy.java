package com.Kata.BookSale.services;

import java.util.Map;

public class DefaultDiscountPolicy implements DiscountPolicy {

    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            1, 1.0,
            2, 0.95,
            3, 0.90,
            4, 0.80,
            5, 0.75
    );

    @Override
    public double discountFactor(int setSize) {
        return DISCOUNTS.getOrDefault(setSize, 1.0);
    }
}
