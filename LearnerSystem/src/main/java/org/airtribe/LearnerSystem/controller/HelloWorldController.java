package org.airtribe.LearnerSystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {

  @GetMapping("/")
  public String hello() {
    return "Hello World!";
  }

  @GetMapping("/hello")
  public String helloFunc() {
    return "Hello World from learner management system";
  }
}
