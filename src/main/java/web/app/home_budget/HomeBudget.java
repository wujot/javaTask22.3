package web.app.home_budget;

import java.sql.Date;


public class HomeBudget {
    private int id;
    private String type;
    private String description;
    private double amount;
    private Date date;

    public HomeBudget() {}

    public HomeBudget(String type, String description, double amount, Date date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HomeBudget:\n" +
                "===========\n" +
                "Id: " + id + "\n" +
                "Type: " + type + "\n" +
                "Description: " + description + "\n" +
                "Amount: " + amount + "\n" +
                "Date: " + date + "\n";
    }
}
