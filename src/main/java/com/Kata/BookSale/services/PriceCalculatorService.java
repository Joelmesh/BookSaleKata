package com.Kata.BookSale.services;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorService implements PriceCalculator {

    private static final double BOOK_PRICE = 50.0;

    private final DiscountPolicy discountPolicy;
    private final BundleOptimizer bundleOptimizer;

    public PriceCalculatorService() {
        this(new DefaultDiscountPolicy(), new DefaultBundleOptimizer());
    }

    public PriceCalculatorService(DiscountPolicy discountPolicy, BundleOptimizer bundleOptimizer) {
        this.discountPolicy = discountPolicy;
        this.bundleOptimizer = bundleOptimizer;
    }

    @Override
    public double calculatePrice(List<Integer> basket) {
        if (basket == null || basket.isEmpty()) {
            return 0.0;
        }

        Map<Integer, Long> counts = basket.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<Integer> sets = new java.util.ArrayList<>();
        while (counts.values().stream().anyMatch(count -> count > 0)) {
            int uniqueBooksInSet = (int) counts.values().stream().filter(count -> count > 0).count();
            counts.replaceAll((bookId, count) -> Math.max(0L, count - 1));
            sets.add(uniqueBooksInSet);
        }

        Map<Integer, Long> setCounts = sets.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<Integer, Long> optimizedSetCounts = bundleOptimizer.optimize(setCounts);

        return optimizedSetCounts.entrySet().stream()
                .mapToDouble(entry -> entry.getValue() * entry.getKey() * BOOK_PRICE * discountPolicy.discountFactor(entry.getKey()))
                .sum();
    }

}
