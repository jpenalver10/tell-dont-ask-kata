package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class OrderItem {

    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public OrderItem(Product product, int quantity) {

        this.product = product;
        this.quantity = quantity;

        calculateAmounts();
    }

    private void calculateAmounts() {

        final BigDecimal unitaryTax = product.getPrice().divide(valueOf(100)).multiply(product.getCategory().getTaxPercentage()).setScale(2, HALF_UP);
        final BigDecimal unitaryTaxedAmount = product.getPrice().add(unitaryTax).setScale(2, HALF_UP);

        this.taxedAmount = unitaryTaxedAmount.multiply(BigDecimal.valueOf(quantity)).setScale(2, HALF_UP);
        this.tax = unitaryTax.multiply(BigDecimal.valueOf(quantity));
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

}
