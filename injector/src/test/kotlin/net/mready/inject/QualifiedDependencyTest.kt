package net.mready.inject


import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject
import javax.inject.Qualifier

class QualifiedDependencyTest {
    @Test
    fun qualifiedInstances() {
        val injector = Injector.with(Module())
        assertEquals(FooA::class.java, injector.instance(Key.of(Foo::class.java, A::class.java)).javaClass)
        assertEquals(FooB::class.java, injector.instance(Key.of(Foo::class.java, B::class.java)).javaClass)
    }

    @Test
    fun injectedQualified() {
        val injector = Injector.with(Module())
        val dummy = injector.instance(Dummy::class.java)
        assertEquals(FooB::class.java, dummy.foo.javaClass)
    }

    @Test
    fun fieldInjectedQualified() {
        val injector = Injector.with(Module())
        val dummy = DummyTestUnit()
        injector.injectFields(dummy)
        assertEquals(FooA::class.java, dummy.foo.javaClass)
    }


    interface Foo

    class FooA : Foo

    class FooB : Foo

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
    annotation class A

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class B

    class Module {
        @Provides
        @A fun a(fooA: FooA): Foo {
            return fooA
        }

        @Provides
        @B fun b(fooB: FooB): Foo {
            return fooB
        }
    }

    class Dummy(@B val foo: Foo)

    class DummyTestUnit {
        @Inject @A lateinit var foo: Foo
    }
}
