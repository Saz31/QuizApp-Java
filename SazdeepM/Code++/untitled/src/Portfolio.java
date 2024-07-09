import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> stocksOwned = new HashMap<>();

    public void addStock(String stockName, int quantity) {
        stocksOwned.put(stockName, stocksOwned.getOrDefault(stockName, 0) + quantity);
    }

    public void removeStock(String stockName, int quantity) {
        if (stocksOwned.containsKey(stockName)) {
            int currentQuantity = stocksOwned.get(stockName);
            if (currentQuantity <= quantity) {
                stocksOwned.remove(stockName);
            } else {
                stocksOwned.put(stockName, currentQuantity - quantity);
            }
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        if (stocksOwned.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (Map.Entry<String, Integer> entry : stocksOwned.entrySet()) {
                System.out.println("Stock: " + entry.getKey() + ", Quantity: " + entry.getValue());
            }
        }
    }
}