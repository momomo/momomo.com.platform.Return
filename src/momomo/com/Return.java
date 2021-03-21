package momomo.com;

/**
 * This class and its inner classes are made to support any method to return more than one instance, statically and retain types and what not. 
 * 
 * Using these classes a method can simply return 1-5 return values.
 * 
 * Note, that they are intentionally not final, since we might desire to generate the returns not in one place, the constructor, but might prepare it using if else logic.
 * 
 * See examples at the bottom of this file for examples. 
 * 
 * @author Joseph S.
 */
public class Return { protected Return(){ /* Just a wrapper class for contained classes. Only subclasses allowed to invoke constructor */ }
    
    /**
     * Not really neccessary but good to support the below classes
     */
    public static class One<First> {
        public First first;
    
        public One(First first) {
            this.first = first;
        }
    
        /**
         * Get first 
         */
        public final First first() {
            return first;
        }
    
        /**
         * Set first
         */
        public final One<First> first(First first) {
            this.first = first; return this;
        }
    
        /**
         * Casts to avoid hassles with casting if you ever need it, not that we forsee it.
         * 
         * For consitency only, but also inherited. 
         */
        public One<First> asOne() {
            return this;
        }
    
        /**
         * Copies to new instance, no longer a Two
         * 
         * For consitency only, but also inherited. 
         */
        public One<First> toOne() {
            return new One<>(this.first);
        }
    }
    
    public static class Two<First, Second> extends One<First> {
        public Second second;
        
        public Two(First first, Second second) {
            super(first);
            this.second = second;
        }
    
        /**
         * Get second 
         */
        public final Second second() {
            return second;
        }
    
        /**
         * Set second
         */
        public final Two<First, Second> second(Second second) {
            this.second = second;
            return this;
        }
    
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it. 
         */
        public Two<First, Second> asTwo() {
            return this;
        }
    
        /**
         * Copies to new instance, no longer a Two
         *
         */
        public Two<First, Second> toTwo() {
            return new Two<>(this.first, this.second);
        }
    }
    
    public static class Three<First, Second, Third> extends Two<First, Second> {
        public Third third;
        
        public Three(First first, Second second, Third third) {
            super(first, second);
            this.third = third;
        }
    
        /**
         * Get third 
         */
        public final Third third() {
            return third;
        }
    
        /**
         * Set third
         */
        public final Three<First, Second, Third> third(Third third) {
            this.third = third;
            return this;
        }
    
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it. 
         */
        public Three<First, Second, Third> asThree() {
            return this;
        }
    
        /**
         * Copies to new instance, no longer a Three 
         *
         */
        public Three<First, Second, Third> toThree() {
            return new Three<>(this.first, this.second, this.third);
        }
    }
    
    public static class Four<First, Second, Third, Fourth> extends Three<First, Second, Third> {
        public Fourth fourth;
        
        public Four(First first, Second second, Third third, Fourth fourth) {
            super(first, second, third);
            this.fourth = fourth;
        }
    
        public Four(Five<First, Second, Third, Fourth, ?> $) {
            this($.first, $.second, $.third, $.fourth);
        }
    
        /**
         * Get fourth 
         */
        public final Fourth fourth() {
            return fourth;
        }
    
        /**
         * Set fourth
         */
        public final Four<First, Second, Third, Fourth> fourth(Fourth fourth) {
            this.fourth = fourth;
            return this;
        }
    
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it. 
         */
        public Four<First, Second, Third, Fourth> asFour() {
            return this;
        }
    
        /**
         * Copies to new instance, no longer a Four
         *
         */
        public Four<First, Second, Third, Fourth> toFour() {
            return new Four<>(this.first, this.second, this.third, this.fourth);
        }
    }
    
    public static final class Five<First, Second, Third, Fourth, Fifth> extends Four<First, Second, Third, Fourth> {
        public Fifth fifth;
        
        public Five(First first, Second second, Third third, Fourth fourth, Fifth fifth) {
            super(first, second, third, fourth);
            this.fifth = fifth;
        }
    
        /**
         * Get fifth 
         */
        public final Fifth fifth() {
            return fifth;
        }
    
        /**
         * Set fifth
         */
        public final Five<First, Second, Third, Fourth, Fifth> fifth(Fifth fifth) {
            this.fifth = fifth;
            return this;
        }
    
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it.
         * 
         * For consitency only.
         */
        public Five<First, Second, Third, Fourth, Fifth> asFive() {
            return this;
        }
    
        /**
         * Copies to new instance, no longer the same Five. 
         * 
         * For consitency only. 
         */
        public Five<First, Second, Third, Fourth, Fifth> toFive() {
            return new Five<>(this.first, this.second, this.third, this.fourth, this.fifth);
        }
    }
    
}
