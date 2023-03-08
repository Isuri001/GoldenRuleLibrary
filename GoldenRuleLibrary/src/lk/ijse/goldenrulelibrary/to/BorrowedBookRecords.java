package lk.ijse.goldenrulelibrary.to;

public class BorrowedBookRecords {

    private String borrowed_book_records_id;
    private String m_id;
    private String book_id;
    private String issue_date;
    private String return_date;
    private String status;

    public BorrowedBookRecords(String borrowed_book_records_id, String m_id, String book_id, String issue_date, String return_date, String status) {
        this.setBorrowed_book_records_id(borrowed_book_records_id);
        this.setM_id(m_id);
        this.setBook_id(book_id);
        this.setIssue_date(issue_date);
        this.setReturn_date(return_date);
        this.setStatus(status);
    }


    public String getBorrowed_book_records_id() {
        return borrowed_book_records_id;
    }

    public void setBorrowed_book_records_id(String borrowed_book_records_id) {
        this.borrowed_book_records_id = borrowed_book_records_id;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowedBookRecords{" +
                "borrowed_book_records_id='" + borrowed_book_records_id + '\'' +
                ", m_id='" + m_id + '\'' +
                ", book_id='" + book_id + '\'' +
                ", issue_date='" + issue_date + '\'' +
                ", return_date='" + return_date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}
