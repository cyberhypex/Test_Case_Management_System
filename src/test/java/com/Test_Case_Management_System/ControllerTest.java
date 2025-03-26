package com.Test_Case_Management_System;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.Test_Case_Management_System.Controller.TestController;
import com.Test_Case_Management_System.DTO.TestCaseDTO;
import com.Test_Case_Management_System.Model.Priority;
import com.Test_Case_Management_System.Model.Status;
import com.Test_Case_Management_System.Model.TestModel;
import com.Test_Case_Management_System.Service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.ArgumentMatchers.any;




@WebMvcTest(TestController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TestService testService;


    @Test
    void testGetALLTests() throws Exception{
        TestCaseDTO test1 = new TestCaseDTO("1", "Test1", "Description1", Priority.PRIORITY_LOW, Status.STATUS_INPROGRESS, Instant.now(), Instant.now());
        TestCaseDTO test2 = new TestCaseDTO("2", "Test2", "Description2", Priority.PRIORITY_HIGH, Status.STATUS_FAILED, Instant.now(), Instant.now());


        List<TestCaseDTO> testList = List.of(test1, test2);

       when(testService.getAllTests(any(Pageable.class))).thenReturn(testList);



        mockMvc.perform(get("/findAllTests?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Test1"))
                .andExpect(jsonPath("$[1].title").value("Test2"));


        verify(testService, times(1)).getAllTests(any(Pageable.class));
    }

    @Test
    void testGetTestById() throws Exception{
        TestCaseDTO test1 = new TestCaseDTO("1", "Test1", "Description1", Priority.PRIORITY_LOW, Status.STATUS_INPROGRESS, Instant.now(), Instant.now());
        when(testService.getTestById(eq("1"))).thenReturn(Optional.of(test1));

        mockMvc.perform(get("/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test1"))
                .andExpect(jsonPath("$.description").value("Description1"))
                .andExpect(jsonPath("$.priority").value("PRIORITY_LOW"))
                .andExpect(jsonPath("$.status").value("STATUS_INPROGRESS"));
        verify(testService,times(1)).getTestById("1");
    }

    @Test
    void deleteTestById_ShouldReturnSuccessMessage() throws Exception {
        String testId = "1";
        String successMessage = "Test case with id: " + testId + " deleted successfully.";


        when(testService.deleteTest(testId)).thenReturn(successMessage);

        mockMvc.perform(delete("/deleteTestById/" + testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(successMessage));

        verify(testService, times(1)).deleteTest(testId);
    }











}


