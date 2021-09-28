package net.unknown.musicapi.providers.itunes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletResponseWrapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class HttpClientTest {



    @Test
    public void  should_returnCorrectFetchUrl_whenGiveCorrectParams(){


        HttpClient httpClient = HttpClient.newHttpClient();


    }


}
