package com.otaku.rating.core.user.model.properties.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailProperties {
    private String username;
}
