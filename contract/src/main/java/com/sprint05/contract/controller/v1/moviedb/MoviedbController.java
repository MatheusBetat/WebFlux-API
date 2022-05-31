package com.sprint05.contract.controller.v1.moviedb;

import com.sprint05.contract.controller.v1.moviedb.facade.MoviedbControllerFacade;
import com.sprint05.contract.controller.v1.moviedb.model.request.MoviedbControllerRequest;
import com.sprint05.contract.controller.v1.moviedb.model.response.MoviedbControllerResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/moviedb")
/*@CrossOrigin("http://localhost")*/
public class MoviedbController {

    private final MoviedbControllerFacade moviedbControllerFacade;

    @PostMapping ()
    @ResponseStatus(OK)
    public Mono<MoviedbControllerResponse> getMovie(@RequestParam MoviedbControllerRequest moviedbControllerRequest) {
        return moviedbControllerFacade.getMovie(moviedbControllerRequest);
    }

    @GetMapping("/{movieId}")
    public Mono<MoviedbControllerResponse> getMyMovie(@PathVariable String movieId){
        return moviedbControllerFacade.getMyMovie(movieId);

    }

    @GetMapping("/movies")
    @ResponseStatus(OK)
    public Flux<MoviedbControllerResponse> getAllMovies(){
        return moviedbControllerFacade.getAllMovie();

    }

    @GetMapping("/ids")
    public Flux<MoviedbControllerResponse> getMovieByIds(@RequestParam List<String> ids){
        return moviedbControllerFacade.getMovieByIds(ids);
    }

    @DeleteMapping("/delete")
    public Mono<Void> deleteMoviesByIds(@RequestParam List<String> ids){
        return moviedbControllerFacade.deleteMoviesByIds(ids);
    }
}
