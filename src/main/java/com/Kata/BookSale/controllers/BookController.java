package com.Kata.BookSale.controllers;

import com.Kata.BookSale.services.PriceCalculator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Book Sale Calculator", description = "Endpoints for calculating book sets with optimized discounts")
public class BookController {
    private final PriceCalculator calculatorService;

    public BookController(PriceCalculator calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    @Operation(
        summary = "Calculate optimal basket price",
        description = "Takes a list of book IDs and applies maximum bundle discounts (e.g. optimizing 5+3 into 4+4)."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Successfully calculated total price",
        content = @Content(examples = @ExampleObject(value = "215.0"))
    )
    public double calculateBasketPrice(
        @RequestBody 
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "List of integers where numbers represent specific book IDs (e.g., [1, 1, 2, 2, 3, 3, 4, 5])",
            required = true
        )
        List<Integer> basket
    ) {
        return calculatorService.calculatePrice(basket);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> listBooks() {
        var books = List.of(
            new BookDto(0, "Clean Code", "Robert Martin", 2008, 50.0),
            new BookDto(1, "The Clean Coder", "Robert Martin", 2011, 50.0),
            new BookDto(2, "Clean Architecture", "Robert Martin", 2017, 50.0),
            new BookDto(3, "Test Driven Development by Example", "Kent Beck", 2003, 50.0),
            new BookDto(4, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 50.0)
        );

        return ResponseEntity.ok(books);
    }

    static record BookDto(int id, String title, String author, int year, double price) {
    }
}
