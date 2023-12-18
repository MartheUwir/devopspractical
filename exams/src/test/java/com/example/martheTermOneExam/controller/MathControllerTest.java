package com.example.marthetermoneexam.controller;

import com.example.marthetermoneexam.dto.DoMathRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void doMath() throws Exception {
        DoMathRequest body = null;
        body.setOperand1(1);
        body.setOperand2(2);
        body.setOperation("+");

        String Json = new ObjectMapper().writeValueAsString(body);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(Json);


        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();


    }


}
