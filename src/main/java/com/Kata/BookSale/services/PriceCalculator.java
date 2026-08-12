package com.Kata.BookSale.services;

import java.util.List;

public interface PriceCalculator {
    double calculatePrice(List<Integer> basket);
}
