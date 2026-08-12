package com.Kata.BookSale.services;

import java.util.HashMap;
import java.util.Map;

public class DefaultBundleOptimizer implements BundleOptimizer {

    @Override
    public Map<Integer, Long> optimize(Map<Integer, Long> setCounts) {
        Map<Integer, Long> optimized = new HashMap<>(setCounts);
        long exchange = Math.min(optimized.getOrDefault(5, 0L), optimized.getOrDefault(3, 0L));

        if (exchange > 0) {
            optimized.put(5, optimized.getOrDefault(5, 0L) - exchange);
            optimized.put(3, optimized.getOrDefault(3, 0L) - exchange);
            optimized.put(4, optimized.getOrDefault(4, 0L) + (exchange * 2));
        }

        return optimized;
    }
}
