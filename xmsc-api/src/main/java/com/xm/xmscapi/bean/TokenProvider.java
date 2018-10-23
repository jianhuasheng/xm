package com.xm.xmscapi.bean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * token生成器
 */
@Slf4j
public class TokenProvider {
    private static final String SECRET_KEY = "asdfghjkwertyuioxcvbnm";
    private static final long TOKEN_VALID_SECONDS = 1000 * 60 * 60 * 24 * 10L;


    /**
     * 生成token, 其实只需要用户id, 其他信息可以作为辅助验证
     *
     * @param user
     * @return
     */
    public static String createToken(JwtUser user) {
        long validity = System.currentTimeMillis() + TOKEN_VALID_SECONDS;
        return Jwts.builder()
                .setSubject(String.valueOf(user.getAccountId()))
//                .claim(CommonConstant.VERSION, String.valueOf(version)) // 辅助信息
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(new Date(validity))
                .compact();
    }

    public static void main(String[] args) throws InterruptedException {
        JwtUser user = new JwtUser();
        user.setAccountId(1258963545);

        System.out.println(createToken(user));
    }

    /**
     * 能获取到即是可用
     *
     * @param token
     * @return
     */
    public static JwtUser getAuthentication(String token) {
        JwtUser user = new JwtUser();

        if (StringUtils.isEmpty(token)) {
            return user;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            int accountId = Integer.valueOf(claims.getSubject());

            user.setAccountId(accountId);

        } catch (ExpiredJwtException e) {
            log.info("JWT token 已过期" + token);
        } catch (Exception e) {
            log.info("JWT token 解析失败" + token, e);
        }
        return user;
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.info("JWT token 已过期" + token);
        } catch (Exception e) {
            log.info("JWT token 验证失败" + token, e);
        }
        return false;
    }
}
