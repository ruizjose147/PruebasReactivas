package com.example.ejemploPruebaReactiva;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import static org.junit.jupiter.api.Assertions.*;

class UppercaseConverterTest {

    @Test
    void TestPublisher(){
        TestPublisher
                .<String>create()
                .next("Primero", "Segundo", "Tercero")
                .error(new RuntimeException("Message"));
    }


   final TestPublisher<String> testPublisher = TestPublisher.create();
    @Test
    void testUpperCase() {
        UppercaseConverter uppercaseConverter = new UppercaseConverter(testPublisher.flux());
        StepVerifier.create(uppercaseConverter.getUpperCase())
                .then(() -> testPublisher.emit("datos", "GeNeRaDoS", "Sofka"))
                .expectNext("DATOS", "GENERADOS", "SOFKA")
                .verifyComplete();
    }

    @Test
    void testPubisherDos(){
        TestPublisher
                .createNoncompliant(TestPublisher.Violation.ALLOW_NULL)
                .emit("1", "2", null, "3");

    }
}