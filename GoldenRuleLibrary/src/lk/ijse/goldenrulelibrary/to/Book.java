package lk.ijse.goldenrulelibrary.to;

public class Book {
    private String book_Id;
    private String pub_Id;
    private String au_Id;
    private String c_Id;
    private String b_Name;
    private String edition;

    public Book(String book_Id, String pub_Id, String au_Id, String c_Id, String b_Name, String edition) {
        this.setBook_Id(book_Id);
        this.setPub_Id(pub_Id);
        this.setAu_Id(au_Id);
        this.setC_Id(c_Id);
        this.setB_Name(b_Name);
        this.setEdition(edition);
    }


    public String getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(String book_Id) {
        this.book_Id = book_Id;
    }

    public String getPub_Id() {
        return pub_Id;
    }

    public void setPub_Id(String pub_Id) {
        this.pub_Id = pub_Id;
    }

    public String getAu_Id() {
        return au_Id;
    }

    public void setAu_Id(String au_Id) {
        this.au_Id = au_Id;
    }

    public String getC_Id() {
        return c_Id;
    }

    public void setC_Id(String c_Id) {
        this.c_Id = c_Id;
    }

    public String getB_Name() {
        return b_Name;
    }

    public void setB_Name(String b_Name) {
        this.b_Name = b_Name;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_Id='" + book_Id + '\'' +
                ", pub_Id='" + pub_Id + '\'' +
                ", au_Id='" + au_Id + '\'' +
                ", c_Id='" + c_Id + '\'' +
                ", b_Name='" + b_Name + '\'' +
                ", edition='" + edition + '\'' +
                '}';
    }
}
