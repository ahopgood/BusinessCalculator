package com.labs.reclusive.business;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaxbandTest {

    @Test
    public void givenReversedList_thenSort(){
        List<Taxband> bands = new LinkedList<>();
        bands.add(Taxband.builder()
                .setMin(10).build());
        bands.add(Taxband.builder()
                .setMin(5).build());
        bands.add(Taxband.builder()
                .setMin(1).build());

        Collections.sort(bands);
        assertEquals(1, bands.get(0).getMin());
        assertEquals(5, bands.get(1).getMin());
        assertEquals(10, bands.get(2).getMin());
    }

    @Test
    public void givenSortedList_thenSort(){
        List<Taxband> bands = new LinkedList<>();
        bands.add(Taxband.builder()
                .setMin(1).build());
        bands.add(Taxband.builder()
                .setMin(5).build());
        bands.add(Taxband.builder()
                .setMin(10).build());

        Collections.sort(bands);
        assertEquals(1, bands.get(0).getMin());
        assertEquals(5, bands.get(1).getMin());
        assertEquals(10, bands.get(2).getMin());
    }

    @Test
    public void givenMixedList_thenSort(){
        List<Taxband> bands = new LinkedList<>();
        bands.add(Taxband.builder()
                .setMin(5).build());
        bands.add(Taxband.builder()
                .setMin(10).build());
        bands.add(Taxband.builder()
                .setMin(1).build());

        Collections.sort(bands);
        assertEquals(1, bands.get(0).getMin());
        assertEquals(5, bands.get(1).getMin());
        assertEquals(10, bands.get(2).getMin());
    }
    
    @Test
    public void testGreaterThan(){
        Taxband bigger = Taxband.builder()
                .setMin(10).build();
        Taxband smaller = Taxband.builder()
                .setMin(1).build();
        
        assertEquals(1, bigger.compareTo(smaller));
    }

    @Test
    public void testLessThan(){
        Taxband bigger = Taxband.builder()
                .setMin(10).build();
        Taxband smaller = Taxband.builder()
                .setMin(1).build();

        assertEquals(-1, smaller.compareTo(bigger));
    }
    
    @Test
    public void testEqualTo(){
        Taxband bigger = Taxband.builder()
                .setMin(10).build();
        Taxband smaller = Taxband.builder()
                .setMin(10).build();

        assertEquals(0, smaller.compareTo(bigger));
    }

    @Test
    public void testBuilder(){
        Taxband band = Taxband.builder()
                .setMin(1)
                .setMax(2)
                .setRate(3)
                .setName("test").build();
        assertEquals(1, band.getMin());
        assertEquals(2, band.getMax());
        assertEquals(3, band.getRate());
        assertEquals("test", band.getName());
    }

    List<Taxband> incomeTaxBands = new LinkedList<Taxband>();

    Taxband personal = Taxband.builder()
            .setMin(0)
            .setMax(11_500)
            .setRate(0)
            .setName("Personal Allowance").build();

    Taxband basic = Taxband.builder()
            .setMin(11_500)
            .setMax(45_000)
            .setRate(20)
            .setName("Basic Rate").build();
    Taxband higher = Taxband.builder()
            .setMin(45_000)
            .setMax(150_000)
            .setRate(40)
            .setName("Higher Rate").build();
    Taxband additionalRate = Taxband.builder()
            .setMin(150_000)
            .setMax(-1)
            .setRate(45)
            .setName("Additional Rate").build();
    @Before
    public void setUp(){

    }

    @Test
    public void testCalculateTax_givenRateIsZero_whenWageIsZero_thenDontTax(){
        assertEquals(0, personal.calculateTax(0));
    }

    @Test
    public void testCalculateTax_givenRateIsZero_whenWageLessThanMax_thenDontTax(){
        assertEquals(0, personal.calculateTax(11_499));
    }

    @Test
    public void testCalculateTax_givenRateIsZero_whenWageEqualToMax_thenDontTax(){
        assertEquals(0, personal.calculateTax(11_500));
    }

    @Test
    public void testCalculateTax_givenRateIsZero_whenWageGreaterThanMax_thenDontTax(){
        assertEquals(0, personal.calculateTax(11_501));
    }
    
    //Basic rate 20%
    @Test
    public void testCalculateTax_givenRateIsTwenty_andMinGreaterThanZero_whenWageLessThanMin_thenDontTax(){
        assertEquals(0, basic.calculateTax(11_499));
    }
    @Test
    public void testCalculateTax_givenRateIsTwenty_andMinGreaterThanZero_whenWageLessThanMax_thenTaxBetweenMinAndWage(){
        assertEquals(20, basic.calculateTax(11_600));
    }

    @Test
    public void testCalculateTax_givenRateIsTwenty_andMinGreaterThanZero_whenWageEqualToMax_thenTaxBetweenMinAndMax(){
        assertEquals(6_700, basic.calculateTax(45_000)); //33_500 taxed at 20%
    }

    @Test
    public void testCalculateTax_givenRateIsTwenty_andMinGreaterThanZero_whenWageGreaterThanMax_thenTaxBetweenMinAndMax(){
        assertEquals(6_700, basic.calculateTax(45_001));
    }

    @Test
    public void testCalculateTax_givenRateIsFourtyFive_andMinGreaterThanZero_whenMaxIsLimitless_thenTaxAllFromMinToWage(){
        assertEquals(45, additionalRate.calculateTax(150_100));
    }
}