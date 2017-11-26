package com.labs.reclusive.business;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TaxYearTest {

    List<Taxband> incomeTaxBands = new LinkedList<>();
    List<Taxband> nationalInsuranceBands = new LinkedList<>();
    
    @Before
    public void setUp(){
        incomeTaxBands.add(Taxband.builder()
                .setMin(0)
                .setMax(11_500)
                .setRate(0)
                .setName("Personal Allowance").build());
        incomeTaxBands.add(Taxband.builder()
                .setMin(11_500)
                .setMax(45_000)
                .setRate(20)
                .setName("Basic Rate").build());
        incomeTaxBands.add(Taxband.builder()
                .setMin(45_000)
                .setMax(150_000)
                .setRate(40)
                .setName("Higher Rate").build());
        incomeTaxBands.add(Taxband.builder()
                .setMin(150_000)
                .setMax(-1)
                .setRate(45)
                .setName("Additional Rate").build());
        
        nationalInsuranceBands.add(Taxband.builder()
                .setName("Personal Allowance")
                .setRate(0)
                .setMin(0)
                .setMax(680)
                .build());
        nationalInsuranceBands.add(Taxband.builder()
                .setName("Primary Threshold")
                .setRate(12)
                .setMin(0)
                .setMax(3750)
                .build());
        nationalInsuranceBands.add(Taxband.builder()
                .setName("Upper Earnings Limit")
                .setRate(2)
                .setMin(3750)
                .setMax(-1)
                .build());
    }
    
    @Test
    public void testCalculateTax(){
        TaxYear year = TaxYear.builder()
                .setIncomeTaxBands(incomeTaxBands)
                .setNationalInsuranceBands(nationalInsuranceBands)
                .build();
        year.calculateIncomeTax(67500);
        year.calculateNationalInsurance(67500);
        
    }
}