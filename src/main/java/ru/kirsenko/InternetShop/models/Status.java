package ru.kirsenko.InternetShop.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="status_table")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "status")//(mappedBy)Группа связанная с товаром будет записана в foreign key  в таблице images
    private List<SalesTable> salesTables = new ArrayList<>();
}
