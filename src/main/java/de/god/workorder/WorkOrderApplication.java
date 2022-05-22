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

    /*@Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private WorkOrderTypeRepository orderTypeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Bean
    public void loadData() {
        if (!currencyRepository.existsById("USD"))
            currencyRepository.save(new Currency("USD"));
        if (!currencyRepository.existsById("IRR"))
            currencyRepository.save(new Currency("IRR"));
        if (!currencyRepository.existsById("EUR"))
            currencyRepository.save(new Currency("EUR"));

        if(!orderTypeRepository.existsById("ANALYSIS"))
            orderTypeRepository.save(new WorkOrderType("ANALYSIS"));
        if(!orderTypeRepository.existsById("REPAIR"))
            orderTypeRepository.save(new WorkOrderType("REPAIR"));
        if(!orderTypeRepository.existsById("REPLACEMENT"))
            orderTypeRepository.save(new WorkOrderType("REPLACEMENT"));

        if(!departmentRepository.existsById("GOoD analysis department"))
            departmentRepository.save(new Department("GOoD analysis department"));
       if(!departmentRepository.existsById("GOoD repair department"))
            departmentRepository.save(new Department("GOoD repair department"));
       if(!departmentRepository.existsById("GOoD replacement department"))
            departmentRepository.save(new Department("GOoD replacement department"));
    }*/
}
