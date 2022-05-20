package de.god.workorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    private String department_name;
}
