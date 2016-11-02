package net.mready.inject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModuleOverrideTest {
    @Test
    public void dependencyOverridenByModule() {
        Injector injector = Injector.with(new PlainStubOverrideModule());
        assertEquals(PlainStub.class, injector.instance(Plain.class).getClass());
    }


    @Test
    public void moduleOverwrittenBySubClass() {
        assertEquals("foo", Injector.with(new FooModule()).instance(String.class));
        assertEquals("bar", Injector.with(new FooOverrideModule()).instance(String.class));
    }

    public static class Plain {
    }

    public static class PlainStub extends Plain {

    }

    public static class PlainStubOverrideModule {
        @Provides
        public Plain plain(PlainStub plainStub) {
            return plainStub;
        }

    }

    public static class FooModule {
        @Provides
        String foo() {
            return "foo";
        }
    }

    public static class FooOverrideModule extends FooModule {
        @Provides
        @Override
        String foo() {
            return "bar";
        }
    }


}
