package com.symbolguys.stronghold.manor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.symbolguys.stronghold.manor.entity.Manor;

@RestController
public class ManorController {

    @Autowired
    public ManorRepository repository;

    @GetMapping("/get-manors")
	public Iterable<Manor> all() {
        return repository.findAll();
    }

}
