package de.god.workorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Saeed Safaeian
 */
@Entity
@Table(name = "tbl_validation_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp receive_date;

    private String type;

    private String department;

    @Enumerated(EnumType.STRING)
    private StatusType status;

    public enum StatusType{
        VALID,
        INVALID
    }
}
