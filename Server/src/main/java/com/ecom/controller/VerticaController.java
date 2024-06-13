package com.ecom.controller;

import com.ecom.service.VerticaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vertica")
@Tag(name = "vertica")
public class VerticaController {

    @Autowired
    private VerticaService verticaService;

    @GetMapping("/test")
    public List<String> test(){
        return verticaService.testQuery();
    }
}
