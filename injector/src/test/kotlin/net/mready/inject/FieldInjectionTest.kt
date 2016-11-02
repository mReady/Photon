package net.mready.inject

import org.junit.Assert.assertNotNull
import org.junit.Test
import javax.inject.Inject

class FieldInjectionTest {
    @Test
    fun fieldsInjected() {
        val injector = Injector.with()
        val target = Target()
        injector.injectFields(target)
        assertNotNull(target.a)
    }

    class Target {
        @Inject lateinit var a: A
    }

    class A
}
