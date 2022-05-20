package de.god.workorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_order_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderType {

    @Id
    private String order_type_name;
}
