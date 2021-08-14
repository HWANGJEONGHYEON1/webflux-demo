package com.example.springwebfluxdemo.merge;

import com.example.springwebfluxdemo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class DemoMerge {

    public static void main(String[] args) {
        Flux<Long> flux1 = Flux.interval(Duration.ofMillis(10)).take(10);
        Flux<Long> flux2 = Flux.just(100L, 200L);

        // TODO Merge flux1 and flux2 values with interleave
        flux1.mergeWith(flux2)
                .doOnNext(System.out::println)
                .blockLast();
        System.out.println("=======");
        // TODO Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)
        flux1.concatWith(flux2)
                .doOnNext(System.out::println)
                .blockLast();

        // TODO Create a Flux containing the value of mono1 then the value of mono2
        final Mono<Integer> mono1 = Mono.just(1);
        final Mono<Integer> mono2 = Mono.just(2);

        mono1.mergeWith(mono2)
                .doOnNext(System.out::println)
                .blockLast();


    }


}
