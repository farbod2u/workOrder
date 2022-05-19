package de.god.workorder;

import de.god.workorder.entity.Currency;
import de.god.workorder.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorkOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkOrderApplication.class, args);
    }

    @Autowired
    private CurrencyRepository currencyRepository;

    @Bean
    public void loadData() {
        if (!currencyRepository.existsById("USD"))
            currencyRepository.save(new Currency("USD"));

        if (!currencyRepository.existsById("IRR"))
            currencyRepository.save(new Currency("IRR"));

        if (!currencyRepository.existsById("EUR"))
            currencyRepository.save(new Currency("EUR"));
    }
}
