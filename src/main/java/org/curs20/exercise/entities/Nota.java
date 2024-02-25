package org.curs20.exercise.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "note")
@Data
public class Nota {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nota")
    private int nota;

    @Column(name = "data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_materie")
    private Materie materie;
}

/* If you choose to use java.util.Date, you can annotate the date field with @Temporal(TemporalType.DATE),
 * which instructs Hibernate to map the field to a SQL DATE type in the database.
 * If you're using Java 8 or later, you can use java.time.LocalDate and annotate the date field
 * with @Column(name = "date"), without @Temporal annotation. */
