package com.Test_Case_Management_System.Repository;

import com.Test_Case_Management_System.Model.TestModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<TestModel,String> {


}
