package org.killer.t0datafetch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.killer.t0datafetch", "org.killer.t0listedcompany", "org.killer.t0sharedata"})
public class T0DatasFetchApplication {

    public static void main(String[] args) {
        SpringApplication.run(T0DatasFetchApplication.class, args);
    }

}
