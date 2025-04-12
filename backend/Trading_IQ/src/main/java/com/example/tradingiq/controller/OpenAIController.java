package com.example.tradingiq.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

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



