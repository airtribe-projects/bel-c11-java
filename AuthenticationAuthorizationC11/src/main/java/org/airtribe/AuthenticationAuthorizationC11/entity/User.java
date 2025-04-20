package org.airtribe.AuthenticationAuthorizationC11.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  private String username;

  private String password;

  private String role;

  private boolean isEnabled;

//  @OneToOne(mappedBy = "user")
//  private VerificationToken _verificationToken;

  public User(Long userId, String username, String password, String role, boolean isEnabled) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.role = role;
    this.isEnabled = isEnabled;
  }

  public User() {

  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }
}
