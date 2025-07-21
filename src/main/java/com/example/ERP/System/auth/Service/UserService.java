package com.example.ERP.System.auth.Service;

import com.example.ERP.System.HR.ExceptionHandling.ResourceNotFoundException;
import com.example.ERP.System.auth.Entities.RoleEntity;
import com.example.ERP.System.auth.Entities.UserEntity;
import com.example.ERP.System.auth.Repository.RoleRespository;
import com.example.ERP.System.auth.Repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
private final BCryptPasswordEncoder encoder;


private final RoleRespository roleRespository;

    public UserService(UserRepository userRepo, @Lazy BCryptPasswordEncoder encoder, RoleRespository roleRespository) {
        this.userRepo = userRepo;
        this.encoder = encoder;

        this.roleRespository = roleRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     return userRepo.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("user with username not  found "+username));

    }

    public UserEntity createRegister(UserEntity user) {
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            throw new ResourceNotFoundException.ResourceAlreadyExistsException("user is already exist "+user.getUsername());
        }  Set<RoleEntity> managedRoles = user
                .getRoles()
                .stream()
                .map(role -> roleRespository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + role.getName())))
                .collect(Collectors.toSet());

        user.setRoles(managedRoles);
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return userRepo.save(user);
    }
}
