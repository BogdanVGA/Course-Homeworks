package org.curs21.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Configuration
@ComponentScan("org.curs21.demo")
public class InitConfig {

    @Bean
    public JsonPlayerList players() throws IOException {
        URL urlObj = new URL("https://api.balldontlie.io/v1/players");
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "db2ffd57-2713-46c6-a443-0aeb5bbd4bcb");

        System.out.println("Connection URL: " + connection.getURL());
        int test = connection.getResponseCode();
        InputStream connectionInputStream = connection.getInputStream();
        System.out.println("HTTP Response code: " + test);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(connectionInputStream, JsonPlayerList.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

// https://jenkov.com/tutorials/java-json/jackson-objectmapper.html#read-object-from-json-inputstream