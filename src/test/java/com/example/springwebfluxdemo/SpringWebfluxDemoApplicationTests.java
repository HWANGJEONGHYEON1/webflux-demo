package com.example.springwebfluxdemo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static reactor.core.scheduler.Schedulers.parallel;

@SpringBootTest
class SpringWebfluxDemoApplicationTests {

    @Test
    void contextLoads() {

        Flux<String> flux = Flux.just("foo", "bar");
        Flux<User> userFlux = Flux.just(new User("swhite"), new User("jpinkman"));

        // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .verifyComplete();

        // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
        StepVerifier.create(flux)
                .expectNext("foo")
                .expectNext("bar")
                .expectError(RuntimeException.class);

        // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite"username
        // and another one with "jpinkman" then completes successfully.
        StepVerifier.create(userFlux)
                .assertNext(u -> Assertions.assertThat(u.getUsername()).isEqualTo("swhite"))
                .assertNext(u -> Assertions.assertThat(u.getUsername()).isEqualTo("jpinkman"))
                .expectComplete();

        // TODO Expect 10 elements then complete and notice how long the test takes.
        final Flux<Long> take = Flux.interval(Duration.ofMillis(100))
                .take(10);

        StepVerifier.create(take)
                .expectNextCount(10)
                .verifyComplete();

        // TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
        // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
        final Flux<Long> take3600 = Flux.interval(Duration.ofSeconds(3600))
                .take(3600);

        Supplier<Flux<Long>> supplier = null;
        StepVerifier.withVirtualTime(supplier)
                .thenAwait(Duration.ofSeconds(3600))
                .expectNextCount(3600)
                .verifyComplete();

    }

    @Test
    public void fluxTest() {
        Flux.just("a", "b", "c", "d", "e", "f", "g", "h")
                .window(2)
                .concatMap(i -> i.map(this::toUppercase).subscribeOn(parallel()))
                .doOnNext(System.out::println)
                .blockLast();
    }

    private List<String> toUppercase(String s) {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.asList(s.toUpperCase(), Thread.currentThread().getName());
    }

}
