package com.bicho.bet.cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;
@Component
public class RateLimitInterceptor extends HandlerInterceptorAdapter {
    private static final int MAX_REQUESTS_PER_HOUR = 2;
    private final RedisTemplate<String, String> redisTemplate;
    @Autowired
    public RateLimitInterceptor(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientId = getClientId(request);
        String requestCountKey = clientId + ":requests";
        Long requests = redisTemplate.opsForValue().increment(requestCountKey, 1);
        if (requests == 1) {
            redisTemplate.expire(requestCountKey, 1, TimeUnit.HOURS);
        }
        if (requests > MAX_REQUESTS_PER_HOUR) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            return false;
        }
        return true;
    }
    private String getClientId(HttpServletRequest request) {
        return request.getRemoteAddr();
    }
}





