package com.ruoxijun.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {

    public static final Long JWT_TTL = 60 * 60 * 1000L; // 一个小时
    public static final String JWT_ISSUER = "ruoxijun";
    public static final String JWT_KEY = "ruoxijun+ruoxijun+ruoxijun+ruoxijun+ruoxijun+ruoxijun";

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    /**
     * 生成 jwt
     *
     * @param subject   jwt 内容
     * @param ttlMillis 生效时间
     * @param uuid      uuid
     * @return jwt
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        MacAlgorithm hs256 = Jwts.SIG.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtils.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .id(uuid) //唯一的ID
                .subject(subject) // 主题可以是JSON数据
                .issuer(JWT_ISSUER) // 签发者
                .issuedAt(now) // 签发时间
                .signWith(secretKey, hs256) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .expiration(expDate);
    }

    /**
     * 解析 JWT
     *
     * @param jwt 令牌
     * @return 解析结果
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    /**
     * 生成签名
     *
     * @return 签名
     */
    public static SecretKey generalKey() {
        return Keys.hmacShaKeyFor(JwtUtils.JWT_KEY.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 获取随机 UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}