package com.example.cambioservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cambio")
public class Cambio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "from_currency", nullable = false, length = 3)
    private String from;

    @Column(name = "to_currency", nullable = false, length = 3)
    private String to;

    @Column(nullable = false)
    private BigDecimal conversionFactor;

    @Transient
    private BigDecimal convertedValue;

    @Transient
    private String enviroment;
}
