package com.devplant.introduction.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("admin.user")
public class AdminUserProperties {

	private String username;

	private String password;

}
