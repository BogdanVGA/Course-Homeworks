package org.curs21.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "players")
@Data
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "play_pos")
    private String position;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "jersey_number")
    private String jerseyNumber;

    @Column(name = "college")
    private String college;

    @Column(name = "country")
    private String country;

    @ManyToOne()
    @JoinColumn(name = "team_id")
    private Team team;
}
