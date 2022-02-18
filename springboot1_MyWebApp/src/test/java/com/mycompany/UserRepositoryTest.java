package com.mycompany;

import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;




@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("NEw12W@gmail.com");
        user.setPassword("123456");
        user.setFistName("Joy");
        user.setLastNmae("Jose");

        User savedUser = repo.save(user);

        Assertions.assertNotNull(savedUser);
    }

    @Test
    public void testListAll(){
        Iterable<User> users= repo.findAll();;
        for(User user:users){
            System.out.println(user);
        }
    }

}
