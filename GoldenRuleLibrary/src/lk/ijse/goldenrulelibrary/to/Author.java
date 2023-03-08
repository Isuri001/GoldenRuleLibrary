package lk.ijse.goldenrulelibrary.to;

public class Author {
    private String id;
    private String name;
    private String subject;
    private String qualification;

    public Author(String id, String name, String subject, String qualification) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.qualification = qualification;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", qualification='" + qualification + '\'' +
                '}';
    }
}