package com.example.reactdemo.dao;

import com.example.reactdemo.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityMongoRepository extends ReactiveMongoRepository<City, Long> {


}
