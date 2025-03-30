package org.airtribe.LearnerSystem.repository;

import org.airtribe.LearnerSystem.entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
  @Query("SELECT l FROM Learner l WHERE l.name = :learnerName")
  Learner findName(String learnerName);
}
