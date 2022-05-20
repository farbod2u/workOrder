package de.god.workorder.repository;

import de.god.workorder.entity.WorkOrderType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Saeed Safaeian
 */
public interface WorkOrderTypeRepository extends JpaRepository<WorkOrderType, String> {
}
