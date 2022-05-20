package de.god.workorder.repository;

import de.god.workorder.entity.ValidationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationHistoryRepository extends JpaRepository<ValidationHistory, Long> {
}
