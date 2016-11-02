package net.mready.inject

import org.junit.Assert.assertNotNull
import org.junit.Test

class TransitiveDependencyTest {
    @Test
    fun transitive() {
        val injector = Injector.with()
        val a = injector.instance(A::class.java)
        assertNotNull(a.b.c)
    }

    class A(val b: B)

    class B(val c: C)

    class C

}
