package org.codejargon.feather;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PojoProvidedThroughModuleTest {
    @Test(expected = InjectorException.class)
    public void pojoNotProvided() {
        Injector injector = Injector.with();
        injector.instance(Pojo.class);
    }

    @Test
    public void pojoProvided() {
        Injector injector = Injector.with(new Module());
        assertNotNull(injector.instance(Pojo.class));
    }

    public static class Module {
        @Provides
        Pojo pojo() {
            return new Pojo("foo");
        }
    }

    public static class Pojo {
        private final String foo;

        public Pojo(String foo) {
            this.foo = foo;
        }
    }
}
