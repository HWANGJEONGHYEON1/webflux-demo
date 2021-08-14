package com.example.springwebfluxdemo.mono;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

public class DemoMono {

    public static void main(String[] args) {
        // TODO Return an empty Mono
        System.out.println(empty());

        System.out.println("=========");
        // TODO Return a Mono that never emits any signal
        System.out.println(never());

        // TODO Return a Mono that contains a "foo" value
        System.out.println(Mono.just("foo").log());

        Mono.error(new IllegalStateException());
    }

    private static Mono empty() {
        return Mono.empty();
    }

    private static Mono never() {
        return Mono.never();
    }


}
