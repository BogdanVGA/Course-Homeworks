package org.curs21.demo.config;

import lombok.Data;

@Data
public class JsonPlayer {

    private Integer id;
    private String first_name;
    private String last_name;
    private String position;
    private String height;
    private String weight;
    private String jersey_number;
    private String college;
    private String country;
    private int draft_year;
    private int draft_round;
    private int draft_number;
    private JsonTeam team;
}
