package lk.ijse.goldenrulelibrary.to;

public class Catagory {
    private String id;
    private String category;
    private String age_group;

    public Catagory(String id, String category, String age_group) {
        this.setId(id);
        this.setCategory(category);
        this.setAge_group(age_group);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    @Override
    public String toString() {
        return "Catagory{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", age_group='" + age_group + '\'' +
                '}';
    }
}

