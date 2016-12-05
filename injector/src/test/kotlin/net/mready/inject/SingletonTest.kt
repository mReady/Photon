package net.mready.inject

import org.junit.Assert.*
import org.junit.Test
import javax.inject.Named
import javax.inject.Singleton

class SingletonTest {
    @Test
    fun nonSingleton() {
        val injector = Injector.with()
        assertNotSame(injector.instance(Plain::class.java), injector.instance(Plain::class.java))
    }

    @Test
    fun singleton() {
        val injector = Injector.with()
        assertSame(injector.instance(SingletonObj::class.java), injector.instance(SingletonObj::class.java))
    }

    @Test
    fun singletonThroughProvider() {
        val injector = Injector.with()
        val provider = injector.provider(SingletonObj::class.java)
        assertSame(provider.get(), provider.get())
    }

    @Test
    fun namedSingleton() {
        val module = object {
            @Provides
            @Named("1")
            fun singleton1() = SingletonObj()

            @Provides
            @Named("2")
            fun singleton2() = SingletonObj()
        }

        val injector = Injector.with(module)

        val singleton1 = injector.instance(Key.of(SingletonObj::class.java, "1"))
        val singleton2 = injector.instance(Key.of(SingletonObj::class.java, "2"))

        assertNotSame(singleton1, singleton2)
        assertSame(singleton1, singleton1)
    }

    @Test
    fun eagerSingletons() {
        val module = object {
            var called = 0

            @Provides
            @EagerSingleton
            fun eagerSingleton(): SingletonObj {
                called += 1
                return SingletonObj()
            }
        }

        val injector = Injector.with(module)

        assertEquals(1, module.called)
        assertSame(injector.instance(SingletonObj::class.java), injector.instance(SingletonObj::class.java))
    }

    class Plain

    @Singleton class SingletonObj
}
