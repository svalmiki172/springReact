package com.example.intern;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface InternRepository extends ReactiveMongoRepository<Intern, String>{

}
