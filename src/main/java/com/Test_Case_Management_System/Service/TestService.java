package com.Test_Case_Management_System.Service;


import com.Test_Case_Management_System.Model.Priority;
import com.Test_Case_Management_System.Model.Status;
import com.Test_Case_Management_System.Model.TestModel;
import com.Test_Case_Management_System.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
