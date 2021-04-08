<!---
-->

##### Intuitive library that makes it easier for you to return multiple, fully defined objects on the fly from any method, any time rather than being limited to the default maximum of one. 

##### Dependencies 
* None. Zero. **JDK5** compatible perhaps even. Not tested but should compile. Tested on **JDK15**.    

##### Maven dependencies available on maven central [search.maven.org](https://search.maven.org/search?q=com.momomo)
##### Dependency   
```xml
<dependency>
  <groupId>com.momomo</groupId>
  <artifactId>momomo.com.platform.Return</artifactId>
  <version>1.0.6</version>
</dependency>                                                      
```                         
##### Repository
```xml
<repository>
    <id>maven-central</id>
    <url>http://repo1.maven.org/maven2</url>
</repository>
```                      

##### Our other, highlighted [repositories](https://github.com/momomo?tab=repositories)                          

* **[`momomo.com.platform.Core`](https://github.com/momomo/momomo.com.platform.Core)**  
Is essentially what makes the our the core of several of momomo.com's public releases and contains a bunch of Java utility.

* **[`momomo.com.platform.Lambda`](https://github.com/momomo/momomo.com.platform.Lambda)**  
Contains a bunch of `functional interfaces` similar to `Runnable`, `Supplier`, `Function`, `BiFunction`, `Consumer` `...` and so forth all packed in a easily accessed and understood intuitive pattern that are used plenty in our libraries. **`Lambda.V1E`**, **`Lambda.V2E`**, **`Lambda.R1E`**, **`Lambda.R2E`**, ...

* **[`momomo.com.platform.Nanotime`](https://github.com/momomo/momomo.com.platform.Nanotime)**  
Allows for nanosecond time resolution when asking for time from Java Runtime in contrast with **`System.currentTimeMillis()`**.

* **[`momomo.com.platform.db.transactional.Hibernate`](https://github.com/momomo/momomo.com.platform.db.transactional.Hibernate)**  
A library to execute database command in transactions without having to use annotations based on Hibernate libraries. No Spring!

* **[`momomo.com.platform.db.transactional.Spring`](https://github.com/momomo/momomo.com.platform.db.transactional.Spring)**  
A library to execute database command in transactions without having to use annotations based on Spring libraries.
          
### Background
 
At times, we would like the ability to return multiple values from a method and this library allows you to do so, currently **up to nine**. **`Nine`** should be plenty for you. If you need more, just declare a class for god sake!

We use this occasionally, ***but not a crazy amount in our code*** and often when we are still experimenting with signatures and what not and instead of declaring separate objects we might use this as a quick fix. 

With this class based library, you can ***for instance*** use:
 
* `Return.One<String>`  
* `Return.Two<String, Integer, Long>`  
* `Return.Three<String, Integer, Long>` 
* `Return.Four<String, Integer, Long, Boolean>`
* `Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>>`
* ...
* `Return.Nine<...>`

It also you to ***return subsets*** as it has a smart inheritance structure, since a **`Return.Three<String, Integer, Long>`** is a also a **`Return.Two<String, Integer>`** and a **`Return.One<String>`**.   

We provide some utility methods to **`cast`** and **`clone`** from one to another, such as **`$.asTwo()`**, **`$.asThree()`** and **`$.toTwo()`** and **`$.toThree()`**, `...`.

Documentation is provided through comments within the class itself **[`Return.java`](src/momomo/com/Return.java)** which is self documented and we recommend you just start try to use it and you will immediately figure out its use. 

### Examples

Examples below can be found in **[`Examples.java`](test/momomo/com/platform/Return/examples/Examples.java)** with highlights below:

#### Creating

```java
public static final class CREATE {
        
    public static Return.Two<Integer, Long> two() {
        return new Return.Two<>(1, 2L);
    }
    
    public static Return.Three<Integer, Long, String> three() {
        return new Return.Three<>(1, 2L, "3");
    }

    public static Return.Four<Integer, Long, String, String> four() {
        return new Return.Four<>(1, 2L, "3", "4");
    }

    public static void main() {
        Return.One<String>                         one   = new Return.One<>  ("1");
        Return.Two<String, Integer>                two   = new Return.Two<>  ("1", 2);
        Return.Three<String, Integer, Long>        three = new Return.Three<>("1", 2, 3L);
        Return.Four<String, Integer, Long, String> four  = new Return.Four<> ("1"); four.second(2).third(3L).fourth("4");
        
        two = new Return.Three<>("1");
        two = new Return.Four<> ("1");
        two = new Return.Five<> ("1");
        two = three; 
        two = four;
    }
}
```

#### Casting

```java
public static Return.Two<Integer, Long> two() {
    return CREATE.four().asTwo();    // Same instance, just casted
}

public static Return.Three<Integer, Long, String> three() {
    return CREATE.four();            // Same instance, just casted though method inference
}

public static void main() {
    Return.Four<Integer, Long, String, String> four = CREATE.four();

    // All casted to lower ones
    Return.One<Integer>                 one   = four.asOne();
    Return.Two<Integer, Long>           two   = four.asTwo();
    Return.Three<Integer, Long, String> three = four.asThree();

    if (one == two && two == three && three == four) {
        System.out.println("The universe is working!");
    }
}
```

#### Cloning

```java
public static final class CLONE {
    public static Return.Two<Integer, Long> two() {
        return CREATE.four().toTwo();    // A new instance, objects are copied over  
    }

    public static Return.Three<Integer, Long, String> three() {
        return CREATE.four().toThree();  // A new instance, objects are copied over  
    }

    public static void main() {
        Return.Four<Integer, Long, String, String> four = CREATE.four();
    
        // All copied / cloned to new instances
        Return.One<Integer>                 one   = four.toOne();
        Return.Two<Integer, Long>           two   = four.toTwo();
        Return.Three<Integer, Long, String> three = four.toThree();
    
        if (one == two && two == three && three == four) {
            System.out.println("The universe is collapsing!");
        }
    }
}
```                                                                                

#### Composition

Creating a **`Return.Three<...>`** from a lesser such as **`Return.Two<...>`**. We do this using a constructor to avoid some **** calling a **`toTwo()`** inadvertedly if we had supplied that instead.       

```java
public static Return.Three<Integer, Long, JFrame> three() {
    // Note, we create a Return.Three from a Return.Two meaning the last one (third) will be null and has to be set manually, otherwise it will remain null.  
    return new Return.Three<Integer, Long, JFrame>( CREATE.two() ).third(new JFrame());
}

public static Return.Four<Integer, Long, String, JFrame> five() {
    // Note, we create a Return.Five from a Return.Four meaning the last one (fourth) will be null and has to be set manually, otherwise it will remain null.  
    return new Return.Four<Integer, Long, String, JFrame>( CREATE.three() ).fourth(new JFrame());
}

public static void main() {
    Return.Two<Integer, Long> two = CREATE.two();

    Return.Three<Integer, Long, JFrame>                three = new Return.Three<>(two);
    Return.Four<Integer, Long, JFrame, JarInputStream> four  = new Return.Four<>(three);
    
    if ( three.third == null && four.third == null && four.fourth == null ) {
        System.out.println("The universe is working!");
    }
}
```            

#### Reading

```java
public static void three() {
    Return.Three<Integer, Long, String> three = CREATE.three();

    Integer first  = three.first;
    Long    second = three.second;
    String  third  = three.third();
}

public static Return.Two<String, String> four() {
    Return.Four<Integer, Long, String, String> four = CREATE.four();

    return new Return.Two<>( four.first + "::" + four.second, four.third() + "::" + four.fourth() );
}
```                              

#### Passing - **[`Params`](src/momomo/com/Params.java)** = **[`Return`](src/momomo/com/Return.java)** - Showing the **[`Params.java`](src/momomo/com/Params.java)** class.    

```java
public static void three(Return.Three<Integer, Long, String> param) {
    Integer first  = param.first;
    Long    second = param.second;
    String  third  = param.third;
}

public static void four(Params.Four<Integer, Long, String, String> param) {
    Integer first  = param.first;
    Long    second = param.second;
    String  third  = param.third;
}

public static void main() {
    three( new Return.Three<>(1, 2L, "3") );
    three( new Params.Three<>(1, 2L, "3") );
    
    three( new Return.Four<> (1, 2L, "3", "4") );
    three( new Params.Four<> (1, 2L, "3", "4") );

    four( new Return.Four<> (1, 2L, "3", "4") );
    four( new Params.Four<> (1, 2L, "3", "4") );

    four( new Return.Five<> (1, 2L, "3", "4", "5") );
    four( new Params.Five<> (1, 2L, "3", "4", "5") );
}
```                                                   

### More examples

More examples can be found in **[`ExamplesToo.java`](test/momomo/com/platform/Return/examples/ExamplesToo.java)** with highlights below: 

```java
/**
 * @return Four in the end
 */
public static Return.Four<StringBuilder, String, List<String>, Boolean> example1() {
    Return.One<String>                  a = new Return.One<>  ("first");
    Return.Two<String, Integer>         b = new Return.Two<>  ("first", 2);
    Return.Three<String, Integer, Long> c = new Return.Three<>("first", 2, 3L);
    // ... 
    
    // We can read
    String  first  = a.first;
    Integer second = b.second;

    // We can set 
    c.first  = "första"; // Directly
    c.second = 10;       // Directly
    c.third(11L);        // Using setter
    
    // Return four
    return new Return.Four<>(new StringBuilder("first"), "second", new ArrayList<>(), false);
}
```

Showing how to auto cast using `asOne()`, `asTwo()` ...    

```java
public static void example2() {
    Return.Four<StringBuilder, String, List<String>, Boolean> $ = new Return.Four<>(new StringBuilder("first"), "second", new ArrayList<>(), false);

    Return.One<StringBuilder>         a = $.asOne(); // Casting from Four<> to One<>
    Return.Two<StringBuilder, String> b = $.asTwo(); // Casting from Four<> to Two<>

    // Still the same instance!
    if ( $.equals(a) && $.equals(b) ) {
        System.out.println(true);
    }

    // All the same instance! a == b == $, just casted!
    if ( a == b && b == $ ) {
        System.out.println(true);
    }
                                
    // Still the same instance!
    if ( a instanceof Return.Four ) {
        System.out.println(true);
    }

    // Still the same instance!
    if ( a instanceof Return.Three ) {
        System.out.println(true);
    }

    // Still the same instance!
    if ( a instanceof Return.Two ) {
        System.out.println(true);
    }

    // Still the same instance!
    if ( a instanceof Return.One ) {
        System.out.println(true);
    }
    
    $.first = new StringBuilder("Changing $.first will effectivaly change a and b as well!");
    System.out.println($.first); // All the same
    System.out.println(a.first); // All the same
    System.out.println(b.first); // All the same
}
```

Showing how to clone / copy to new types using `toOne()`, `toTwo()` ...

```java
public static void example3() {
    Return.Four<StringBuilder, String, ArrayList<Object>, Boolean> $ = new Return.Four<>(new StringBuilder("first"), "second", new ArrayList<>(), false);

    Return.One<StringBuilder>         a = $.toOne(); // Not casting, but copying to a new instance!
    Return.Two<StringBuilder, String> b = $.toTwo(); // Not casting, but copying to a new instance!

    // Not the same instance!
    if ( $.equals(a) && $.equals(b) ) {
        System.out.println(false);
    }

    // False!
    if ( a instanceof Return.Four ) {
        System.out.println(false);
    }

    // False!
    if ( a instanceof Return.Three ) {
        System.out.println(false);
    }

    // False!
    if ( a instanceof Return.Two ) {
        System.out.println(false);
    }

    // True!
    if ( a instanceof Return.One ) {
        System.out.println(true);
    }

    $.first = new StringBuilder("Changing $.first will NOT change a and b as well!");
    System.out.println($.first); // Changing $.first will NOT change a and b too!
    System.out.println(a.first); // "first" 
    System.out.println(b.first); // "first" 
}
```

Methods returning higher order, all eventually from five()    

```java
/**
 * Create One<> by calling higher order one two()
 */
private static Return.One<String> one() {
    return two();  // A two can be casted to a one
}

/**
 * Create Two<> by calling higher order one three(), four() or five()
 */
private static Return.Two<String, Integer> two() {
    if ( false ) return four();                 // A four can be casted to two
    if ( false ) return five();                 // A five can be casted to two
    if ( false ) new Return.Two<>("first", 2);  // We can create it directly
    
    return three(); // A three can be casted to a two
}

/**
 * Create Three<> by calling higher order one four()
 */
private static Return.Three<String, Integer, Long> three() {
    return four();
}

/**
 * Create Four<> by calling higher order one five()
 */
private static Return.Four<String, Integer, Long, Boolean> four() {
    return five();
}

/**
 * Create Five<>
 */
private static Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five() {
    return new Return.Five<>("first", 2, 3L, Boolean.FALSE, new LinkedHashMap<>());
}
```

Using `one(), two(), three(), four(), five()`    

```java
/**
 * Just showing all generic types will still resolve. 
 * 
 * We return one casted to Two<> from a Five<>
 */
public static Return.Two<String, Integer> example5() {
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
    
    String                                         first  = $.first;
    Integer                                        second = $.second;
    Long                                           third  = $.third;
    Boolean                                        fourth = $.fourth;
    LinkedHashMap<String, List<ArrayList<String>>> fifth  = $.fifth;
    
    return $.asTwo();
}
```

Casting again.    

```java
public static void example6() {
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
    
    // Auto casting. The only major benefit of this one is that you won't have to type all of the generic signatures. 
    // If you have $ here, and you would like to express it as a Four, typing those might a be a hassle, at least when typing examples. 
    // So we provide convienience methods to do that. Intellij / Eclipse will generate the field. 
    
    Return.One<String>                                                                          one   = $.asOne();
    Return.Two<String, Integer>                                                                 two   = $.asTwo();
    Return.Three<String, Integer, Long>                                                         three = $.asThree();
    Return.Four<String, Integer, Long, Boolean>                                                 four  = $.asFour();
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = $.asFive();
    
    // Yes, the last one is not required, but it looks so much more consistent for this example so we added it. 
    // It just returns this and is cheap. 
}
```

Cloning again.    

```java
public static void example7() {
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
    
    // Clones. 
    Return.One<String>                                                                          one   = $.toOne();
    Return.Two<String, Integer>                                                                 two   = $.toTwo();
    Return.Three<String, Integer, Long>                                                         three = $.toThree();
    Return.Four<String, Integer, Long, Boolean>                                                 four  = $.toFour();
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = $.toFive();
    
    // Note that these are all clones. Sure, we could have done:  
    Return.Four<String, Integer, Long, Boolean> last = new Return.Four<>($.first, $.second, $.third, $.fourth);
    // But typing that manually is a bit too much. You won't get much help from editor typing that either!  
}
```

### Contribute
Send an email to `opensource{at}momomo.com` if you would like to contribute in any way, make changes or otherwise have thoughts and/or ideas on things to improve.