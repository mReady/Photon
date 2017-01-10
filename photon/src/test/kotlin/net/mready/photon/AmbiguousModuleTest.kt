package net.mready.photon

import org.junit.Test

class AmbiguousModuleTest {
    @Test(expected = InjectorException::class)
    fun ambiguousModule() {
        Injector.with(Module())
    }

    class Module {
        @Provides
        fun foo(): String = "foo"

        @Provides
        fun bar(): String = "bar"
    }
}
