package group0153.conferencesystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * A configuration class for the Maven and Spring frameworks that run the GUI.
 */
@Configuration
public class WebConfig {
    /**
     * Return a created WebMvcConfigurer instance configured to prepare the program to run.
     *
     * @return an instance of WebMvcConfigurer that is set up to work with the registry provided
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
