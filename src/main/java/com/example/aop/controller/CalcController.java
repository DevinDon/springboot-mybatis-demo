package com.example.aop.controller;

import com.example.aop.service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class CalcController {

  @Autowired
  private CalcService service;

  @GetMapping("/add")
  public double add() {
    return this.service.add(1, 2, 3);
  }

  @GetMapping("/sub")
  public double sub() {
    return this.service.sub(1, 2, 3);
  }

  @GetMapping("/mul")
  public double mul() {
    return this.service.mul(1, 2, 3);
  }

  @GetMapping("/div")
  public double div() {
    return this.service.div(1, 2, 3);
  }

}
