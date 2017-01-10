package net.mready.photon

import org.junit.Assert.assertNotNull
import org.junit.Test

class PojoProvidedThroughModuleTest {
    @Test(expected = InjectorException::class)
    fun pojoNotProvided() {
        val injector = Injector.with()
        injector.instance(Pojo::class.java)
    }

    @Test
    fun pojoProvided() {
        val injector = Injector.with(Module())
        assertNotNull(injector.instance(Pojo::class.java))
    }

    class Module {
        @Provides
        fun pojo(): Pojo {
            return Pojo("foo")
        }
    }

    class Pojo(private val foo: String)
}
