package br.dev.ismael.jsis.domain.infra.http.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GetController {
    @GetMapping
    public ResponseEntity test_get(){
        return ResponseEntity.ok("FOI");
    }
}
