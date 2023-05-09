package br.senai.sc.trunfoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class TrunfoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrunfoApiApplication.class, args);
    }

}
