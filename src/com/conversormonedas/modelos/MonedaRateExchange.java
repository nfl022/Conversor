package com.conversormonedas.modelos;

import java.util.Map;

public class MonedaRateExchange {

    private Map<String, Double> conversion_rates;
    private String base_code;
    private String time_last_update_utc;

    public MonedaRateExchange() {}

    public MonedaRateExchange(Map<String, Double> conversion_rates, String base_code, String time_last_update_utc) {
        this.conversion_rates = conversion_rates;
        this.base_code = base_code;
        this.time_last_update_utc = time_last_update_utc;
    }

    public void setConversion_rates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public void setTime_last_update_utc(String time_last_update_utc) {
        this.time_last_update_utc = time_last_update_utc;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    public String getBase_code() {
        return base_code;
    }

    public String getTime_last_update_utc() {
        return time_last_update_utc;
    }
}
