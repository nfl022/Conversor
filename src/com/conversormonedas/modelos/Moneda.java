package com.conversormonedas.modelos;

import com.google.gson.annotations.SerializedName;

public class Moneda {

    @SerializedName("conversionName")
    private String conversionName;

    @SerializedName("value")
    private double value;

    @SerializedName("base_code")
    private String name;  

    @SerializedName("origin")
    private String origin;

    public Moneda(MonedaRateExchange monedaRateExchange, String targetCurrency, double value) {
        this.name = monedaRateExchange.getBase_code();
        this.value = value;
        this.conversionName = monedaRateExchange.getBase_code() + " to " + targetCurrency;
        this.origin = monedaRateExchange.getTime_last_update_utc();
    }

    public double getValue() {
        return value;
    }

    public String getConversionName() {
        return conversionName;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "Conversion: " + conversionName + ", Rate: " + value + ", Last Updated: " + origin;
    }
}
