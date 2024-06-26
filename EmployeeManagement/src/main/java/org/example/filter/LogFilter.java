package org.example.filter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "logFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(LogFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    long startTime = System.currentTimeMillis();
    logger.info("I am in log filter pre-processing at {}",startTime);
    filterChain.doFilter(servletRequest, servletResponse);
    long endTime = System.currentTimeMillis();
    logger.info("I am in log filter post-processingat {}",endTime);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
