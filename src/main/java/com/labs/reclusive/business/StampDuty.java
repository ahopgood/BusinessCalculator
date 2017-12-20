package com.labs.reclusive.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class StampDuty {

    private static Logger log = LoggerFactory.getLogger(StampDuty.class);
    private List<Taxband> firstTimeBuyerBands;
    private List<Taxband> bands;

    public StampDuty(){
        //11-2017 -- 2018
        this.firstTimeBuyerBands = new LinkedList<>();
        firstTimeBuyerBands.add(Taxband.builder()
                .setMin(0)
                .setMax(300_000)
                .setRate(0)
                .setName("Stamp Duty Full Relief").build());
        firstTimeBuyerBands.add(Taxband.builder()
                .setMin(300_000)
                .setMax(500_000)
                .setRate(5)
                .setName("Stamp Duty Partial Relief").build());
        firstTimeBuyerBands.add(Taxband.builder()
                .setMin(500_000)
                .setMax(925_000)
                .setRate(5)
                .setName("Stamp Duty 5%").build());
        firstTimeBuyerBands.add(Taxband.builder()
                .setMin(925_000)
                .setMax(1_500_000)
                .setRate(10)
                .setName("Stamp Duty 10%").build());
        firstTimeBuyerBands.add(Taxband.builder()
                .setMin(1_500_000)
                .setMax(-1)
                .setRate(12)
                .setName("Stamp Duty Max Threshold").build());

        this.bands = new LinkedList<>();
        bands.add(Taxband.builder()
                .setMin(0)
                .setMax(125_000)
                .setRate(0)
                .setName("Stamp Duty 0%").build());
        bands.add(Taxband.builder()
                .setMin(125_000)
                .setMax(250_000)
                .setRate(2)
                .setName("Stamp Duty 2%").build());
        bands.add(Taxband.builder()
                .setMin(250_000)
                .setMax(925_000)
                .setRate(5)
                .setName("Stamp Duty 5%").build());
        bands.add(Taxband.builder()
                .setMin(925_000)
                .setMax(1_500_000)
                .setRate(10)
                .setName("Stamp Duty 10%").build());
        bands.add(Taxband.builder()
                .setMin(1_500_000)
                .setMax(-1)
                .setRate(12)
                .setName("Stamp Duty Max Threshold").build());
    }

    public StampDuty(List<Taxband> bands, List<Taxband> firstTimeBuyerBands){
        this.bands = bands;
        this.firstTimeBuyerBands = firstTimeBuyerBands;
    }

    public int calculateStampDuty(boolean firstTimeBuyer, int propertyValue){
        List<Taxband> selectedBand;
        if (firstTimeBuyer){
            selectedBand = this.firstTimeBuyerBands;
        } else {
            selectedBand = this.bands;
        }
        return calculateStampDuty(selectedBand, propertyValue);
    }

    int calculateStampDuty(List<Taxband> bands, int propertyValue){
        int tax = 0;
        for (Taxband band : bands){
            int currentTax = band.calculateTax(propertyValue);
            log.info("{} {}", band.getName(), currentTax);
            tax += currentTax;
        }
        return tax;
    }
}
