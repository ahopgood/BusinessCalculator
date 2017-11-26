package com.labs.reclusive.business;

import java.util.LinkedList;
import java.util.List;

public class Entrypoint {
    
    public static void main(String[] args){
        List<Taxband> incomeTaxBands = new LinkedList<>();
        incomeTaxBands.add(Taxband.builder()
                .setMin(0)
                .setMax(11_500)
                .setRate(0)
                .setName("Personal Allowance").build());
        incomeTaxBands.add(Taxband.builder()
                .setMin(11_501)
                .setMax(45_000)
                .setRate(20)
                .setName("Basic Rate").build());
        incomeTaxBands.add(Taxband.builder()
                .setMin(45_001)
                .setMax(150_000)
                .setRate(40)
                .setName("Higher Rate").build());
        incomeTaxBands.add(Taxband.builder()
                .setMin(150_001)
                .setMax(-1)
                .setRate(45)
                .setName("Additional Rate").build());
        TaxYear.builder()
                .setIncomeTaxBands(incomeTaxBands)
                .build();
        
        
    }
}
