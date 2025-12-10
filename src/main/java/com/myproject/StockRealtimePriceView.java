package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement logic to check if price has changed and log it
        if (stockPrice.getAvgPrice() == lastPrices.getOrDefault(stockPrice.getCode(), Double.NaN))
            return;

        if (lastPrices.containsKey(stockPrice.getCode()))
            Logger.logRealtime(stockPrice.getCode(), stockPrice.getAvgPrice());

        lastPrices.put(stockPrice.getCode(), stockPrice.getAvgPrice());
    }
}
