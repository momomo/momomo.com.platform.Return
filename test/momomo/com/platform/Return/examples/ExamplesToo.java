package momomo.com.platform.Return.examples;

import momomo.com.Params;
import momomo.com.Return;

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
    public static Return.Four<StringBuilder, String, List<String>, Boolean> example1() {
        Return.One<String>                  a   = new Return.One<>  ("first");
        Return.Two<String, Integer>         b   = new Return.Two<>  ("first", 2);
        Return.Three<String, Integer, Long> c   = new Return.Three<>("first", 2, 3L);
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
    
    /**
     * Showing how to auto cast using asOne(), asTwo() ...
     */
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
    
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Showing how to clone / copy to new types using toOne(), toTwo() ... 
     */
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
    
    /////////////////////////////////////////////////////////////////////
    // Methods returning higher order, all eventually from five() 
    /////////////////////////////////////////////////////////////////////
    
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
    
    /////////////////////////////////////////////////////////////////////
    // Using one(), two(), three(), four(), five() 
    /////////////////////////////////////////////////////////////////////
    
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
    
    
    /**
     * Casting again
     */
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
    
    /**
     * 'Cloning' again
     */
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
    
    /////////////////////////////////////////////////////////////////////
    // Param = Return - Showing the Param class. 
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Should you desire to pass say Return.Three<...>, you can declare it as Param.Three instead, just for readability and because it would look wierder having a method with (Return.Three<...> param)
     * 
     * We return casted to One<>!
     */
    private static Return.One<String> eatme(Params.Three<String, Integer, Long> param) {
        return param.asOne();
    }
    
    /**
     * Passing Return to eatme method which declares a Param.Three<>  
     */
    public static void example8() {
        // We can pass it to params method 
        Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
        
        eatme($);
        // eatme( $.asOne() );   // Compiler error!
        // eatme( $.asTwo() );   // Compiler error!
        
        eatme( $.asThree() );
        eatme( $.asFour () );
        eatme( $.asFive () );
    }
    
}