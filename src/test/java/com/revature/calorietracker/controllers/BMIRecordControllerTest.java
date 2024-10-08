package com.revature.calorietracker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.calorietracker.models.BMIRecord;
import com.revature.calorietracker.models.User;
import com.revature.calorietracker.service.BMIRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BMIRecordController.class)
public class BMIRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BMIRecordService bmiRecordService;

    @Autowired
    private ObjectMapper objectMapper;


}