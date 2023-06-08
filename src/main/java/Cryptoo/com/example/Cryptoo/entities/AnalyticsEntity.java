package Cryptoo.com.example.Cryptoo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "analytics")
public class AnalyticsEntity implements Serializable {

    private static final long serialVersionUID = -3204114820588241243L;

    @Id
    private String anaylticId;

    private String companyId;
    private int totalServices=0;
    private int servicesClicks=0;
    private int servicesImpression=0;
    private Double avgServicePrice=0.0;
    private int totalOrders=0;
    private int newOrders=0;
    private int completedOrders=0;
    private int acceptedOrders=0;
    private int cancelledOrders=0;
    private int cancelledOrdersByClient=0;
    private Double totalEarnings=0.0;
    private Double lastMounthEarnings=0.0;

    public String getAnaylticId() {
        return anaylticId;
    }

    public void setAnaylticId(String anaylticId) {
        this.anaylticId = anaylticId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getTotalServices() {
        return totalServices;
    }

    public void setTotalServices(int totalServices) {
        this.totalServices = totalServices;
    }

    public int getServicesClicks() {
        return servicesClicks;
    }

    public void setServicesClicks(int servicesClicks) {
        this.servicesClicks = servicesClicks;
    }

    public int getServicesImpression() {
        return servicesImpression;
    }

    public void setServicesImpression(int servicesImpression) {
        this.servicesImpression = servicesImpression;
    }

    public Double getAvgServicePrice() {
        return avgServicePrice;
    }

    public void setAvgServicePrice(Double avgServicePrice) {
        this.avgServicePrice = avgServicePrice;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getNewOrders() {
        return newOrders;
    }

    public void setNewOrders(int newOrders) {
        this.newOrders = newOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(int completedOrders) {
        this.completedOrders = completedOrders;
    }

    public int getAcceptedOrders() {
        return acceptedOrders;
    }

    public void setAcceptedOrders(int acceptedOrders) {
        this.acceptedOrders = acceptedOrders;
    }

    public int getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(int cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }

    public int getCancelledOrdersByClient() {
        return cancelledOrdersByClient;
    }

    public void setCancelledOrdersByClient(int cancelledOrdersByClient) {
        this.cancelledOrdersByClient = cancelledOrdersByClient;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public Double getLastMounthEarnings() {
        return lastMounthEarnings;
    }

    public void setLastMounthEarnings(Double lastMounthEarnings) {
        this.lastMounthEarnings = lastMounthEarnings;
    }
}
