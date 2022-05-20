package de.god.workorder.repository;

import de.god.workorder.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Saeed Safaeian
 */
public interface DepartmentRepository extends JpaRepository<Department, String> {
}
