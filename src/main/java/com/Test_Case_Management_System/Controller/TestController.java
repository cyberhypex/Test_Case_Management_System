package com.Test_Case_Management_System.Controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.Test_Case_Management_System.Model.Priority;
import com.Test_Case_Management_System.Model.Status;
import com.Test_Case_Management_System.Model.TestModel;
import com.Test_Case_Management_System.Repository.TestRepository;
import com.Test_Case_Management_System.Service.TestService;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return ResponseEntity.status(HttpStatus.CREATED).body(testModel);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TestModel> getTestById(@PathVariable String id) {
        return testService.getTestById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findAllTests")
    public ResponseEntity<List<TestModel>> getAllTests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")));
        Page<TestModel> pageResult = testService.getAllTests(pageable);

        if (pageResult.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pageResult.getContent());
    }


    @DeleteMapping("/deleteTestById/{id}")
    public String deleteTestCaseById(@PathVariable String id){
        return testService.deleteTest(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TestModel> updateTest(@PathVariable String id,
                                                @RequestBody TestModel updatedTest) {
        return testService.updateTests(id, updatedTest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }










}
