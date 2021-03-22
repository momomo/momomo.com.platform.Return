package momomo.com.platform.Return.examples;

import momomo.com.Params;
import momomo.com.Return;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Joseph S.
 */
public class Examples {
    
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
    // Supports the examples below, and are examples of their own. Read them.
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
}
