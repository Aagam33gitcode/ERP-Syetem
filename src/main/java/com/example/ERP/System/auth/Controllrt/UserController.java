package com.example.ERP.System.auth.Controllrt;

import com.example.ERP.System.auth.Entities.UserEntity;
import com.example.ERP.System.auth.Service.JWTService;
import com.example.ERP.System.auth.Service.UserService;
import org.apache.catalina.Manager;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService service;
private final AuthenticationManager manager;
    private final JWTService jwtService;
    public UserController(UserService service, AuthenticationManager manager, JWTService jwtService) {
        this.service = service;
        this.manager = manager;
        this.jwtService = jwtService;
    }

    @PostMapping("/api/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user){
        UserEntity entity= service.createRegister(user);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
    @PostMapping("/api/login")
    public ResponseEntity<String> Login(@RequestBody UserEntity user){
        Authentication authentication= manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
//            String user1= jwtService.generateToken(user.getUsername());
            UserEntity authenticatedUser = (UserEntity) authentication.getPrincipal();
            String token = jwtService.generateToken(authenticatedUser.getUsername());
        return new ResponseEntity<>(token,HttpStatus.OK);
        }
        return new ResponseEntity<>("not token generated",HttpStatus.NOT_FOUND);
    }
}
