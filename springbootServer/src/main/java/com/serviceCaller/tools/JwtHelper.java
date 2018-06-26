package com.serviceCaller.tools;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 构造及解析jwt的工具类
 */
public class JwtHelper {


    private final static Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    /**
     *@Author: Shixiong
     *@Description:
     *@param jsonWebToken:The input String for analyzing
     *@param base64Security : The key for decode
     *@Date: 2018/6/5
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security){
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }
        catch(Exception ex)
        {
            logger.error("Token authorization failed,target token:{}",jsonWebToken);
            return null;
        }
    }
    /**
     * @Author: Shixiong
     * @Description:The tool for jwt string creation
     * @param name  userName should be encode into token string
     * @param audience param of jwtTool
     * @param issuer   param of jwtTool
     * @param TTLMillis  Set the expire date of token
     * @param base64Security: param of jwtTool
     * @Date: 2018/6/5
     */
    public static String createJWT(String name,String audience, String issuer, long TTLMillis, String base64Security)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("userName", name)
                .setIssuer(issuer)
                .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }
}