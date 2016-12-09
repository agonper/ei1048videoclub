package es.uji.agdc.videoclub.views;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Created by daniel on 9/12/16.
 */

@Configuration
@Lazy
public class ScreenFactory {

    @Bean
    public AuthScreen authScreen() {
        return new AuthScreen();
    }


}
