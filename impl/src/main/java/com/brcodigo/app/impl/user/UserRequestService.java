package com.brcodigo.app.impl.user;

import com.brcodigo.app.impl.user.model.UserAuthModel;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@AllArgsConstructor
public class UserRequestService {

    private static final String X_API_KEY = "X-API-KEY";
    private static final String X_API_SUBSIDIARY = "X-API-SUBSIDIARY";

    private final UserService1 userService1;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserAuthModel currentUser() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtils.isEmpty(sra)) {
            return UserAuthModel.builder().build();
        } else if (ObjectUtils.isEmpty(sra.getRequest().getHeader(X_API_KEY))) {
            return UserAuthModel.builder().build();
        } else {
            try {
                return userService1.getAuth(sra.getRequest().getHeader(X_API_KEY), sra.getRequest().getHeader(X_API_SUBSIDIARY));
            } catch (Exception error) {
                return UserAuthModel.builder().build();
            }
        }
    }

}
