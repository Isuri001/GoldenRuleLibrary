package lk.ijse.goldenrulelibrary.to;

public class Expenditure {
    private String date;
    private String tras_id;
    private String amount;
    private String invoice_no;

    public Expenditure(String date, String tras_id, String amount, String invoice_no) {
        this.setDate(date);
        this.setTras_id(tras_id);
        this.setAmount(amount);
        this.setInvoice_no(invoice_no);
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTras_id() {
        return tras_id;
    }

    public void setTras_id(String tras_id) {
        this.tras_id = tras_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    @Override
    public String toString() {
        return "Expenditure{" +
                "date='" + date + '\'' +
                ", tras_id='" + tras_id + '\'' +
                ", amount='" + amount + '\'' +
                ", invoice_no='" + invoice_no + '\'' +
                '}';
    }

}
