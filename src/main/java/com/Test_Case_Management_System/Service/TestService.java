package com.Test_Case_Management_System.Service;

import com.Test_Case_Management_System.DTO.TestCaseDTO;
import com.Test_Case_Management_System.Model.Priority;
import com.Test_Case_Management_System.Model.Status;
import com.Test_Case_Management_System.Model.TestModel;
import com.Test_Case_Management_System.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public TestCaseDTO createTest(String title, String description, Priority priority, Status status) {
        TestModel testModel = new TestModel(title, description, priority, status);
        testModel.setCreatedAt(Instant.now());
        testModel.setUpdatedAt(Instant.now());
        TestModel savedTest = testRepository.save(testModel);
        return convertToDTO(savedTest);
    }

    public Optional<TestCaseDTO> getTestById(String id) {
        return testRepository.findById(id).map(this::convertToDTO);
    }

    public List<TestCaseDTO> getAllTests(Pageable pageable) {
        Page<TestModel> testPage = testRepository.findAll(pageable);
        return testPage.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public String deleteTest(String id) {
        if (testRepository.existsById(id)) {
            testRepository.deleteById(id);
            return "Test case with id: " + id + " deleted successfully.";
        }
        return "Test case with id: " + id + " not found.";
    }

    public Optional<TestCaseDTO> updateTests(String id, TestModel testModel) {
        return testRepository.findById(id).map(existingTest -> {
            existingTest.setTitle(testModel.getTitle());
            existingTest.setDescription(testModel.getDescription());
            existingTest.setStatus(testModel.getStatus());
            existingTest.setPriority(testModel.getPriority());
            existingTest.setUpdatedAt(Instant.now());

            TestModel updatedTest = testRepository.save(existingTest);
            return convertToDTO(updatedTest);
        });
    }

    private TestCaseDTO convertToDTO(TestModel testModel) {
        return new TestCaseDTO(
                testModel.getId(),
                testModel.getTitle(),
                testModel.getDescription(),
                testModel.getPriority(),
                testModel.getStatus(),
                testModel.getCreatedAt(),
                testModel.getUpdatedAt()
        );
    }
}
