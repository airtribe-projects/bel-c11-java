package org.airtribe.AuthenticationAuthorizationC11.controller;

import java.util.UUID;
import org.airtribe.AuthenticationAuthorizationC11.dto.UserDTO;
import org.airtribe.AuthenticationAuthorizationC11.entity.User;
import org.airtribe.AuthenticationAuthorizationC11.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/register")
  public User register(@RequestBody UserDTO user) {
    User registeredUser = authenticationService.registerUser(user);
    String generatedToken = UUID.randomUUID().toString();
    String applicationUrl = "http://localhost:9200/verifyRegistration?token=" + generatedToken;
    System.out.println("Verify your identity by clicking on the following url: " + applicationUrl);
    authenticationService.registerVerificationToken(registeredUser, generatedToken);
    return registeredUser;
  }

  @PostMapping("/verifyRegistration")
  public String verifyToken(@RequestParam String token) {
    boolean isTokenValid = authenticationService.validateRegistrationToken(token);
    if (isTokenValid) {
      authenticationService.enableUser(token);
      return "Token validation successful, enabled user, please login to proceed";
    } else {
      return "Token validation failed";
    }
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello from auth";
  }


  @GetMapping("/tokenHello")
  public String tokenHello() {
    return "Hello from auth";
  }

  @PostMapping("/signin")
  public String login(@RequestParam String username, @RequestParam String password) {
    return authenticationService.loginUser(username, password);
  }
}
