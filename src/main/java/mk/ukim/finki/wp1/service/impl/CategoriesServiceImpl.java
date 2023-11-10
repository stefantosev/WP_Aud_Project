package mk.ukim.finki.wp1.service.impl;


import mk.ukim.finki.wp1.model.Category;
import mk.ukim.finki.wp1.repository.InMemoryCategoryRepository;
import mk.ukim.finki.wp1.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

//ke gi implementira site metodi od interfaceot

@Service  //implementacija na odreden servis vo biznis slojot
public class CategoriesServiceImpl implements CategoryService {

    private  final InMemoryCategoryRepository categoryRepo; //final za da ne se menue nikogas

    public CategoriesServiceImpl(InMemoryCategoryRepository categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    //da proverime dali loso ime vnesuvame, ako ne da go kreirame
    @Override
    public Category create(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepo.save(c);
        return c;
    }


    @Override
    public Category update(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepo.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }

        categoryRepo.delete(name);
    }

    @Override
    public List<Category> categories(){
        return categoryRepo.findAll(); //
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepo.search(searchText);
    }


}
