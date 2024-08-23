package net.unknown.musicapi.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.unknown.musicapi.persistence.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserIdFilterTest {

    private UserRepo userRepo;
    private UserIdFilter userIdFilter;


    @BeforeEach
    public void setup() {
        this.userRepo = mock(UserRepo.class);
        this.userIdFilter = new UserIdFilter(userRepo);
    }

    @Test
    public void whenUserExistsTheRequestIsForwarded() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getHeader(eq("Authorization"))).thenReturn("1");
        when(userRepo.existsById(eq(1L))).thenReturn(true);
        FilterChain filterChain = mock(FilterChain.class);

        userIdFilter.doFilter(httpServletRequest, mock(HttpServletResponse.class), filterChain);

        verify(filterChain, times(1)).doFilter(any(), any());
    }

    @Test
    public void whenUserDoesNotExistsNotAuthorizedStatusIsReturned() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getHeader(eq("Authorization"))).thenReturn("1");
        when(userRepo.existsById(any())).thenReturn(false);
        FilterChain filterChain = mock(FilterChain.class);
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        userIdFilter.doFilter(httpServletRequest, servletResponse, filterChain);

        assertThat(servletResponse.getStatus()).isEqualTo(401);
        verify(filterChain, times(0)).doFilter(any(), any());
    }

    @Test
    public void whenAuthorizationHeaderValueIsNotANumberNotAuthorizedStatusIsReturned() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getHeader(eq("Authorization"))).thenReturn("notANumber");
        FilterChain filterChain = mock(FilterChain.class);
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        userIdFilter.doFilter(httpServletRequest, servletResponse, filterChain);

        assertThat(servletResponse.getStatus()).isEqualTo(401);
        verify(filterChain, times(0)).doFilter(any(), any());
    }

    @Test
    public void whenAuthorizedHeaderIsNotPresentInTheRequestNotAuthorizedStatusIsReturned() throws ServletException, IOException {
        FilterChain filterChain = mock(FilterChain.class);
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        userIdFilter.doFilter(mock(HttpServletRequest.class), servletResponse, filterChain);

        assertThat(servletResponse.getStatus()).isEqualTo(401);
        verify(filterChain, times(0)).doFilter(any(), any());
    }

}
