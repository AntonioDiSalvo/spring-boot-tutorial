package com.example.springboottutorial.actuator;

import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@ConditionalOnEnabledHealthIndicator("mio_check")
public class CustomHealthCheck implements HealthIndicator {
    private final Random mRandom = new Random();

    @Override
    public Health health() {
        Health.Builder myHealthBuilder = new Health.Builder();
//        if (mRandom.nextInt() % 2 == 0) {
//            return myHealthBuilder.down().build();
//        } else {
//            return myHealthBuilder.up().build();
//        }
        return myHealthBuilder.up().build();
    }
}
