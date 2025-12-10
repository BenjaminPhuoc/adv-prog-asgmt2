package com.myproject;

import java.util.ArrayList;
import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;

    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        // TODO: Implement constructor
        this.hoseLib = hoseLib;
        this.stockCodes = stockCodes;
    }

    @Override
    public List<StockPrice> fetch() {
        // TODO: Fetch stock data and convert it to StockPrice list
        List<StockPrice> stockPrices = new ArrayList<>();

        List<HoseData> hoseDataList = hoseLib.getPrices(stockCodes);
        for (HoseData hoseData : hoseDataList) {
            stockPrices.add(convertToStockPrice(hoseData));
        }
        return stockPrices;
    }

    private StockPrice convertToStockPrice(HoseData hseData) {
        // TODO: Convert HoseData to StockPrice
        return new StockPrice(hseData.getStockCode(), hseData.getPrice(), hseData.getVolume(), hseData.getTimestamp());
    }
}
