package com.labs.reclusive.business;

public class Pension {
    private final int personalRate;
    private final int companyRate;

    public Pension(int personalRate, int companyRate) {
        this.personalRate = personalRate;
        this.companyRate = companyRate;
    }

    public int getPersonalRate() {
        return personalRate;
    }

    public int getCompanyRate() {
        return companyRate;
    }

    public static Builder builder(){
        return new Builder();
    }
    
    public static class Builder {
        private int personalRate;
        private int companyRate;

        public Builder setPersonalRate(int personalRate) {
            this.personalRate = personalRate;
            return this;
        }

        public Builder setCompanyRate(int companyRate) {
            this.companyRate = companyRate;
            return this;
        }

        public Pension build(){
            return new Pension(this.personalRate, this.companyRate);
        }
    }
}
