package net.mready.photon

import org.junit.Assert.assertEquals
import org.junit.Test

class ModuleOverrideTest {
    @Test
    fun dependencyOverridenByModule() {
        val injector = Injector.with(PlainStubOverrideModule())
        assertEquals(PlainStub::class.java, injector.instance(Plain::class.java).javaClass)
    }

    @Test
    fun moduleOverwrittenBySubClass() {
        assertEquals("foo", Injector.with(FooModule()).instance(String::class.java))
        assertEquals("bar", Injector.with(FooOverrideModule()).instance(String::class.java))
    }

    open class Plain

    class PlainStub : Plain()

    class PlainStubOverrideModule {
        @Provides
        fun plain(plainStub: PlainStub): Plain {
            return plainStub
        }

    }

    open class FooModule {
        @Provides
        open fun foo(): String {
            return "foo"
        }
    }

    class FooOverrideModule : FooModule() {
        @Provides
        override fun foo(): String {
            return "bar"
        }
    }


}
