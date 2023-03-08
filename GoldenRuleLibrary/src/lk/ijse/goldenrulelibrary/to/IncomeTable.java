package lk.ijse.goldenrulelibrary.to;

public class IncomeTable {
    private String date;
    private double income;

    public IncomeTable(String date, double income) {
        this.date = date;
        this.income = income;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
