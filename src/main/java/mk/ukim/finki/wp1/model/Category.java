package mk.ukim.finki.wp1.model;

import lombok.Data;

@Data  //-> so ova ne treba getteri i setteri
public class Category {

    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category(String name) {
        this.name = name;
    }
}
