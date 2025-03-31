package org.airtribe.LearnerSystem.repository;

import org.airtribe.LearnerSystem.entity.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CohortRepository extends JpaRepository<Cohort, Long> {

}
