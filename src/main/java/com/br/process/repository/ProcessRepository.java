package com.br.process.repository;
import com.br.process.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProcessRepository extends JpaRepository<Process, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM process WHERE lower(name_requester) LIKE lower(?1) AND deleted = false ORDER BY created_at DESC OFFSET ?2 LIMIT ?3")
    List<Process> findAllByCriteria(String search_criteria, int asked_page, int items_per_page);
}
