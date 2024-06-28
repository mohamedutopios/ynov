package org.example.productTest;

import org.example.enums.Categorie;
import org.example.model.Item;
import org.example.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ItemServiceTest {


    private ItemService itemService;

    @BeforeEach
    void init() {
        itemService = new ItemService();
    }


    @ParameterizedTest
    @CsvSource({
            "Ordinateur,1000, ELECTRONIQUE",
            "Chaise, 50,MEUBLE",
            "Pomme,1,ALIMENTAIRE"
    })
    @CsvFileSource()
    void testAjoutItemCsvSource(String nom, double prix, Categorie categorie) {

        Item item = itemService.ajouterItem(1, nom, prix, categorie);

        assertAll("Verification des propriétés de l'item",
                () -> assertEquals(nom, item.getNom()),
                () -> assertEquals(prix, item.getPrix()),
                () -> assertEquals(categorie, item.getCategorie()),
                () -> {
                    switch (categorie) {
                        case ELECTRONIQUE -> assertTrue(item.getPrix()> 100);
                        case MEUBLE -> assertTrue(item.getPrix()> 20 && item.getPrix()<500);
                        case ALIMENTAIRE -> assertTrue(item.getPrix()< 20);

                    }

                }

        );


    }


}
