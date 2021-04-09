package momomo.com.platform.Return.examples;

import momomo.com.Objects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Joseph S.
 */
public class ExamplesToo {
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * @return Four in the end
     */
    public static Objects.Four<StringBuilder, String, List<String>, Boolean> example1() {
        Objects.One<String>                  a   = new Objects.One<>  ("first");
        Objects.Two<String, Integer>         b   = new Objects.Two<>  ("first", 2);
        Objects.Three<String, Integer, Long> c   = new Objects.Three<>("first", 2, 3L);
        // ... 
        
        // We can read
        String  first  = a.first;
        Integer second = b.second;
    
        // We can set 
        c.first  = "första"; // Directly
        c.second = 10;       // Directly
        c.third(11L);        // Using setter
        
        // Return four
        return new Objects.Four<>(new StringBuilder("first"), "second", new ArrayList<>(), false);
    }
    
    /**
     * Showing how to auto cast using asOne(), asTwo() ...
     */
    public static void example2() {
        Objects.Four<StringBuilder, String, List<String>, Boolean> $ = new Objects.Four<>(new StringBuilder("first"), "second", new ArrayList<>(), false);
    
        Objects.One<StringBuilder>         a = $.asOne(); // Casting from Four<> to One<>
        Objects.Two<StringBuilder, String> b = $.asTwo(); // Casting from Four<> to Two<>
    
        // Still the same instance!
        if ( $.equals(a) && $.equals(b) ) {
            System.out.println(true);
        }
    
        // All the same instance! a == b == $, just casted!
        if ( a == b && b == $ ) {
            System.out.println(true);
        }
    
        // Still the same instance!
        if ( a instanceof Objects.Four ) {
            System.out.println(true);
        }
    
        // Still the same instance!
        if ( a instanceof Objects.Three ) {
            System.out.println(true);
        }
    
        // Still the same instance!
        if ( a instanceof Objects.Two ) {
            System.out.println(true);
        }
    
        // Still the same instance!
        if ( a instanceof Objects.One ) {
            System.out.println(true);
        }
        
        $.first = new StringBuilder("Changing $.first will effectivaly change a and b as well!");
        System.out.println($.first); // All the same
        System.out.println(a.first); // All the same
        System.out.println(b.first); // All the same
    }
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Showing how to clone / copy to new types using toOne(), toTwo() ... 
     */
    public static void example3() {
        Objects.Four<StringBuilder, String, ArrayList<Object>, Boolean> $ = new Objects.Four<>(new StringBuilder("first"), "second", new ArrayList<>(), false);
    
        Objects.One<StringBuilder>         a = $.toOne(); // Not casting, but copying to a new instance!
        Objects.Two<StringBuilder, String> b = $.toTwo(); // Not casting, but copying to a new instance!
    
        // Not the same instance!
        if ( $.equals(a) && $.equals(b) ) {
            System.out.println(false);
        }
    
        // False!
        if ( a instanceof Objects.Four ) {
            System.out.println(false);
        }
    
        // False!
        if ( a instanceof Objects.Three ) {
            System.out.println(false);
        }
    
        // False!
        if ( a instanceof Objects.Two ) {
            System.out.println(false);
        }
    
        // True!
        if ( a instanceof Objects.One ) {
            System.out.println(true);
        }
    
        $.first = new StringBuilder("Changing $.first will NOT change a and b as well!");
        System.out.println($.first); // Changing $.first will NOT change a and b too!
        System.out.println(a.first); // "first" 
        System.out.println(b.first); // "first" 
    }
    
    /////////////////////////////////////////////////////////////////////
    // Methods returning higher order, all eventually from five() 
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Create One<> by calling higher order one two()
     */
    private static Objects.One<String> one() {
        return two();  // A two can be casted to a one
    }
    
    /**
     * Create Two<> by calling higher order one three(), four() or five()
     */
    private static Objects.Two<String, Integer> two() {
        if ( false ) return four();                 // A four can be casted to two
        if ( false ) return five();                 // A five can be casted to two
        if ( false ) new Objects.Two<>("first", 2);  // We can create it directly
        
        return three(); // A three can be casted to a two
    }
    
    /**
     * Create Three<> by calling higher order one four()
     */
    private static Objects.Three<String, Integer, Long> three() {
        return four();
    }
    
    /**
     * Create Four<> by calling higher order one five()
     */
    private static Objects.Four<String, Integer, Long, Boolean> four() {
        return five();
    }
    
    /**
     * Create Five<>
     */
    private static Objects.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five() {
        return new Objects.Five<>("first", 2, 3L, Boolean.FALSE, new LinkedHashMap<>());
    }
    
    /////////////////////////////////////////////////////////////////////
    // Using one(), two(), three(), four(), five() 
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Just showing all generic types will still resolve. 
     * 
     * We return one casted to Two<> from a Five<>
     */
    public static Objects.Two<String, Integer> example5() {
        Objects.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
        
        String                                         first  = $.first;
        Integer                                        second = $.second;
        Long                                           third  = $.third;
        Boolean                                        fourth = $.fourth;
        LinkedHashMap<String, List<ArrayList<String>>> fifth  = $.fifth;
        
        return $.asTwo();
    }
    
    
    /**
     * Casting again
     */
    public static void example6() {
        Objects.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
        
        // Auto casting. The only major benefit of this one is that you won't have to type all of the generic signatures. 
        // If you have $ here, and you would like to express it as a Four, typing those might a be a hassle, at least when typing examples. 
        // So we provide convienience methods to do that. Intellij / Eclipse will generate the field. 
        
        Objects.One<String>                                                                          one   = $.asOne();
        Objects.Two<String, Integer>                                                                 two   = $.asTwo();
        Objects.Three<String, Integer, Long>                                                         three = $.asThree();
        Objects.Four<String, Integer, Long, Boolean>                                                 four  = $.asFour();
        Objects.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = $.asFive();
        
        // Yes, the last one is not required, but it looks so much more consistent for this example so we added it. 
        // It just returns this and is cheap. 
    }
    
    /**
     * 'Cloning' again
     */
    public static void example7() {
        Objects.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
        
        // Clones. 
        Objects.One<String>                                                                          one   = $.toOne();
        Objects.Two<String, Integer>                                                                 two   = $.toTwo();
        Objects.Three<String, Integer, Long>                                                         three = $.toThree();
        Objects.Four<String, Integer, Long, Boolean>                                                 four  = $.toFour();
        Objects.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = $.toFive();
        
        // Note that these are all clones. Sure, we could have done:  
        Objects.Four<String, Integer, Long, Boolean> last = new Objects.Four<>($.first, $.second, $.third, $.fourth);
        // But typing that manually is a bit too much. You won't get much help from editor typing that either!  
    }
    
}
