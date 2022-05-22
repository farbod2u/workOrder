package de.god.workorder;

import de.god.workorder.entity.Currency;
import de.god.workorder.entity.Department;
import de.god.workorder.entity.WorkOrderType;
import de.god.workorder.repository.CurrencyRepository;
import de.god.workorder.repository.DepartmentRepository;
import de.god.workorder.repository.WorkOrderTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * @author Saeed Safaeian
 */
@SpringBootApplication
public class WorkOrderApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WorkOrderApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WorkOrderApplication.class, args);
    }

}
