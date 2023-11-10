package mk.ukim.finki.wp1.repository;


import mk.ukim.finki.wp1.bootstrap.DataHolder;
import mk.ukim.finki.wp1.model.Category;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository  //adapter so prevzema podatoci
public class InMemoryCategoryRepository {
    //listanje, brisenje, azuriranje na elementi
    public List<Category> findAll(){
       return DataHolder.categories;
    }

    //save method za zacuvuvanje na vrednostite  (SUBMIT button)
    public  Category save(Category c){
        if (c==null || c.getName()==null || c.getName().isEmpty()){
            return null;
        }
        DataHolder.categories.removeIf(r->r.getName().equals(c.getName()));
        DataHolder.categories.add(c);
        return c;
    }


    //ako najde nesto da go vrati a ako ne najde -> nisto ne vrakja(da ne vrakja errors)
    public Optional<Category> findByName(String name){
        return DataHolder.categories.stream().filter(r->r.getName().equals(name))
                .findFirst();  //da go najde prvoto

    }

    //ako soddrzi tekstot text kako argument daden
    public List<Category> search(String text){
        return DataHolder.categories.stream().filter(r->r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
        //da gi vrati kako lista
    }

    //ako imeto e ista so kategorijata ja brise cela kategorija
    public void delete(String name){
        if(name == null){
            return; //ako ne postoi imeto
        }
        DataHolder.categories.removeIf(r->r.getName().equals(name));
    }
}
