package com.example.reactdemo.handler;

import com.example.reactdemo.dao.CityLocalRepository;
import com.example.reactdemo.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CityLocalHandler {
    private final CityLocalRepository cityLocalRepository;

    @Autowired
    public CityLocalHandler(CityLocalRepository cityLocalRepository) {
        this.cityLocalRepository = cityLocalRepository;
    }


    public Mono<Long> save(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityLocalRepository.save(city)));
    }


    public Mono<City> findCityById(Long id) {
        return Mono.justOrEmpty(cityLocalRepository.findCityById(id));
    }

    public Flux<City> findAllCity() {
        return Flux.fromIterable(cityLocalRepository.findAll());
    }
    public Mono<Long> modifyCity(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityLocalRepository.updateCity(city)));
    }

    public Mono<Long> deleteCity(Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityLocalRepository.deleteCity(id)));
    }

}
