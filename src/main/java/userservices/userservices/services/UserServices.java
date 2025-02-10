package userservices.userservices.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import userservices.userservices.Enitity.User;
import userservices.userservices.Repositorary.UserRepositorary;

import java.util.List;

@Component
public class UserServices {

     @Autowired
     private UserRepositorary userRepositorary;

     public  boolean saveNewUser(User user){
           userRepositorary.save(user);
            return  true;

     }

      public User findId(ObjectId id)  {
         return  userRepositorary.findById(id).orElseThrow(()-> new ResourceAccessException("no user found "));
      }

      public List<User> findAll(){
        return userRepositorary.findAll();
      }


}
