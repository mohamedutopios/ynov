package org.example.service;

import org.example.enums.Categorie;
import org.example.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemService {


    private List<Item> items = new ArrayList<>();



    public Item ajouterItem(int id, String nom, double prix, Categorie categorie) {

        switch (categorie){

            case ELECTRONIQUE -> {
                if(prix<100) throw new IllegalArgumentException("Le prix ne peut être inférieur à 100 euros");
            }
            case MEUBLE -> {
                if(prix<20 || prix > 500) throw new IllegalArgumentException("Le prix ne peut être inférieur à 20 euros et supérieur à 500");
            }

            case ALIMENTAIRE -> {
                if(prix>20) throw new IllegalArgumentException("Le prix ne peut être supérieur à 20 euros ");
            }

        }
        Item item = new Item(id,nom,prix,categorie);
        items.add(item);
        return item;

    }






}
