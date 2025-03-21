package com.Test_Case_Management_System.Repository;

import com.Test_Case_Management_System.Model.TestModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TestRepository extends MongoRepository<TestModel,String> {
    Optional<TestModel> findById(String id);
    Page<TestModel> findAll(Pageable pageable);



}
