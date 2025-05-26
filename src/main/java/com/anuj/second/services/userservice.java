package com.anuj.second.services;
import com.anuj.second.entity.user;
import com.anuj.second.repository.userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class userservice {

    @Autowired
    private userrepository repo;

    private static final PasswordEncoder encode=new BCryptPasswordEncoder();

    public void save_new_user(user d){
        d.setPassword(encode.encode(d.getPassword()));
        d.setRoles(Arrays.asList("USER"));
        repo.save(d);
    }

    public void save_admin(user d){
        d.setPassword(encode.encode(d.getPassword()));
        d.setRoles(Arrays.asList("USER","ADMIN"));
        repo.save(d);
    }

    public void save_existing_user(user d){
        repo.save(d);
    }

    public List<user> getAll(){
        return repo.findAll();
    }

    public void deleteById(String id){
        repo.deleteById(id);
    }

    public user findByUsername(String user){return repo.findByusername(user);}
}
