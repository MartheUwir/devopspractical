package com.example.marthetermoneexam.controller;

import com.example.marthetermoneexam.dto.DoMathRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void doMath_success() {
        DoMathRequest body = null;
        body.setOperand1(1);
        body.setOperand2(2);
        body.setOperation("+");
        ResponseEntity<Double> response = this.restTemplate.postForEntity("/calculate",body,Double.class );
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(3, response.getBody());
    }

    @Test
    public void doMath_Invalid_Operation() {
        DoMathRequest body = null;
        body.setOperand1(1);
        body.setOperand2(0);
        body.setOperation("/");
        ResponseEntity<Double> response = this.restTemplate.postForEntity("/calculate",body,Double.class );
        assertTrue(response.getStatusCode().isError());
        assertEquals("Cannot divide by 0", response.getBody());
    }

    @Test
    public void doMath_unknown_Operation() {
        DoMathRequest body = null;
        body.setOperand1(1);
        body.setOperand2(0);
        body.setOperation("!");
        ResponseEntity<Double> response = this.restTemplate.postForEntity("/calculate",body,Double.class );
        assertTrue(response.getStatusCode().isError());
        assertEquals("Unknown operation", response.getBody());
    }
}
