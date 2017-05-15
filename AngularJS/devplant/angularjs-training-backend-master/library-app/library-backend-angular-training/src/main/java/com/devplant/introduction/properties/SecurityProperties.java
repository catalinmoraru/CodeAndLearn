package com.devplant.introduction.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app.security")
public class SecurityProperties {

	private String privateKey;
	private String publicKey;


}
