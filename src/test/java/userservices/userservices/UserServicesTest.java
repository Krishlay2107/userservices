package userservices.userservices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import userservices.userservices.Enitity.User;
import userservices.userservices.Repositorary.UserRepositorary;

import static org.bson.assertions.Assertions.assertNotNull;

@SpringBootTest
public class UserServicesTest {

    @Autowired
    private UserRepositorary userRepositorary;

@Test
     public void  findByUserId( ){
         assertNotNull(userRepositorary.findById("67aa46cf8294b90995e840ba"));
     }
}
