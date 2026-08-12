package com.Kata.BookSale;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.*;

import com.Kata.BookSale.services.PriceCalculatorService;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class PriceCalculatorControllerTest {
   private final PriceCalculatorService service = new PriceCalculatorService();

    @Test
    void testAllPricingScenarios() {
        
        verifyPrice(Collections.emptyList(), 0.0);
        verifyPrice(null, 0.0);

       
        verifyPrice(List.of(1, 1, 1), 150.0);

        
        verifyPrice(List.of(0, 1), 95.0);         
        verifyPrice(List.of(0, 1, 2, 3, 4), 187.5); 

       
        verifyPrice(List.of(0, 0, 0, 0, 0, 1), 295.0); 

        verifyPrice(List.of(0, 0, 1, 1, 2, 2, 3, 4), 320.0); 
        verifyPrice(List.of(0,0,0,0, 1,1,1,1, 2,2,2,2, 3,3, 4,4), 640.0); 
    }

    
    private void verifyPrice(List<Integer> basket, double expectedPrice) {
        assertEquals(expectedPrice, service.calculatePrice(basket));
    }

}
