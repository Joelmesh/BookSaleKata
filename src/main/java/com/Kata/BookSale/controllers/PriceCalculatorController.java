package com.Kata.BookSale.controllers;

import com.Kata.BookSale.models.BasketRequest;
import com.Kata.BookSale.services.PriceCalculator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/checkout")
@Tag(name = "Book Sale Calculator", description = "Endpoints for calculating book sets with optimized discounts")
public class PriceCalculatorController {

    private final PriceCalculator priceCalculator;

    public PriceCalculatorController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @PostMapping("/price")
    @Operation(
        summary = "Calculate optimal basket price",
        description = "Takes a list of book IDs and applies maximum bundle discounts (e.g. optimizing 5+3 into 4+4)."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully calculated total price",
        content = @Content(examples = @ExampleObject(value = "{\"totalPrice\":95.0}"))
    )
    public ResponseEntity<Map<String, Object>> calculateBasketPrice(
            @org.springframework.web.bind.annotation.RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Basket request containing a list of book IDs to calculate price for",
                required = true
            )
                    BasketRequest request) {
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
    @Operation(summary = "List all available books", description = "Returns the canonical book catalog used for price calculation sample requests.")
    @ApiResponse(responseCode = "200", description = "A list of available books")
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

    public static record BookDto(int id, String title, String author, int year, double price) {
    }

}
