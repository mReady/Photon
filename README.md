### About

Photon is a lightweight reflective dependency injection ([JSR-330](https://jcp.org/en/jsr/detail?id=330 "JSR-330"))
library for Java and Android.

```gladle
compile 'net.mready.photon:photon:1.0.2'
```

*This project is based on [Feather](http://zsoltherpai.github.io/feather)*


#### Usage

##### Create the injector

Simple initialization
```java
Injector injector = Injector.with();
```

Or via the builder syntax
```java
Injector injector = new Injector.Builder()
        .build();
```

An application typically needs a single Injector instance.


##### Instantiating dependencies

Dependencies with an `@Inject` constructor or a **single public constructor** 
can be injected without the need for any configuration. Eg:

```java
public class A {
    @Inject
    public A(B b) {
        // ...
    }
}

public class B {
    @Inject
    public B(C c, D d) {
        // ...
    }
}

public class C {
}

@Singleton
public class D {
}
```

Requesting an instance of A:
```java
A a = injector.instance(A.class);
```


##### Modules

When injecting an interface or an object needing custom instantiation you can provide a module to instruct Photon on
how to create the dependency:

```java
public interface DataSource {
    // ...
}

public class MyModule {
    @Provides
    @Singleton // an app will probably need a single instance 
    DataSource dataSource() {
        DataSource dataSource = // instantiate a concrete DataSource
        return dataSource;
    }
}
```

Registering the module(s):

```java
Injector injector = Injector.with(new MyModule());
```

The DataSource dependency will now be available for injection:

```java
public class MyApp {
    @Inject 
    public MyApp(DataSource dataSource) {
        // ...
    }
}
```


Provider methods can be annotated with `@EagerSingleton` to instruct Photon that the dependency should be constructed 
eagerly (when the injector is initialized)

```java
public class MyModule {
    @Provides
    @EagerSingleton
    MyEagerService myEagerService(A a, B b) { // Photon can inject arguments in provider methods
        return new MyEagerService(a, b); 
    }
}
```


##### Qualifiers

Photon supports qualifiers (@Named or custom qualifiers)

```java
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface SomeCustomQualifier { }

public class MyModule {
    @Provides
    @Named("greeting")
    String greeting() {
        return "hi";
    }

    @Provides
    @SomeCustomQualifier
    Foo someFoo(SomeFoo someFoo) {
        return someFoo;
    }
}
```

Injecting:

```java
public class A {
    @Inject
    public A(@SomeCustomQualifier Foo foo, @Named("greeting") String greet) {
        // ...
    }
}
```

Requesting an instance directly from the injector:

```java
String greet = injector.instance(String.class, "greeting");
Foo foo = injector.instance(Key.of(Foo.class, SomeCustomQualifier.class));
```


##### Provider injection

Photon can inject [Provider](https://docs.oracle.com/javaee/6/api/javax/inject/Provider.html)s  to facilitate lazy loading 
or circular dependencies:

```java
public class A {
    @Inject
    public A(Provider<B> b) {
        B b = b.get(); // fetch a new instance when needed
    }
}
```

Requesting a Provider directly from the injector:

```java
Provider<B> bProvider = injector.provider(B.class);
```


##### Field injection

By default Photon only provides constructor injection when injecting to a dependency graph. 
It can also inject fields if:
 
It's explicitly triggered for a target object: 

```java
public class AUnitTest {
    @Inject
    private Foo foo;
    @Inject
    private Bar bar;

    @Before
    public void setUp() {
        Injector injector = // obtain an injector instance
        injector.injectFields(this);
    }
}
```

Or if the injector is initialized with automatic field injection via the builder syntax:

```java
Injector injector = new Injector.Builder()
        .autoInjectFields(true)
        .build();
```

Please note that constructor injection is generally recommended over field injection wherever possible.  
