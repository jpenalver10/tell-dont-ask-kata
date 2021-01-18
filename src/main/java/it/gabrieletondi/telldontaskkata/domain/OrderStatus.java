package it.gabrieletondi.telldontaskkata.domain;

public enum OrderStatus {
    APPROVED, REJECTED, SHIPPED, CREATED;

    public boolean isShipped() {
        return this == SHIPPED;
    }

    public boolean isRejected() {
        return this == REJECTED;
    }

    public boolean isApproved() {
        return this == APPROVED;
    }

    public boolean isCreated() {
        return this == CREATED;
    }
}
