package userservices.userservices.services;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import userservices.userservices.Enitity.Hotel;
import userservices.userservices.Enitity.Rating;
import userservices.userservices.Enitity.User;
import userservices.userservices.Repositorary.UserRepositorary;
import userservices.userservices.controller.UserController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServices {

     @Autowired
     private UserRepositorary userRepositorary;

     @Autowired
    private RestTemplate restTemplate;

   private final Logger log=LoggerFactory.getLogger(UserServices.class);

     public  boolean saveNewUser(User user){
           userRepositorary.save(user);
            return  true;

     }

    public User findId(String id) {
        // Find user by id
        User user = userRepositorary.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No user found with id: " + id));


        Rating[] ratingArray = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getId(), Rating[].class);
        log.info("Ratings of user: {}", Arrays.toString(ratingArray));

//        Rating[] ratingArray= restTemplate.getForObject("http://RATING-SERVICES/rating/user/" + user.getId(),Rating[].class);


        List<Rating> ratingsWithHotels = Arrays.stream(ratingArray)
                .map(rating -> {
                    Hotel hotel = null;
                    try {

                        hotel = restTemplate.getForObject("http://HOTEL/hotels/id/" + rating.getHotelId(), Hotel.class);
                    } catch (RestClientException e) {
                        log.error("Failed to fetch hotel for hotelId: {}", rating.getHotelId(), e);
                    }


                    rating.setHotel(hotel);
                    return rating;
                }).collect(Collectors.toList());


        user.setRating(ratingsWithHotels);

        return user;
    }


    public List<User> findAll(){
        return userRepositorary.findAll();
      }


}
