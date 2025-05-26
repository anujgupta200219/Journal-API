package com.anuj.second.service;

import com.anuj.second.entity.user;
import com.anuj.second.repository.userrepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class userservicetests {

    @Autowired
    private userrepository repo;

    @Disabled
    @Test
    public void testadd(){
        assertNotNull(repo.findByusername("Ram"));
        user user = repo.findByusername("Ram");
        assertTrue(!user.getEntries().isEmpty());
    }

//    @ParameterizedTest
//    @CsvSource({
//            "1,2,3",
//            "2,1,1",
//            "4,1,5"
//    })
//    public void test(int a,int b,int expected){
//        assertEquals(expected,a+b,"failed for "+a+" "+b+" "+expected);
//    }
}
