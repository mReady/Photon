package net.mready.inject;

import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

public class FieldInjectionTest {
    @Test
    public void fieldsInjected() {
        Injector injector = Injector.with();
        Target target = new Target();
        injector.injectFields(target);
        assertNotNull(target.a);
    }


    public static class Target {
        @Inject
        private A a;
    }

    public static class A {

    }
}
