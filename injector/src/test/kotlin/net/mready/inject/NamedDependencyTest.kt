package net.mready.inject

import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Named

class NamedDependencyTest {
    @Test
    fun namedInstanceWithModule() {
        val injector = Injector.with(HelloWorldModule())
        assertEquals("Hello!", injector.instance(Key.of(String::class.java, "hello")))
        assertEquals("Hi!", injector.instance(Key.of(String::class.java, "hi")))
    }

    class HelloWorldModule {
        @Provides
        @Named("hello") fun hello(): String {
            return "Hello!"
        }

        @Provides
        @Named("hi") fun hi(): String {
            return "Hi!"
        }
    }

}
