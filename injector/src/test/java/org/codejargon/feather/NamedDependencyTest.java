package org.codejargon.feather;

import org.junit.Test;

import javax.inject.Named;

import static org.junit.Assert.assertEquals;

public class NamedDependencyTest {
    @Test
    public void namedInstanceWithModule() {
        Injector injector = Injector.with(new HelloWorldModule());
        assertEquals("Hello!", injector.instance(Key.of(String.class, "hello")));
        assertEquals("Hi!", injector.instance(Key.of(String.class, "hi")));
    }

    public static class HelloWorldModule {
        @Provides
        @Named("hello")
        String hello() {
            return "Hello!";
        }

        @Provides
        @Named("hi")
        String hi() {
            return "Hi!";
        }
    }

}
