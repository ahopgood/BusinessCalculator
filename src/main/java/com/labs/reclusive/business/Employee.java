package com.labs.reclusive.business;

public class Employee {
    
    private final int grossWage;
    
    private final Pension pension;

    public Employee(int grossWage, Pension pension) {
        this.grossWage = grossWage;
        this.pension = pension;
    }

    public int getGrossWage() {
        return grossWage;
    }

    public Pension getPension() {
        return pension;
    }

    public Builder builder(){
        return new Builder();
    }
    
    public static class Builder {
        private int grossWage;
        private Pension pension;

        public Builder setGrossWage(int grossWage) {
            this.grossWage = grossWage;
            return this;
        }

        public Builder setPension(Pension pension) {
            this.pension = pension;
            return this;
        }
        
        public Employee build(){
            return new Employee(this.grossWage, this.pension);
        }
    }
}
