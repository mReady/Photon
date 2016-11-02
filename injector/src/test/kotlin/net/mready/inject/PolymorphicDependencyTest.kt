package net.mready.inject

import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Named

class PolymorphicDependencyTest {
    @Test
    fun multipleImplementations() {
        val injector = Injector.with(Module())
        assertEquals(FooA::class.java, injector.instance(Key.of(Foo::class.java, "A")).javaClass)
        assertEquals(FooB::class.java, injector.instance(Key.of(Foo::class.java, "B")).javaClass)
    }

    class Module {
        @Provides
        @Named("A") fun a(fooA: FooA): Foo {
            return fooA
        }

        @Provides
        @Named("B") fun a(fooB: FooB): Foo {
            return fooB
        }
    }

    interface Foo

    class FooA : Foo

    class FooB : Foo
}
