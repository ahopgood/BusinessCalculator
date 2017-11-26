package com.labs.reclusive.business;

public class Taxband implements Comparable<Taxband> {

    /**
     * Minimum amount this tax band will apply to
     */
    private final int min;
    /**
     * Maximum amount this tax band will apply to
     */
    private final int max;
    /**
     * The percentage rate at which this tax band will apply
     */
    private final int rate;

    /**
     * The name of this tax band
     */
    private final String name;
    
    public Taxband(int min, int max, int rate, String name) {
        this.min = min;
        this.max = max;
        this.rate = rate;
        this.name = name;
    }
    
    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }

    public int calculateTax(int grossWage){
        int tax = 0;
        if (rate <= 0 ){
            return tax;
        }
        if (grossWage >= min){
            if (grossWage > max && max > 0){
                tax = ( ((max - min) * rate) / 100 );
            } else {
                tax = ( ((grossWage - min) * rate) / 100) ;
            }
        }
        return tax;
    }
    
    
    public static Builder builder(){
        return new Builder();
    }

    public int compareTo(Taxband o) {
//        return Integer.compare(this.min, o.min);
        if (this.min > o.min){
            return 1;
        } else if (this.min < o.min){
            return -1;
        } else {
            return 0;
        }
    }

    public static class Builder {
        private int min;
        private int max;
        private int rate;
        private String name;

        public Builder setMin(int min) {
            this.min = min;
            return this;
        }

        public Builder setMax(int max) {
            this.max = max;
            return this;
        }

        public Builder setRate(int rate) {
            this.rate = rate;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Taxband build(){
            return new Taxband(min, max, rate, name);
        }
    }
}
