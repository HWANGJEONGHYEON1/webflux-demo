package com.example.springwebfluxdemo.Transform;

import com.example.springwebfluxdemo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;

public class DemoTransform {

    public static void main(String[] args) {
        // TODO Capitalize the user username, firstname and lastname
        Mono.just(new User("hjh", "jh", "h"))
                .map(u -> new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));

        // TODO Capitalize the users username, firstName and lastName
        Flux.just(new User("hjh", "jh", "h"), new User("kje", "je", "k"))
                .map(u -> new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()))
                .subscribe(System.out::println);

        // TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
        Flux.just(new User("hjh", "jh", "h"), new User("kje", "je", "k"))
                .flatMap(DemoTransform::asyncCapitalizeUser);


    }

    static Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

}
