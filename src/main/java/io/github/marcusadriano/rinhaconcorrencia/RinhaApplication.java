package io.github.marcusadriano.rinhaconcorrencia;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_FLOAT_AS_INT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@SpringBootApplication
public class RinhaApplication implements InitializingBean {

    private final ObjectMapper mapper;

    public RinhaApplication(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(ACCEPT_FLOAT_AS_INT, false);
    }

    public static void main(String[] args) {
        SpringApplication.run(RinhaApplication.class, args);
    }
}
