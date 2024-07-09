public class Order {
    private int orderId;
    private long time;
    private String stockName;
    private String type; // BUY or SELL
    private int quantity;
    private double price;

    public Order(int orderId, long time, String stockName, String type, int quantity, double price) {
        this.orderId = orderId;
        this.time = time;
        this.stockName = stockName;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public long getTime() { return time; }
    public String getStockName() { return stockName; }
    public String getType() { return type; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}