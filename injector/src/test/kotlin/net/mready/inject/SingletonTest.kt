package net.mready.inject

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import javax.inject.Singleton

class SingletonTest {
    @Test
    fun nonSingleton() {
        val injector = Injector.with()
        assertNotEquals(injector.instance(Plain::class.java), injector.instance(Plain::class.java))
    }

    @Test
    fun singleton() {
        val injector = Injector.with()
        assertEquals(injector.instance(SingletonObj::class.java), injector.instance(SingletonObj::class.java))
    }

    @Test
    fun singletonThroughProvider() {
        val injector = Injector.with()
        val provider = injector.provider(SingletonObj::class.java)
        assertEquals(provider.get(), provider.get())
    }

    class Plain

    @Singleton class SingletonObj
}
