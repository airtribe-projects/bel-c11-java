package org.airtribe.LearnerSystem.repository;

import org.airtribe.LearnerSystem.entity.Learner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LearnerRepositoryJdbc {

  private final JdbcTemplate jdbcTemplate;

  public LearnerRepositoryJdbc(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int save(Learner learner) {
    String sql = "INSERT INTO learners (name, username, password) VALUES (?, ?, ?)";
    return jdbcTemplate.update(sql, learner.getName(), learner.getUsername(), learner.getPassword());
  }

  public Learner findById(Long id) {
    String sql = "SELECT * FROM learners WHERE id = ?";
    RowMapper<Learner> rowMapper = (rs, rowNum) ->
        new Learner(rs.getLong("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"));
    return jdbcTemplate.queryForObject(sql, rowMapper, id);
  }
}
