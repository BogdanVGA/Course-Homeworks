package org.curs20.exercise.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "materii")
@Data
public class Materie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nume")
    private String nume;

    @ManyToMany(mappedBy = "cursuriAlese", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student> studentiInrolati;

    @OneToMany(mappedBy = "materie")
    @JsonIgnore
    private Set<StudentiToMaterie> inscrieri;

    @OneToMany(mappedBy = "materie")
    @JsonIgnore
    private Set<Nota> note;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Materie materie = (Materie) obj;
        return id.equals(materie.id) && nume.equals(materie.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume);
    }
}
