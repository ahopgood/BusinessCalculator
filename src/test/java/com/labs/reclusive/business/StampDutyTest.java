package com.labs.reclusive.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StampDutyTest {

    private StampDuty stampDuty = new StampDuty();

    @Before
    public void setUp() throws Exception {
    }

    boolean FIRST_TIME_BUYER = true;
    boolean REGULAR_BUYER = false;

    @Test
    public void testStampDuty_whenUnderThreshold(){
        int grossValue = 125_000;
        int expectedTax = 0;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(REGULAR_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

    @Test
    public void testStampDuty_whenUnderTwoPercentThreshold(){
        int grossValue = 250_000;
        int expectedTax = 2_500;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(REGULAR_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

    @Test
    public void testStampDuty_whenUnderFivePercentThreshold(){
        int grossValue = 925_000;
        int expectedTax = 36_250;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(REGULAR_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

    @Test
    public void testStampDuty_whenUnderTenPercentThreshold(){
        int grossValue = 1_500_000;
        int expectedTax = 93_750;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(REGULAR_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

    @Test
    public void testStampDuty_whenUnderTwelvePercentThreshold(){
        int grossValue = 2_000_000;
        int expectedTax = 153_750;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(REGULAR_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

    @Test
    public void testStampDuty_givenFirstTimeBuyer_whenUnderFullReliefThreshold(){
        int grossValue = 300_000;
        int expectedTax = 0;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(FIRST_TIME_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

    @Test
    public void testStampDuty_givenFirstTimeBuyer_whenUnderPartialReliefThreshold(){
        int grossValue = 500_000;
        int expectedTax = 10_000;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(FIRST_TIME_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

    @Test
    public void testStampDuty_givenFirstTimePurchase_whenUnderFivePercentThreshold(){
        int grossValue = 925_000;
        int expectedTax = 31_250;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(FIRST_TIME_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

    @Test
    public void testStampDuty_givenFirstTimePurchase_whenUnderTenPercentThreshold(){
        int grossValue = 1_500_000;
        int expectedTax = 88_750;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(FIRST_TIME_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

    @Test
    public void testStampDuty_givenFirstTimePurchase_whenUnderTwelvePercentThreshold(){
        int grossValue = 2_000_000;
        int expectedTax = 148_750;
        int remainder = grossValue;
        int taxed = stampDuty.calculateStampDuty(FIRST_TIME_BUYER, grossValue);
        assertEquals(expectedTax, taxed);
    }

}