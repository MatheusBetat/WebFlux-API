package com.sprint05.contract.controller.v1.user;

import com.sprint05.contract.controller.v1.user.facade.UserControllerFacade;
import com.sprint05.contract.controller.v1.user.model.request.UserControllerRequest;
import com.sprint05.contract.controller.v1.user.model.response.UserControllerResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserControllerFacade userControllerFacade;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public Mono<UserControllerResponse> getUser(@PathVariable String id) {
        return userControllerFacade.getUser(id);
    }

    @GetMapping("/all")
    @ResponseStatus(OK)
    public Flux<UserControllerResponse> getAllUsers(){
        return userControllerFacade.getAllUser();

    }

    @PostMapping("/create")
    public Mono<UserControllerResponse> saveUser(@Valid @RequestBody UserControllerRequest userControllerRequest){
        return userControllerFacade.saveUser(userControllerRequest);

    }

    @PatchMapping("/setmovie")
    public Mono<UserControllerResponse> setMyMovie(@RequestParam String id, String movieId) {
        return userControllerFacade.setMyMovie(id, movieId);

    }

    @DeleteMapping("/delete")
    public Mono<Void> deleteUserByIds(@RequestParam List<String> ids){
        return userControllerFacade.deleteUserByIds(ids);

    }
}
