package com.example.ERP.System.auth.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JWTService {
    private final String SercretKey;
     JWTService(){
        this.SercretKey=generateSecretKey();
    }
    public String generateToken(String username) {

        Map<String,Object> claims=new HashMap<>();
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*3))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private String generateSecretKey() {
try{
    KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
      SecretKey secretKey=keyGenerator.generateKey();
    System.out.println("secret Key "+secretKey);
    return Base64.getEncoder().encodeToString(secretKey.getEncoded());

}catch (NoSuchAlgorithmException e){
    throw new RuntimeException("No generate SecretKey "+e);
}
    }

    private Key getKey(){
        byte[] keyByte= Decoders.BASE64.decode(SercretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }


    public String extractUsername(String token) {
         return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T > claimsTResolver) {
        final Claims claims=extractAllClaims(token);
        return claimsTResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
    return  Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
     }

    public boolean validateToken(String token, UserDetails userDetails) {
    final String userName=extractUsername(token);
    return (userName.equals(userDetails.getUsername())&&!isTokenExprired(token));

     }

    private boolean isTokenExprired(String token) {
         return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
         return extractClaims(token,Claims::getExpiration);
    }
}

