package userservices.userservices.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import userservices.userservices.Enitity.Rating;
import userservices.userservices.Enitity.User;
import userservices.userservices.services.UserServices;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Builder
public class UserController {

     @Autowired
     private RestTemplate restTemplate;

    private final Logger log= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServices userServices;

@PostMapping("/signup")
     public ResponseEntity<User> createuser(@RequestBody User user){
         userServices.saveNewUser(user);
          return  new ResponseEntity<>(user,HttpStatus.CREATED);
}
@GetMapping("/{id}")
@CircuitBreaker(name="rating_and_hotel", fallbackMethod = "handleRatingHotelBreaker")
@Retry(name="rating_and_hotel", fallbackMethod = "handleRatingHotelBreaker")
@RateLimiter(name="USERRATELIMTER",fallbackMethod = "handleRatingHotelBreaker")
 public ResponseEntity<User> singleUser(@PathVariable String id) {
      User user=userServices.findId(id);

      return new ResponseEntity<>(user,HttpStatus.OK);
}

  public  ResponseEntity<User> handleRatingHotelBreaker(@PathVariable String id,Exception ex ){
log.info("circuit  is break due to some reson",ex.getMessage());
  User user= new User();


     return  new ResponseEntity<>(user, HttpStatus.OK);
  }


    @GetMapping("/alluser")
 public ResponseEntity<List<User>> getAllUser(){
      List<User> allUser=userServices.findAll();
      return  new ResponseEntity<>(allUser,HttpStatus.FOUND);
 }


}
