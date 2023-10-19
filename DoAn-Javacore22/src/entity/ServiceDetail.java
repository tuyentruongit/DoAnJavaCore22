package entity;

import java.io.Serializable;

public class ServiceDetail implements Serializable {
    private int quantity;
    private Service service;

    public ServiceDetail(int quantity, Service service) {
        this.quantity = quantity;
        this.service = service;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ServiceDetail{" +
                "quantity=" + quantity +
                ", service=" + service +
                '}';
    }
}
