package org.codejargon.feather;

import org.junit.Test;

import javax.inject.Provider;

import static org.junit.Assert.assertNotNull;

public class DependencyTest {
    @Test
    public void dependencyInstance() {
        Injector injector = Injector.with();
        assertNotNull(injector.instance(Plain.class));
    }

    @Test
    public void provider() {
        Injector injector = Injector.with();
        Provider<Plain> plainProvider = injector.provider(Plain.class);
        assertNotNull(plainProvider.get());
    }

    @Test(expected = InjectorException.class)
    public void unknown() {
        Injector injector = Injector.with();
        injector.instance(Unknown.class);
    }

    public static class Plain {

    }

    public static class Unknown {
        public Unknown(String noSuitableConstructor) {

        }
    }
}


