package com.example.reactdemo.controller;
import com.example.reactdemo.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/city/redis")
public class CityRedisWebFluxController {
    private static String CITY_KEY_PREFIX = "city:";

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        String key = "city:" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        Mono<City> createResult;
        if (!hasKey) {
            createResult = Mono.create(monoSink -> monoSink.success(null));
        }else {
            City city = operations.get(key);
            createResult = Mono.create(monoSink -> monoSink.success(city));
        }
        return createResult;
    }

//    @GetMapping()
//    public Flux<City> findAllCity() {
//        return cityHandler.findAllCity();
//    }

    @PostMapping()
    public Mono<City> saveCity(@RequestBody City city) {
        String key = "city:" + city.getId();
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        operations.set(key, city, 60, TimeUnit.SECONDS);
        return Mono.create(monoSink -> monoSink.success(city));
    }


    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        String key = CITY_KEY_PREFIX + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        return Mono.create(monoSink -> monoSink.success(id));
    }
}