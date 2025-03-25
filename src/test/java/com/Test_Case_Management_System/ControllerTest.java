package com.Test_Case_Management_System;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.Test_Case_Management_System.Controller.TestController;
import com.Test_Case_Management_System.DTO.TestCaseDTO;
import com.Test_Case_Management_System.Model.Priority;
import com.Test_Case_Management_System.Model.Status;
import com.Test_Case_Management_System.Service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;




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
}


