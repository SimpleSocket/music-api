package net.unknown.musicapi.controllers.advices;

import net.unknown.musicapi.exceptions.ApiRequestFailed;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AlbumApiException {
    @ExceptionHandler({ApiRequestFailed.class})
    public final ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
