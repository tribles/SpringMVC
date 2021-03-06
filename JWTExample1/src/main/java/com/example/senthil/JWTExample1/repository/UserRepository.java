package com.example.senthil.JWTExample1.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.senthil.JWTExample1.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

}
