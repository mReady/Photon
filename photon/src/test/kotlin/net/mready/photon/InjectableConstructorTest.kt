package net.mready.photon

import org.junit.Assert
import org.junit.Test
import javax.inject.Inject

class InjectableConstructorTest {

    @Test
    fun plainClassInstance() {
        val injector = Injector.with()
        Assert.assertNotNull(injector.instance(Plain::class.java))
    }

    @Test
    fun singleConstructorInstance() {
        val injector = Injector.with()
        Assert.assertNotNull(injector.instance(SingleConstructor::class.java).a)
    }

    @Test(expected = InjectorException::class)
    fun multiConstructorInstance() {
        val injector = Injector.with()
        Assert.assertNotNull(injector.instance(MultipleConstructors::class.java))
    }

    @Test
    fun singlePublicConstructorInstance() {
        val injector = Injector.with()
        Assert.assertNotNull(injector.instance(SinglePublicConstructor::class.java).a)
    }

    @Test
    fun annotatedConstructorInstance() {
        val injector = Injector.with()
        Assert.assertNotNull(injector.instance(AnnotatedConstructor::class.java))
    }

    @Test(expected = InjectorException::class)
    fun multiAnnotatedConstructorsInstance() {
        val injector = Injector.with()
        Assert.assertNotNull(injector.instance(MultipleAnnotatedConstructors::class.java))
    }

    @Test
    fun annotatedConstructorInstance2() {
        val injector = Injector.with()
        Assert.assertNull(injector.instance(AnnotatedConstructor2::class.java).a)
    }


    class Plain

    class SingleConstructor(val a: Plain)

    class MultipleConstructors(val a: Plain?) {
        constructor() : this(null)
    }

    class SinglePublicConstructor(val a: Plain?) {
        private constructor() : this(null)
    }

    class AnnotatedConstructor @Inject constructor(val a: Plain?) {
        constructor() : this(null)
    }

    class MultipleAnnotatedConstructors @Inject constructor(val a: Plain?) {
        @Inject constructor() : this(null)
    }

    class AnnotatedConstructor2 constructor(val a: Plain?) {
        @Inject private constructor() : this(null)
    }
}