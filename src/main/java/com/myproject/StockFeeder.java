package com.myproject;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern
    private StockFeeder() {
    }

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if (instance == null)
            instance = new StockFeeder();
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList
        for (int i = 0; i < stockList.size(); i++) {
            if (!stockList.get(i).getCode().equals(stock.getCode()))
                continue;

            stockList.set(i, stock);
            return;
        }

        stockList.add(stock);
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence
        if (stockList.stream().noneMatch(stock -> stock.getCode().equals(code))) {
            Logger.errorRegister(code);
            return;
        }

        viewers.computeIfAbsent(code, k -> new ArrayList<>());
        List<StockViewer> viewersList = viewers.get(code);

        if (viewersList.stream().anyMatch(e -> e.getClass().equals(stockViewer.getClass()))) {
            Logger.errorRegister(code);
            return;
        }

        viewersList.add(stockViewer);
    }

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging
        if (stockList.stream().noneMatch(stock -> stock.getCode().equals(code))) {
            Logger.errorUnregister(code);
            return;
        }

        List<StockViewer> viewersList = viewers.get(code);
        if (viewersList == null || !viewersList.remove(stockViewer))
            Logger.errorUnregister(code);
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates
        viewers.get(stockPrice.getCode()).forEach(viewer -> {
            viewer.onUpdate(stockPrice);
        });
    }
}