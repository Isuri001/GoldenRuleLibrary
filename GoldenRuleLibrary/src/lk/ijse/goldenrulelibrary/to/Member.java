package lk.ijse.goldenrulelibrary.to;

public class Member {
    private String Id;
    private String Name;
    private String Address;
    private String Type;
    private String Contact;

    public Member(String id, String name, String address, String type, String contact) {
        this.Id = id;
        this.Name = name;
        this.Address = address;
        this.Type=type;
        this.Contact=contact;
    }




    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
