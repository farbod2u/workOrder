package de.god.workorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Saeed Safaeian
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_currency")
public class Currency {

    @Id
    @Column(length = 3)
    private String symbole;
}
