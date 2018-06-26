package com.serviceCaller.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/*
 * 自定义配置文件的解析类
 */
@Component
@PropertySource("classpath:/config/jwt.properties")
public class JwtInfo {
    @Value("${jwt.info.clientId}")
    private String clientId;
    @Value("${jwt.info.base64Secret}")
    private String base64Secret;
    @Value("${jwt.info.name}")
    private String name;
    @Value("${jwt.info.expiresSecond}")
    private int expiresSecond;
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getBase64Secret() {
        return base64Secret;
    }
    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getExpiresSecond() {
        return expiresSecond;
    }
    public void setExpiresSecond(int expiresSecond) {
        this.expiresSecond = expiresSecond;
    }

}
