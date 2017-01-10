package net.mready.photon

import org.junit.Assert.assertNotNull
import org.junit.Test
import javax.inject.Inject
import kotlin.test.assertNull

class FieldInjectionTest {
    @Test
    fun fieldsInjectedManually() {
        val injector = Injector.with()
        val target = Target()
        injector.injectFields(target)
        assertNotNull(target.a)
    }

    @Test
    fun fieldsNotInjectedByDefault() {
        val injector = Injector.with()
        val target = injector.instance(Target::class.java)
        assertNull(target.a)
    }

    @Test
    fun fieldsInjectedAutomatically() {
        val injector = Injector.Builder()
                .autoInjectFields(true)
                .build()
        val target = injector.instance(Target::class.java)
        assertNotNull(target.a)
    }

    class Target {
        @Inject var a: A? = null
    }

    class A
}
