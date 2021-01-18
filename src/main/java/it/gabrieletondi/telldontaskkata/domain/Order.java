package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private BigDecimal total = BigDecimal.ZERO;
    private String currency = "EUR";
    private List<OrderItem> items = new ArrayList<>();
    private BigDecimal tax = BigDecimal.ZERO;
    private OrderStatus status;
    private int id;

    public Order() {
        // empty
    }

    public Order(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getCurrency() {
        return currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addItem(OrderItem orderItem) {

        this.items.add(orderItem);

        accumulateTotal(orderItem.getTaxedAmount());
        accumulateTax(orderItem.getTax());
    }

    private void accumulateTotal(BigDecimal taxedAmount) {
        this.total = this.total.add(taxedAmount);
    }

    private void accumulateTax(BigDecimal tax) {
        this.tax = this.tax.add(tax);
    }

    public boolean isShipped() {
        return this.status.isShipped();
    }

    public boolean isRejected() {
        return this.status.isRejected();
    }

    public boolean isApproved() {
        return this.status.isApproved();
    }

    public void moveStatus(boolean approved) {
        this.status = approved ? OrderStatus.APPROVED : OrderStatus.REJECTED;
    }

    public boolean isCreated() {
        return this.status.isCreated();
    }

    public void setShippedStatus() {
        this.status = OrderStatus.SHIPPED;
    }
}
