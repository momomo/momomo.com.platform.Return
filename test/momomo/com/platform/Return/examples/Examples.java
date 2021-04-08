package momomo.com.platform.Return.examples;

import momomo.com.Params;
import momomo.com.Return;

import javax.swing.*;
import java.util.jar.JarInputStream;

/**
 * @author Joseph S.
 */
public class Examples {
    
    /////////////////////////////////////////////////////////////////////
    
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
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class CAST {
        
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
    }
    
    /////////////////////////////////////////////////////////////////////
    
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
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class COMPOSITION {
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
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class READ {
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
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class PASSING {
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
    }
    
    /////////////////////////////////////////////////////////////////////
    
}
