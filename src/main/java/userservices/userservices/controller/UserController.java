package userservices.userservices.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservices.userservices.Enitity.User;
import userservices.userservices.services.UserServices;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

@PostMapping("/signup")
     public ResponseEntity<User> createuser(@RequestBody User user){
         userServices.saveNewUser(user);
          return  new ResponseEntity<>(user,HttpStatus.CREATED);
}
@GetMapping("/{id}")
 public ResponseEntity<User> singleUser(@PathVariable ObjectId id) {
      User user=userServices.findId(id);
      return new ResponseEntity<>(HttpStatus.OK);
}
@GetMapping("/alluser")
 public ResponseEntity<List<User>> getAllUser(){
      List<User> allUser=userServices.findAll();
      return  new ResponseEntity<>(allUser,HttpStatus.FOUND);
 }


}
