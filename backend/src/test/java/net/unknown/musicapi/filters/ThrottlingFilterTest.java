package net.unknown.musicapi.filters;

import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ThrottlingFilterTest {

    private Bucket createBucketOfCapacity(int capacity) {
        return Bucket.builder()
                .addLimit(limit -> limit.capacity(capacity).refillGreedy(capacity, Duration.ofHours(1)))
                .build();
    }

    @Test
    public void throttlingFilterDoesNotStopRequestWhenRequestsNotExceedsCallLimit() throws ServletException, IOException {
        ThrottlingFilter throttlingFilter = new ThrottlingFilter(createBucketOfCapacity(10));
        FilterChain filterChain = mock(FilterChain.class);

        throttlingFilter.doFilter(mock(ServletRequest.class), mock(ServletResponse.class), filterChain);

        verify(filterChain, times(1)).doFilter(any(), any());
    }

    @Test
    public void throttlingFilterFillsHttpResponseWithStatus429ToManyRequestsWhenRequestsExceedLimit() throws ServletException, IOException {
        ThrottlingFilter throttlingFilter = new ThrottlingFilter(createBucketOfCapacity(1));
        FilterChain filterChain = mock(FilterChain.class);
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        throttlingFilter.doFilter(mock(ServletRequest.class), servletResponse, filterChain);
        throttlingFilter.doFilter(mock(ServletRequest.class), servletResponse, filterChain);

        assertThat(servletResponse.getContentAsString()).isEqualTo("Too many requests");
        assertThat(servletResponse.getStatus()).isEqualTo(429);
        verify(filterChain, times(1)).doFilter(any(), any());
    }
}
