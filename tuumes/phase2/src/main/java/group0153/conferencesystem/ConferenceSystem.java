package group0153.conferencesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A class that launches the program with the arguments specified.
 */
@SpringBootApplication
public class ConferenceSystem {

    /**
     * Run Spring with specified arguments.
     *
     * @param args arguments specified when program is launched
     */
    public static void main(String[] args) {
        SpringApplication.run(ConferenceSystem.class, args);
    }
}
