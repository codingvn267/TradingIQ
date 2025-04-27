package com.example.tradingiq.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * Controller that provides a mock AI response for stock analysis.
 * 
 * This controller simulates an AI-based technical analysis 
 * by returning a pre-defined JSON structure based on the stock symbol provided.
 * 
 * Mapped to /api/openai endpoint.
 * 
 * Please note that this is a mock implementation and does not perform any actual AI analysis.
 * In a real-world scenario, this would interface with an AI service to get dynamic responses.
 */

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    @PostMapping
    public ResponseEntity<String> mockAI(@RequestBody Map<String, String> requestBody) {
        String symbol = requestBody.get("symbol");

        String mockResponse = """
        {
          "choices": [
            {
              "message": {
                "role": "assistant",
                "content": "📈 Based on recent trends, %s appears to be in a short-term consolidation phase. Consider holding or setting alerts for a breakout above resistance."
              }
            }
          ]
        }
        """.formatted(symbol);

        return ResponseEntity.ok(mockResponse);
    }
}



