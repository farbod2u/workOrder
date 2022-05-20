package de.god.workorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrder {
    
    private String type;

    private String department;

    private LocalDate start_date;

    private LocalDate end_date;

    private String currency;

    private Double cost;

    private LocalDate analysis_date;

    private LocalDate test_date;

    private String responsible_person;

    private String factory_name;

    private String factory_order_number;

    private List<Part> parts;

}
