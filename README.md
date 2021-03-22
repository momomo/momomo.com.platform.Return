<!---
-->

## momomo.com.platform.Return

###### An intuitive library that allows you to return multiple return values with defined types on the fly from any method rather than being limited to the default maximum of one.

#### Dependencies 
* None. Zero. **JDK5** compatible perhaps even. Not tested but should compile. Tested on **JDK15**.    

##### Maven dependencies available on maven central [search.maven.org](https://search.maven.org/search?q=com.momomo)
##### Dependency   
```xml
<dependency>
  <groupId>com.momomo</groupId>
  <artifactId>momomo.com.platform.Return</artifactId>
  <version>1.0.5</version>
</dependency>                                                      
```                         
##### Repository
```xml
<repository>
    <id>maven-central</id>
    <url>http://repo1.maven.org/maven2</url>
</repository>
```                      

##### Our other repositories

* [`momomo.com.platform.Core`](https://github.com/momomo/momomo.com.platform.Lambda)  
Is essentially what makes the our the core of several of momomo.com's public releases and contains a bunch of `Java` utility.

* [`momomo.com.platform.Lambda`](https://github.com/momomo/momomo.com.platform.Lambda)  
Contains a bunch of `functional interfaces` similar to `Runnable`, `Supplier`, `Function`, `BiFunction`, `Consumer` `...` and so forth all packed in a easily accessed 
and understood intuitive pattern.  
`Lambda.V1E`, `Lambda.V2E`, `Lambda.R1E`, `Lambda.R2E` are used plenty in examples below.

* [`momomo.com.platform.Nanotime`](https://github.com/momomo/momomo.com.platform.Nanotime)  
Allows for nanosecond time resolution when asking for time from Java Runtime in contrast with `System.currentTimeMillis()`.

* [`momomo.com.platform.db.transactional.Hibernate`](https://github.com/momomo/momomo.com.platform.db.transactional.Hibernate)  
A library to execute database command in transactions without having to use annotations based on Hibernate libraries. No Spring!

### Background
 
Sometimes we want to easily return multiple return values from a method. This library allows you to do so, up to **five**.

We use this occasionally, but not a crazy amount in our code. It **also** you to return subsets as it has smart inheritance structure.
  
With this `class` based library, you can use: 
* `Return.One<String>` to return just `One` well defined object.   
It is provided just for consistency, although it could have some usage. 
* `Return.Two<String, Integer, Long>` to return `Two` well defined objects. 
* `Return.Three<String, Integer, Long>` to return `Three` well defined objects. 
* `Return.Four<String, Integer, Long, Boolean>` to return `Four` well defined objects
* `Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>>` to return `Five` well defined objects.

* **I hope you understand `<...>` are just examples**!  

Five should be plenty for you. If you need more, just declare a class for god sake!

We also provide some utility methods, to cast and clone from one to another.  

### Guide

#### [Return.java](src/momomo/com/Return.java)
The class is really self documented. Just try to use it. You will figure it out.  

### Examples 

Examples can be found in [`Example.java`](test/momomo/com/platform/Return/examples/Examples.java) with the **class body inline** below packed with **examples**: 

```java
/////////////////////////////////////////////////////////////////////
    
/**
 * @return Four in the end
 */
public static Return.Four<StringBuilder, String, List<String>, Boolean> demoZero() {
    Return.One<String>                  one   = new Return.One<>("first");
    Return.Two<String, Integer>         two   = new Return.Two<>("first", 1);
    Return.Three<String, Integer, Long> three = new Return.Three<>("first", 1, 2L);
    
    // And so forth
    
    return new Return.Four<>(new StringBuilder(), "second", new ArrayList<>(), false);
}

public static void demoOne() {
    Return.Four<StringBuilder, String, List<String>, Boolean> four = demoZero();

    Return.One<StringBuilder>         one = four.asOne();
    Return.Two<StringBuilder, String> two = four.asTwo();
    
    // And so forth ...
}

/////////////////////////////////////////////////////////////////////
// Supports the examples below, and are examples of their own. 
// Parse the code inside the method body.
/////////////////////////////////////////////////////////////////////

private static Return.One<String> one() {
    return two();
}

private static Return.Two<String, Integer> two() {
    if ( false ) return four();
    if ( false ) return five();
    
    return three();
}

private static Return.Three<String, Integer, Long> three() {
    return four();
}

private static Return.Four<String, Integer, Long, Boolean> four() {
    return five();
}

private static Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five() {
    return new Return.Five<>("", 1, 2L, Boolean.FALSE, new LinkedHashMap<>());
}

/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////

public static void exampleZero() {
    Return.Two<String, Integer> $ = two();

    String  first  = $.first();
    Integer second = $.second();

    Return.One<String> casted = $.asOne();
    Return.One<String> cloned = $.toOne();
    
    // Casted
    if ( casted instanceof Return.Two ) {
        System.out.println(true);
    }

    // Cloned
    if ( cloned instanceof Return.Two ) {
        System.out.println(false);
    }

    casted.first("value");

    System.out.println(casted.first());

    if ( casted.first().equals($.first()) ) {
        System.out.println(true); // Same instance!
    }

    exampleOne();
}

/////////////////////////////////////////////////////////////////////

public static void exampleOne() {
    Return.Three<String, Integer, Long> $      = three();
    Return.Two<String, Integer>         casted = $.asTwo();
    
    testingParameters($);
    // paramsThree(casted); // Won't work
}

/////////////////////////////////////////////////////////////////////

public static void exampleTwo() {
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
    
    String                                         first  = $.first();
    Integer                                        second = $.second();
    Long                                           third  = $.third();
    Boolean                                        fourth = $.fourth();
    LinkedHashMap<String, List<ArrayList<String>>> fifth  = $.fifth();
}

/////////////////////////////////////////////////////////////////////

public static void exampleThree() {
    // Notice the Return.Four from a five() !
    Return.Four<String, Integer, Long, Boolean> $ = five();
    
    // We can also reassign it to a four, three, two, one ... 
    Return.Three<String, Integer, Long> casted = $;
    String  first = casted.first();
    Integer two   = casted.second();
    // ...
    
    // We can pass it to params method 
    testingParameters(five());
    testingParameters($);
    testingParameters(casted);

    testingParameters( fourToThree() );
    testingParameters( fiveToThree() );
    testingParameters( fiveToFour()  );
}

/////////////////////////////////////////////////////////////////////

public static void exampleFour() {
    // Here we show how all methods that call five() are able to return lower / higher inheritance classes
    Return.One<String>                                                                          one   = fiveToOne();
    Return.Two<String, Integer>                                                                 two   = fiveToTwo();
    Return.Three<String, Integer, Long>                                                         three = fiveToThree();
    Return.Four<String, Integer, Long, Boolean>                                                 four  = fiveToFour();
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = five();

    // paramsThree( one   );  // error!
    // paramsThree( two   );  // error!
    testingParameters( three );
    testingParameters( four  );
    testingParameters( five  );
}

/////////////////////////////////////////////////////////////////////

public static void exampleFive() {
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();

    // Auto casting. The only major benefit of this one is that you won't have to type all of the generic signatures. 
    // If you have $ here, and you would like to express it as a Four, typing those might a be a hassle, at least when typing examples. 
    // So we provide convienience methods to do that. Intellij / Eclipse will generate the field. 

    Return.One<String>                                                                          one   = $.asOne();
    Return.Two<String, Integer>                                                                 two   = $.asTwo();
    Return.Three<String, Integer, Long>                                                         three = $.asThree();
    Return.Four<String, Integer, Long, Boolean>                                                 four  = $.asFour();
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = $.asFive();
    
    // Yes, the last one is not required, but it looks so much more consistent for this example so we added it. It just returns this and is cheap. 
    
    // Note that when casting from say a Five to a Two, the instance would still be a Five! So the values would still be accessible as it is not recreated. 
    // It is the same instance! To transform it, we suggest you use the constructor for that. More features we are adding on the fly!
    
    // So we added it. Now we have: 
    
    exampleSix();
}

/////////////////////////////////////////////////////////////////////

public static void exampleSix() {
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();

    // Clones. 
    Return.One<String>                                                                          one   = $.toOne();
    Return.Two<String, Integer>                                                                 two   = $.toTwo();
    Return.Three<String, Integer, Long>                                                         three = $.toThree();
    Return.Four<String, Integer, Long, Boolean>                                                 four  = $.toFour();
    Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = $.toFive(); 
    
    // Note that these are all clones. Sure, we could have done: 
    Return.Four<String, Integer, Long, Boolean> last = new Return.Four<>($.first(), $.second(), $.third(), $.fourth());
    // But typing that manyally is a bit too much. You won't get much help from editor for that case.  
}

/////////////////////////////////////////////////////////////////////
// Method auto casting, when reassigning aslo
/////////////////////////////////////////////////////////////////////

/**
 * Auto casting since Five inherit Four which inherits Three which ...
 * 
 * @return One
 */
private static Return.One<String> fiveToOne() {
    return five();       // Perfetly valid
}

/**
 * @return Two
 */
private static Return.Two<String, Integer> fiveToTwo() {
    return five();       // Perfetly valid
}

/**
 * @return Three
 */
private static Return.Three<String, Integer, Long> fiveToThree() {
    return five();       // Perfetly valid
}

/**
 * @return Three
 */
private static Return.Three<String, Integer, Long> fourToThree() {
    return four();       // Perfetly valid
}

/**
 * @return Four
 */
private static Return.Four<String, Integer, Long, Boolean> fiveToFour() {
    return five();       // Perfetly valid
}

/////////////////////////////////////////////////////////////////////
// Params demo below. Maybe overkill. 
/////////////////////////////////////////////////////////////////////
// We also provide a syntactic sugar class equivalent to Return called Params to allow you to pass your Return.One, Return.Two .. as Params.One instead.
// Checkout the Params.java class which is an empty skeleton.
// It is only because it might be ugly to have a method param be called Params. 
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////

/**
 * Should you desire to pass the Return... you can declare it as Params.One ... instead.
 * See example calls from {@link momomo.com.platform.Return.examples.Examples#exampleFour()}
 */
private static Return.Three<String, Integer, Long> testingParameters(Params.Three<String, Integer, Long> params) {
    return params;
}
```

### Contribute
Send an email to `opensource{at}momomo.com` if you would like to contribute in any way, make changes or otherwise have thoughts and/or ideas on things to improve.