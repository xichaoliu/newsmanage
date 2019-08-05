package com.news.web.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.news.web.module.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JwtUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Integer timestamp = 12*60*60*1000; // 24小时
    Algorithm algorithm = Algorithm.HMAC256("secret");

    public String generateToken(User user) {
        Date now = new Date();
        Long expMillis = now.getTime() + timestamp;
        Date exp = new Date(expMillis);
        String jwt = JWT.create()
                .withClaim("id", user.getId())
                .withExpiresAt(exp).sign(algorithm);
        return jwt;
    }

    public Map<String, Object> parseToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        // Map<String,Claim> map =jwt.getClaims();
        // Date time = jwt.getExpiresAt();
        Map<String,Object> mp = new HashMap<>();
        // String id = map.get("id").asString();
        // mp.put("id", id);
        // mp.put("expireDate", time.toString());
        // return map.get("username").asString();
        return mp;
    }
}
