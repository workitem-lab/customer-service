package com.workitem.customer.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceHealthIndicator  implements HealthIndicator {

    @Override
    public Health health() {

        boolean domainOk = true;

        if (domainOk) {
            return Health.up()
                    .withDetail("service", "Available")
                    .build();
        } else {
            return Health.down()
                    .withDetail("customer-service", "Something is wrong")
                    .build();
        }
    }
}
