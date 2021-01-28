package de.amirrocker.hadesGatekeeper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.amirrocker.hadesGatekeeper.cqs.Bus;
import de.amirrocker.hadesGatekeeper.cqs.Registry;
import de.amirrocker.hadesGatekeeper.cqs.SpringBus;
import de.amirrocker.hadesGatekeeper.domain.json.BooleanSerializer;
import de.amirrocker.hadesGatekeeper.init.InitDatabase;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.broker.jmx.MBeanInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@ComponentScan(basePackages = "de.amirrocker")
@EnableWebSecurity
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
@SpringBootApplication
public class HadesGatekeeperApplication implements ApplicationRunner {

    private final InitDatabase dbInitializer;

    public HadesGatekeeperApplication(InitDatabase dbInitializer) {
        this.dbInitializer = dbInitializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(HadesGatekeeperApplication.class, args);
    }

    @Bean
    public Registry registry(ApplicationContext applicationContext) {
        return new Registry(applicationContext);
    }

    @Bean
    public Bus commandBus(Registry registry) {
        return new SpringBus(registry);
    }

    @Override
    public void run(ApplicationArguments args) {
        dbInitializer.init();
    }

}
