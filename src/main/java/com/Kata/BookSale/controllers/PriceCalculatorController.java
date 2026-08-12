package com.Kata.BookSale.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Kata.BookSale.models.BasketRequest;
import com.Kata.BookSale.services.PriceCalculator;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/checkout")
public class PriceCalculatorController {

    private final PriceCalculator priceCalculator;

    public PriceCalculatorController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @PostMapping("/price")
    public ResponseEntity<Map<String, Object>> calculateBasketPrice(@RequestBody BasketRequest request) {
        
        if (request == null || request.getBookIds() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                "error", "The request body or bookIds array cannot be null."
            ));
        }

        double price = priceCalculator.calculatePrice(request.getBookIds());

        Map<String, Object> response = Map.of(
            "totalPrice", price,
            "currency", "EUR",
            "status", "SUCCESS"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> listBooks() {
        List<BookDto> books = List.of(
            new BookDto(0, "Clean Code", "Robert Martin", 2008, 50.0),
            new BookDto(1, "The Clean Coder", "Robert Martin", 2011, 50.0),
            new BookDto(2, "Clean Architecture", "Robert Martin", 2017, 50.0),
            new BookDto(3, "Test Driven Development by Example", "Kent Beck", 2003, 50.0),
            new BookDto(4, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50.0)
        );

        return ResponseEntity.ok(books);
    }

    private static record BookDto(int id, String title, String author, int year, double price) {
    }

}
