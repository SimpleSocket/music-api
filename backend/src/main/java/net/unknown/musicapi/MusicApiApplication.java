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
public class MusicApiApplication  implements CommandLineRunner {

	@Autowired
	private HttpClientItunesApi httpClientItunesApi;

	public static void main(String[] args) {
		SpringApplication.run(MusicApiApplication.class, args);
	}

	@Autowired
	UserRepo userRepo;

	@Override
	public void run(String... args) throws Exception {

//		httpClientItunesApi.getTopArtistAlbums("4560");
//		httpClientItunesApi.searchArtist("abba");
//
//		System.out.println();
//		Album album = new Album(100L, "Stars");
//		Album album1 = new Album(101L, "Stars2");
//		albumRepo.save(album);
//		albumRepo.save(album1);
//
//
		User user = new User(1005L);
		User user6 = new User(1006L);
		userRepo.save(user);
		userRepo.save(user6);
//
//		Artist artist = new Artist(2L,"Abba");
//		artistRepo.save(artist);
//
//
//		user.addArtist(artist);
//		artist.addUser(user);
//		album.setArtist(artist);
//
//		user6.addArtist(artist);
//		artist.addUser(user6);
//
//		artistRepo.save(artist);
//		userRepo.save(user);
//		userRepo.save(user6);
//		albumRepo.save(album);


	}
}
