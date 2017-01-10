package net.mready.photon

import org.junit.Assert.assertNotNull
import org.junit.Test
import javax.inject.Provider

class ProviderInjectionTest {
    @Test
    fun providerInjected() {
        val injector = Injector.with()
        assertNotNull(injector.instance(A::class.java).plainProvider.get())
    }

    class A(val plainProvider: Provider<B>)

    class B
}
