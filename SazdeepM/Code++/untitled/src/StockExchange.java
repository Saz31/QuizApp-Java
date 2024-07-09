import java.util.*;

public class StockExchange {
    private List<Order> buyOrders = new ArrayList<>();
    private List<Order> sellOrders = new ArrayList<>();
    private Portfolio portfolio;

    public StockExchange(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void addOrder(Order order) {
        if (order.getType().equals("BUY")) {
            buyOrders.add(order);
            buyOrders.sort(Comparator.comparingDouble(Order::getPrice).reversed().thenComparingLong(Order::getTime));
        } else {
            sellOrders.add(order);
            sellOrders.sort(Comparator.comparingDouble(Order::getPrice).thenComparingLong(Order::getTime));
        }
        matchOrders();
    }

    private void matchOrders() {
        List<String> executedOrders = new ArrayList<>();
        Iterator<Order> buyIterator = buyOrders.iterator();
        while (buyIterator.hasNext()) {
            Order buyOrder = buyIterator.next();
            Iterator<Order> sellIterator = sellOrders.iterator();
            while (sellIterator.hasNext()) {
                Order sellOrder = sellIterator.next();
                if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                    int executedQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());
                    executedOrders.add(sellOrder.getOrderId() + ", " + buyOrder.getOrderId() + ", " + executedQuantity + ", " + sellOrder.getPrice());
                    buyOrder.setQuantity(buyOrder.getQuantity() - executedQuantity);
                    sellOrder.setQuantity(sellOrder.getQuantity() - executedQuantity);
                    portfolio.addStock(buyOrder.getStockName(), executedQuantity);
                    portfolio.removeStock(sellOrder.getStockName(), executedQuantity);
                    if (buyOrder.getQuantity() == 0) {
                        buyIterator.remove();
                        break;
                    }
                    if (sellOrder.getQuantity() == 0) {
                        sellIterator.remove();
                    }
                }
            }
        }
        executedOrders.forEach(System.out::println);
    }
}