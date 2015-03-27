package com.verifone.ums.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Pavel Mikhalchuk
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestBenchLogger implements Filter {

    private static final Logger log = LoggerFactory.getLogger("HTTP:BENCH");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (log.isInfoEnabled() && request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            long start = System.currentTimeMillis();

            try {
                chain.doFilter(request, response);
            } finally {
                long end = System.currentTimeMillis();

                log(start, end, (HttpServletRequest) request, (HttpServletResponse) response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void log(long start, long end, HttpServletRequest request, HttpServletResponse response) {
        StringBuilder b = new StringBuilder();
        b.append(request.getRemoteAddr());
        b.append(": millis=");
        b.append(end - start);
        b.append(" q={");
        b.append(request.getMethod());
        b.append(" ");
        b.append(request.getRequestURI());
        if (request.getQueryString() != null) {
            b.append("?").append(request.getQueryString());
        }
        b.append("} ");
        b.append(response.getStatus());

        log.info(b.toString());
    }

    @Override
    public void destroy() {
    }

}