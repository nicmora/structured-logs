package pe.seek.structuredlogs.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import pe.seek.structuredlogs.model.User;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static net.logstash.logback.argument.StructuredArguments.v;

@Service
@Slf4j
public class UserService {

    public void getAll() {
        log.trace("Esto es un mensaje de TRACE");
        log.debug("Esto es un mensaje de DEBUG");
        log.info("Esto es un mensaje de INFO");
        log.warn("Esto es un mensaje de WARN");
        log.error("Esto es un mensaje de ERROR");

        User user = getUserExample();
        log.info("Esto es un mensaje con información del {} con ID={}", kv("user", user), v("dni", user.getId()));

        MDC.put("user", user.toString());
        MDC.put("id", user.getId().toString());
        log.info("Esto es un mensaje con MDC");
        MDC.clear();
    }

    private User getUserExample() {
        return User.builder()
                .id(1L)
                .username("seeker")
                .email("seeker@seek.pe")
                .enabled(true)
                .build();
    }

}

