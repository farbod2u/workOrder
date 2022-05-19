package de.god.workorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private Timestamp start_date;

    @Column(nullable = false)
    private Timestamp end_date;

    @ManyToOne
    @JoinColumn(name = "currency")
    private Currency currency;

    private Double cost;

    private Timestamp analysis_date;

    private Timestamp test_date;

    private String responsible_person;

    private String factory_name;

    @Column(length = 10)
    private String factory_order_number;

    @OneToMany
    @JoinColumn(name = "orderId")
    private List<Part> parts;

    public enum OrderType {
        ANALYSIS,
        REPAIR,
        REPLACEMENT
    }
}
