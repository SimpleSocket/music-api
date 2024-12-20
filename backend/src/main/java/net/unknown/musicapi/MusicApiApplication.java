package net.unknown.musicapi;

import net.unknown.musicapi.clients.ItunesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MusicApiApplication {

    @Autowired
    private ItunesClient itunesClient;

    public static void main(String[] args) {
        SpringApplication.run(MusicApiApplication.class, args);
    }
}
