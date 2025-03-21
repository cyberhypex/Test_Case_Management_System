package com.Test_Case_Management_System.Service;


import com.Test_Case_Management_System.Model.Priority;
import com.Test_Case_Management_System.Model.Status;
import com.Test_Case_Management_System.Model.TestModel;
import com.Test_Case_Management_System.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;  // ✅ RIGHT

import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;



    public TestModel createTest(String title, String description, Priority priority, Status status){
        TestModel testModel=new TestModel(
                title,
                description,
                priority,
                status
        );

        return testRepository.save(testModel);
    }

    public Optional<TestModel> getTestById(String id) {
       return testRepository.findById(id);
    }

    public Page<TestModel> getAllTests(Pageable pageable) {
        return testRepository.findAll(pageable);
    }

    public String deleteTest(String id){
        testRepository.deleteById(id);
        return "TestCase with id: "+id+" "+"deleted successfully";
    }

    public Optional<TestModel> updateTests(String id, TestModel testModel){
        return testRepository.findById(id).map(existingTest->{
            existingTest.setTitle(testModel.getTitle());
            existingTest.setUpdatedAt(testModel.getUpdatedAt());
            existingTest.setDescription(testModel.getDescription());
            existingTest.setStatus(testModel.getStatus());
            existingTest.setPriority(testModel.getPriority());
            existingTest.setUpdatedAt(Instant.now());

            return testRepository.save(existingTest);

        });
    }

}
