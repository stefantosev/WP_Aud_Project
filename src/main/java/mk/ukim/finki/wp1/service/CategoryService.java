package mk.ukim.finki.wp1.service;

import mk.ukim.finki.wp1.model.Category;

import java.util.List;

//ke gi pisueme samo metodite
public interface CategoryService {

    Category create(String name, String description);
    Category update(String name, String description);
    void delete(String name);

    List<Category> categories();
    List<Category> searchCategories(String searchText);
}
