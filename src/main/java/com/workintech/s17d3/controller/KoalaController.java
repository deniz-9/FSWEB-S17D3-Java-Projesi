package com.workintech.s17d3.controller;

import com.workintech.s17d3.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Koala> findAll() {
        return this.koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala find(@PathVariable("id") Integer id){
        return koalas.get(id);
    }

    @PostMapping
    public Koala save(@RequestBody Koala koala){
        koalas.put(koala.getId(),koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public  Koala update(@PathVariable("id") Integer id, @RequestBody Koala koala){
        koala.setId(id);
        if(koalas.containsKey(id)){
            koalas.put(id,koala);
            return koalas.get(id);
        }
        else {
            return save(koala);
        }

    }
    @DeleteMapping("/{id}")
    public  Koala delete(@PathVariable("id") Integer id){
        return koalas.remove(id);
    }


}