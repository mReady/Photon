package org.codejargon.feather;

import org.junit.Test;

import javax.inject.Provider;
import javax.inject.Singleton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SingletonTest {
    @Test
    public void nonSingleton() {
        Injector injector = Injector.with();
        assertNotEquals(injector.instance(Plain.class), injector.instance(Plain.class));
    }

    @Test
    public void singleton() {
        Injector injector = Injector.with();
        assertEquals(injector.instance(SingletonObj.class), injector.instance(SingletonObj.class));
    }

    @Test
    public void singletonThroughProvider() {
        Injector injector = Injector.with();
        Provider<SingletonObj> provider = injector.provider(SingletonObj.class);
        assertEquals(provider.get(), provider.get());
    }

    public static class Plain {

    }

    @Singleton
    public static class SingletonObj {

    }
}
