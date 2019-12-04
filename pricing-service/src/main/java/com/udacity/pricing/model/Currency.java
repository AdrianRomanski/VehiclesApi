package com.udacity.pricing.model;

import java.util.HashMap;
import java.util.Map;

public enum Currency {
    US_DOLLAR(1.0, 1),
    EURO(0.91, 2),
    JAPANESE_YEN(108.99, 3),
    POUND_STERLING(0.78, 4),
    AUSTRALIAN_DOLLAR(1.48, 5),
    CANADIAN_DOLLAR(1.33, 6),
    SWISS_FRANC(1.0, 7),
    CHINESE_RENMINBI(7.04, 8),
    SWEDISH_KRONA(9.64, 9),
    NEW_ZEALAND_DOLLAR(1.56, 10);

    private final Double exchangeRate;
    private final Integer mostTradedCurrencyRanking;

    private static final Map<Integer, Currency> currencies = new HashMap<Integer, Currency>();

    static {
        for (Currency c : Currency.values()) {
            currencies.put(c.mostTradedCurrencyRanking, c);
        }
    }

    Currency(Double exchangeRate, Integer mostTradedCurrencyRanking) {
        this.mostTradedCurrencyRanking = mostTradedCurrencyRanking;
        this.exchangeRate = exchangeRate;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public static Map<Integer, Currency> getCurrencies() {
        return Map.copyOf(currencies);
    }
}
