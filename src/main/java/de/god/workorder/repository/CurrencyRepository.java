package de.god.workorder.repository;

import de.god.workorder.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Saeed Safaeian
 */
public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
