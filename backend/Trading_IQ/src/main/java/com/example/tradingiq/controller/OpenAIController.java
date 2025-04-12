package com.example.tradingiq.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {
  @PostMapping
  public ResponseEntity<String> askOpenAI(@RequestBody Map<String, String> requestBody) {
    String symbol = requestBody.get("symbol");

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth("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"); 
    String requestJson = """
        {
          "model": "gpt-3.5-turbo",
          "messages": [
            { "role": "system", "content": "You are a helpful financial trading assistant." },
            { "role": "user", "content": "Give me a technical analysis and trading recommendation for %s." }
          ],
          "max_tokens": 200,
          "temperature": 0.7
        }
        """.formatted(symbol);

    HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(
        "https://api.openai.com/v1/chat/completions",
        requestEntity,
        String.class);

    return ResponseEntity.ok(response.getBody());
  }

}
