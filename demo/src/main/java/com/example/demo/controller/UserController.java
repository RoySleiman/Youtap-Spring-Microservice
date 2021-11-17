package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {



    @GetMapping("/getusercontacts")
    public List<UserResponse>  getUser(@RequestParam(required = false) Integer id , @RequestParam(required = false) String username )
    {
        Mono<User[]> response = WebClient.create()
                .get()
                .uri("https://jsonplaceholder.typicode.com/users")
                .retrieve()
                .bodyToMono(User[].class).log();

        User[] objects = response.block();

        List<UserResponse> result= new ArrayList<>();

        for(int i = 0 ; i < objects.length ; i++)
        {
            if ( (id != null && id.equals(objects[i].id) ) ||  (username != null && username.equals(objects[i].username)) )
                result.add( new UserResponse(objects[i])  );

        }

        if(result.size() == 0)
            result.add( new UserResponse(-1)  );

        return result;

    }
}
