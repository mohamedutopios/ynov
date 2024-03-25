package org.example.productTest;


import org.example.model.Produit;
import org.example.service.ProduitService;
import org.junit.jupiter.api.Assertions;
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
    void testCreateProduitAndIncr() {
        Produit produit = produitService.ajouterProduit("chaise", 23);
        Produit produit1 = produitService.ajouterProduit("Table", 89);

        Assertions.assertAll("Vérification des propriétes des produist",
                () -> Assertions.assertEquals("chaise", produit.getName(), "Le nom de la chaise doit être correct"),
                () -> Assertions.assertEquals(23, produit.getPrice(), "le prix doit etre correct"),
                () -> Assertions.assertTrue(produit.getId() > 0, "le produit doit avoir un id"),
                () -> Assertions.assertTrue(produit.getId() < produit1.getId()));
    }







}
