package net.mready.photon

import org.junit.Assert.assertNotNull
import org.junit.Test

class DependencyTest {
    @Test
    fun dependencyInstance() {
        val injector = Injector.with()
        assertNotNull(injector.instance(Plain::class.java))
    }

    @Test
    fun provider() {
        val injector = Injector.with()
        val plainProvider = injector.provider(Plain::class.java)
        assertNotNull(plainProvider.get())
    }

    @Test(expected = InjectorException::class)
    fun unknown() {
        val injector = Injector.with()
        injector.instance(Unknown::class.java)
    }

    class Plain

    class Unknown(noSuitableConstructor: String)
}


