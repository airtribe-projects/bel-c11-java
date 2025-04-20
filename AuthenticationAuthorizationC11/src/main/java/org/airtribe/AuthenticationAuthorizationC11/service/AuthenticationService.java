package org.airtribe.AuthenticationAuthorizationC11.service;

import java.util.Collections;
import java.util.Date;
import org.airtribe.AuthenticationAuthorizationC11.dto.UserDTO;
import org.airtribe.AuthenticationAuthorizationC11.entity.User;
import org.airtribe.AuthenticationAuthorizationC11.entity.VerificationToken;
import org.airtribe.AuthenticationAuthorizationC11.repository.UserRepository;
import org.airtribe.AuthenticationAuthorizationC11.repository.VerificationTokenRepository;
import org.airtribe.AuthenticationAuthorizationC11.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder _passwordEncoder;

  @Autowired
  private VerificationTokenRepository _verificationTokenRepository;

  public User registerUser(UserDTO user) {
    User dbUser = new User();
    dbUser.setUsername(user.getUsername());
    dbUser.setPassword(_passwordEncoder.encode(user.getPassword()));
    dbUser.setEnabled(false);
    dbUser.setRole("USER");
    return userRepository.save(dbUser);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        Collections.emptyList());
  }

  public void registerVerificationToken(User registeredUser, String generatedToken) {
    VerificationToken token = new VerificationToken();
    token.setToken(generatedToken);
    token.setUser(registeredUser);
    Long expiryDate = (new java.util.Date().getTime() + 1000 * 60 * 60 * 24);
    token.setExpiryDate(new Date(expiryDate));
    _verificationTokenRepository.save(token);
  }

  public boolean validateRegistrationToken(String token) {
    VerificationToken registeredToken = _verificationTokenRepository.findByToken(token);
    if (registeredToken == null) {
      return false;
    }

    long registeredExpiryDate = registeredToken.getExpiryDate().getTime();
    if (registeredExpiryDate < System.currentTimeMillis()) {
      return false;
    }

    return true;
  }

  public void enableUser(String token) {
    VerificationToken registeredToken = _verificationTokenRepository.findByToken(token);
    User registeredUser = registeredToken.getUser();
    registeredUser.setEnabled(true);
    userRepository.save(registeredUser);
    _verificationTokenRepository.delete(registeredToken);
  }

  public String loginUser(String username, String password) {
    User registeredUser = userRepository.findByUsername(username);

    if (registeredUser == null || !registeredUser.isEnabled()) {
      return "User not registered/ User not enabled";
    }

    boolean arePasswordMatch = _passwordEncoder.matches(password, registeredUser.getPassword());
    if (arePasswordMatch) {
        return JwtUtil.generateToken(username);
    }

    return "User not authenticated";
  }
}
