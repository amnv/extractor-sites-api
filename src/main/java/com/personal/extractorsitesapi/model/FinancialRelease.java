package com.personal.extractorsitesapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "financial_release")
@Getter @Setter @NoArgsConstructor
public class FinancialReleases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @NotNull
    private String description;

    @Column(name = "due_date")
    @NotNull
    private LocalDate dueDate;

    @Column(name = "pay_date")
    private LocalDate payDate;

    @NotNull
    private BigDecimal amount;

    private String obs;

    @NotNull
    private Type type;

    @ManyToOne
    @JoinColumn(name = "category_fk")
    @NotNull
    private Category category;

    @ManyToOne
    @JoinColumn(name = "person_fk")
    @NotNull
    private Person person;
}
