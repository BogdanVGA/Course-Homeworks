package org.curs21.demo.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;

@Data
public class JsonPlayerList {

    private ArrayList<JsonPlayer> data;
    @JsonIgnore
    private String meta;
}
