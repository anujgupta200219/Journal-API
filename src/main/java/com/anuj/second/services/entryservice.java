package com.anuj.second.services;
import com.anuj.second.entity.entry;
import com.anuj.second.entity.user;
import com.anuj.second.repository.entityrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class entryservice {

    @Autowired
    private entityrepository repo;

    @Autowired
    private userservice users;

    @Transactional
    public void save_entry(entry d, String username){
        try{
            d.setDate(LocalDateTime.now());
            user u=users.findByUsername(username);
            entry saved=repo.save(d);
            u.getEntries().add(saved);
            users.save_existing_user(u);
        }
        catch(Exception e){
            throw new RuntimeException("Unexpected error occur "+e);
        }
    }

    public void save_entry(entry d){
        repo.save(d);
    }

        public List<entry> getAll(){
        return repo.findAll();
    }
    public Optional<entry> getById(String id){
        return repo.findById(id);
    }

    @Transactional
    public void deleteById(String id, String username){
        try {
            user u = users.findByUsername(username);
            boolean is=u.getEntries().removeIf(x -> x.getId().equals(id));
            if(is) {
                users.save_existing_user(u);
                repo.deleteById(id);
            }
        }
        catch(Exception e){
            throw new RuntimeException("Error occur while deleting entry");
        }
    }
}
