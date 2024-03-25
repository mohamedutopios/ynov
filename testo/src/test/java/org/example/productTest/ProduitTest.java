package org.example.productTest;


import org.example.model.Produit;
import org.example.service.ProduitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test de la classe ProduitService")
public class ProduitTest {

    private ProduitService produitService;

    @BeforeEach
    void init(){
        produitService = new ProduitService();
    }

    @Test
    @DisplayName("Creation de produit et incrementation")
    void testCreateProduitAndIncr(){

        Produit produit =




    }




}
