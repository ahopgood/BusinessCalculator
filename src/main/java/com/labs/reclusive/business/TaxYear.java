package com.labs.reclusive.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TaxYear {
    private static final Logger LOG = LoggerFactory.getLogger(TaxYear.class);
    
    private final List<Taxband> incomeTaxBands;
    
    private final List<Taxband> nationalInsuranceBands;
    
    private final List<Taxband> studentLoanBands;

    public TaxYear(List<Taxband> incomeTaxBands, List<Taxband> nationalInsuranceBands, List<Taxband> studentLoanBands) {
        this.incomeTaxBands = incomeTaxBands;
        this.nationalInsuranceBands = nationalInsuranceBands;
        this.studentLoanBands = studentLoanBands;
    }
    
    public void verifyTaxBands(){
        
    }
    
    public int calculateIncomeTax(int grossWage){
        return calculateAnnualTax(grossWage, incomeTaxBands);
    }
    
    public int calculateNationalInsurance(int grossWage){
        return calculateAnnualTax(grossWage, nationalInsuranceBands);
    }
    
    public int calculateAnnualTax(int grossWage, List<Taxband> bands){
        int totalTax = 0;
        for (Taxband band : bands){
            int tax = band.calculateTax(grossWage);
            LOG.info("{} = {}" , band.getName(), tax);
            totalTax += tax;
        }
        return totalTax;
    }
    
    public static Builder builder(){
        return new Builder();
    }
    
    public static class Builder {
        private List<Taxband> incomeTaxBands;
        private List<Taxband> nationalInsuranceBands;
        private List<Taxband> studentLoanBands;
        
        public TaxYear build(){
            TaxYear year = new TaxYear(this.incomeTaxBands, this.nationalInsuranceBands, this.studentLoanBands); 
            return year;
        }

        public Builder setIncomeTaxBands(List<Taxband> incomeTaxBands) {
            this.incomeTaxBands = incomeTaxBands;
            return this;
        }

        public Builder setNationalInsuranceBands(List<Taxband> nationalInsuranceBands) {
            this.nationalInsuranceBands = nationalInsuranceBands;
            return this;
        }

        public Builder setStudentLoanBands(List<Taxband> studentLoanBands) {
            this.studentLoanBands = studentLoanBands;
            return this;
        }
    }
}
