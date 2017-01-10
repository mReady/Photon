package net.mready.photon

import org.junit.Assert.assertNotNull
import org.junit.Test
import javax.inject.Provider

class CircularDependencyTest {
    @Test(expected = InjectorException::class)
    fun circularDependencyCaught() {
        val injector = Injector.with()
        injector.instance(Circle1::class.java)
    }

    @Test
    fun circularDependencyWithProviderAllowed() {
        val injector = Injector.with()
        val circle1 = injector.instance(CircleWithProvider1::class.java)
        assertNotNull(circle1.circleWithProvider2.circleWithProvider1.get())
    }

    class Circle1(val circle2: Circle2)

    class Circle2(val circle1: Circle1)

    class CircleWithProvider1(val circleWithProvider2: CircleWithProvider2)

    class CircleWithProvider2(val circleWithProvider1: Provider<CircleWithProvider1>)
}
