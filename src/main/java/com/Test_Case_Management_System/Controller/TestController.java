package com.Test_Case_Management_System.Controller;


import com.Test_Case_Management_System.Model.Priority;
import com.Test_Case_Management_System.Model.Status;
import com.Test_Case_Management_System.Model.TestModel;
import com.Test_Case_Management_System.Repository.TestRepository;
import com.Test_Case_Management_System.Service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TestController {


    final private TestRepository testRepository;
    final private TestService testService;

    public TestController(TestRepository testRepository, TestService testService) {
        this.testRepository = testRepository;
        this.testService = testService;
    }

    @PostMapping("/create/{title}/{priority}/{status}")
    public ResponseEntity<TestModel> createTest(@PathVariable String title,
                                                @RequestParam(required = false) String description,
                                                @PathVariable Priority priority,
                                                @PathVariable Status status) {
        TestModel testModel = testService.createTest(title, description, priority, status);
        return ResponseEntity.ok(testModel);
    }

}
