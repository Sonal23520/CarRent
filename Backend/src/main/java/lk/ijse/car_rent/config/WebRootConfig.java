package lk.ijse.car_rent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({lk.ijse.car_rent.config.JPAConfig.class})
public class WebRootConfig {

}
