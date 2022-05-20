package de.god.workorder.repository;

import de.god.workorder.entity.WorkOrderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderTypeRepository extends JpaRepository<WorkOrderType, String> {
}
