package gift.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.Builder;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient(Builder builder) {
        return builder.build();
    }
}
