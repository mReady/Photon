package org.codejargon.feather;

import org.junit.Test;

public class AmbiguousModuleTest {
    @Test(expected = InjectorException.class)
    public void ambiguousModule() {
        Injector.with(new Module());
    }

    public static class Module {
        @Provides
        String foo() {
            return "foo";
        }

        @Provides
        String bar() {
            return "bar";
        }
    }
}
