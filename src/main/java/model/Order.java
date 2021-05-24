package model;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * Class represents an object that implements a real life order with some specific
 * attributes
 * @author Daria Miu
 */
public class Order implements Serializable {
    private int orderId;
    private int clientId;
    private LocalDateTime orderDate;

    public Order(int orderId, int clientId, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                clientId == order.clientId &&
                Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, orderDate);
    }



}
