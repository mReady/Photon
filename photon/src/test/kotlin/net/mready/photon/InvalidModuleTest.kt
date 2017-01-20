package net.mready.photon

import org.junit.Test

class InvalidModuleTest {
    @Test(expected = InjectorException::class)
    fun ambiguousModule() {
        Injector.with(AmbiguousModule())
    }

    @Test(expected = InjectorException::class)
    fun emptyModule() {
        Injector.with(object {})
    }

    class AmbiguousModule {
        @Provides
        fun foo(): String = "foo"

        @Provides
        fun bar(): String = "bar"
    }
}
