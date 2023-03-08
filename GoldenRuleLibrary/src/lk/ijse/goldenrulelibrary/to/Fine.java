package lk.ijse.goldenrulelibrary.to;

public class Fine {
    private String f_id;
    private String borrowed_book_records_id;
    private double cost;

    public Fine(String f_id, String borrowed_book_records_id, double cost) {
        this.setF_id(f_id);
        this.setBorrowed_book_records_id(borrowed_book_records_id);
        this.setCost(cost);
    }


    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getBorrowed_book_records_id() {
        return borrowed_book_records_id;
    }

    public void setBorrowed_book_records_id(String borrowed_book_records_id) {
        this.borrowed_book_records_id = borrowed_book_records_id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "f_id='" + f_id + '\'' +
                ", borrowed_book_records_id='" + borrowed_book_records_id + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
