package org.airtribe.LearnerSystem.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.airtribe.LearnerSystem.entity.Learner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class LearnerRepositoryManual {

    private final DriverManagerDataSource dataSource;

    @Autowired
    public LearnerRepositoryManual(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Save a learner
    public int save(Learner learner) throws SQLException {
        String sql = "INSERT INTO learners (name, username, password) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, learner.getName());
            pstmt.setString(2, learner.getUsername());
            pstmt.setString(3, learner.getPassword());

            return pstmt.executeUpdate();
        }
    }

    // Find a learner by ID
    public Learner findById(Long id) throws SQLException {
        String sql = "SELECT * FROM learners WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Learner(rs.getLong("id"), rs.getString("name"),
                            rs.getString("username"), rs.getString("password"));
                }
            }
        }
        return null;
    }
}
