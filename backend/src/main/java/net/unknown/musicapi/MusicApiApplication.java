package net.unknown.musicapi;

import net.unknown.musicapi.providers.itunes.HttpClientItunesApi;
import net.unknown.musicapi.persistence.models.User;
import net.unknown.musicapi.persistence.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MusicApiApplication {

	@Autowired
	private HttpClientItunesApi httpClientItunesApi;

	public static void main(String[] args) {
		SpringApplication.run(MusicApiApplication.class, args);
	}
}
