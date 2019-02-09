package com.example.reactdemo.handler;

import com.example.reactdemo.dao.CityMongoRepository;
import com.example.reactdemo.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CityMongoHandler {
    private final CityMongoRepository cityMongoRepository;

    @Autowired
    public CityMongoHandler(CityMongoRepository cityMongoRepository) {
        this.cityMongoRepository = cityMongoRepository;
    }


    public Mono<City> save(City city) {
        return cityMongoRepository.save(city);
    }


    public Mono<City> findCityById(Long id) {
        return cityMongoRepository.findById(id);
    }

    public Flux<City> findAllCity() {
        return cityMongoRepository.findAll();
    }
    public Mono<City> modifyCity(City city) {
        return cityMongoRepository.save(city);
    }

    public Mono<Long> deleteCity(Long id) {
        cityMongoRepository.deleteById(id);
        return Mono.create(cityMonoSink -> cityMonoSink.success(id));
    }

}
