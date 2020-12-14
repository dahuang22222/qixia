package com.changjianghuyu.qixia.web.common.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class JwtUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);
        /** 存放token的请求头对应的key的名字 */
    private static String headerKey = "token";
        /** 加密的secret */
    private static String secret = "%changjianghuyu&*";
        /** 过期时间，单位为秒 */
    private static long expire = 2400L;
    static {
        // TODO 上面变量的值应该从配置文件中读取,方便测试这里就不从配置文件中读取
        // 利用配置文件中的值覆盖静态变量初始化的值
    }
        /**
         * 生成jwt token
         */
    public static String generateToken(Map<String, Object> userInfoMap) {
        if (userInfoMap == null || userInfoMap.isEmpty()) {
            userInfoMap = new HashMap<String, Object>();
        }
        //  过期时间
//        Date expireDate = new Date(System.currentTimeMillis() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")   // 设置头部信息
                .setClaims(userInfoMap)               // 装入自定义的用户信息
                /*.setExpiration(new Date(System.currentTimeMillis() + 100)) */           // token过期时间(暂时不设置过期时间)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

        /**
         * 校验token并解析token
         * @param token
         * @return Claims：它继承了Map,而且里面存放了生成token时放入的用户信息
         */
    public static Claims verifyAndGetClaimsByToken(String token) {
        try {
            /* 如果过期或者是被篡改，则会抛异常.
             注意点：只有在生成token设置了过期时间(setExpiration(expireDate))才会校验是否过期，
             可以参考源码io.jsonwebtoken.impl.DefaultJwtParser的299行。
             拓展：利置用不设过期时间就不校验token是否过期的这一特性，我们不设置Expiration;
                   而采用自定义的字段来存放过期时间放在Claims（可以简单的理解为map）中;
                   通过token获取到Claims后自己写代码校验是否过期。
                   通过这思路，可以去实现对过期token的手动刷新
            */
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            LOG.debug("verify token error:[{}] ", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static String getHeaderKey() {
        return headerKey;
    }

    public static void main(String[] args) {
        //token的map
        HashMap<String, Object> TokenMap = new HashMap<String, Object>();
        TokenMap.put("id", "11");
        TokenMap.put("phone", "13645244921");
        //添加用户token

        String mytoken = JwtUtils.generateToken(TokenMap);
//        String mytoken = JwtUtils.generateToken(TokenMap);
        System.out.println("mytoken:"+mytoken);
//        Claims claims = JwtUtils.verifyAndGetClaimsByToken(mytoken);
//        System.out.println("claims:"+claims);
//        //立马过期
//        Date expireDate = new Date(System.currentTimeMillis() + 100);
//        //删除元素
//        Jwts.claims(TokenMap).clear();
        Claims claims2 = JwtUtils.verifyAndGetClaimsByToken(mytoken);
        System.out.println("claims:"+claims2);
    }
}