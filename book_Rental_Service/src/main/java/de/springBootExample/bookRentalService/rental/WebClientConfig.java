package de.springBootExample.bookRentalService.rental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * The WebClientConfig class provides a configuration for creating a WebClient bean.
 * This WebClient can be used to make HTTP requests to external services.
 */
@Configuration
public class WebClientConfig {

    /**
     * This method creates a WebClient.Builder bean.
     * The WebClient can be used to make HTTP requests to external services.
     *
     * @return a WebClient.Builder instance
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}