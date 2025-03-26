package com.Test_Case_Management_System.Controller;

import com.Test_Case_Management_System.DTO.TestCaseDTO;
import com.Test_Case_Management_System.Model.Priority;
import com.Test_Case_Management_System.Model.Status;
import com.Test_Case_Management_System.Model.TestModel;
import com.Test_Case_Management_System.Service.TestService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/create/{title}/{priority}/{status}")
    public ResponseEntity<TestCaseDTO> createTest(@PathVariable String title,
                                                  @RequestParam(required = false) String description,
                                                  @PathVariable Priority priority,
                                                  @PathVariable Status status) {
        TestCaseDTO testDTO = testService.createTest(title, description, priority, status);
        return ResponseEntity.status(HttpStatus.CREATED).body(testDTO);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TestCaseDTO> getTestById(@PathVariable String id) {
        Optional<TestCaseDTO> testDTO = testService.getTestById(id);
        return testDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findAllTests")
    public ResponseEntity<List<TestCaseDTO>> getAllTests(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "createdAt,desc") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")));
        List<TestCaseDTO> allTests = testService.getAllTests(pageable);

        if (allTests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(allTests);
    }

    @DeleteMapping("/deleteTestById/{id}")
    public ResponseEntity<String> deleteTestCaseById(@PathVariable String id) {
        return ResponseEntity.ok(testService.deleteTest(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TestCaseDTO> updateTest(@PathVariable String id,
                                                  @RequestBody TestModel updatedTest) {
        return testService.updateTests(id, updatedTest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}