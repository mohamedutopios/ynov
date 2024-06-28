package org.example.productTest;


import org.example.model.Produit;
import org.example.service.ProduitService;
import org.junit.jupiter.api.*;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test de la classe ProduitService")
public class ProduitTest {

    private ProduitService produitService;

    @BeforeEach
    void init() {
        produitService = new ProduitService();
    }

    @Test
    @DisplayName("Creation de produit et incrementation")
    void testCreateProduitAndIncr() {
        Produit produit = produitService.ajouterProduit("chaise", 23);
        Produit produit1 = produitService.ajouterProduit("Table", 89);

        Assertions.assertAll("Vérification des propriétes des produist",
                () -> assertEquals("chaise", produit.getName(), "Le nom de la chaise doit être correct"),
                () -> assertEquals(23, produit.getPrice(), "le prix doit etre correct"),
                () -> assertEquals(1, produit.getId(), "Id est egal 1"),
                () -> assertEquals(2, produit1.getId(), "Id est egal 2"),
                () -> Assertions.assertTrue(produit.getId() > 0, "le produit doit avoir un id"),
                () -> Assertions.assertTrue(produit.getId() < produit1.getId()));
    }

    @Test
    @DisplayName("Supprime un produit")
    void testDeleteProduut() {
        Produit produit = produitService.ajouterProduit("Scooter", 2548.0);
        produitService.supprimeUnProduitParId(produit.getId());
        Assertions.assertThrows(NoSuchElementException.class,
                ()-> produitService.trouverUnProduitParId(produit.getId()));

    }

    @Test
    @DisplayName("Trouver un produit par son id")
   // @Tag("Modification")
   // @Disabled("Pas à tester")
    void testTrouverProduitParId(){
        Produit produit = produitService.ajouterProduit("Iphone",1458);
        Produit produit1 = produitService.ajouterProduit("IPad",1700);

        Assertions.assertAll("Vérification de la recupération par Id",
                () -> assertEquals(produit, produitService.trouverUnProduitParId(produit.getId()),"Je dois avoir un produit"),
                () -> assertEquals(produit1, produitService.trouverUnProduitParId(produit1.getId()),"Je dois avoir un produit"),
                () -> Assertions.assertThrows(NoSuchElementException.class, ()-> produitService.trouverUnProduitParId(75L),""));

    }


    @Test
   @Tag("Recuperation")
    @DisplayName("Récupération de tous les produits")
    void testGetAllProduit() {
        produitService.ajouterProduit("chaise", 23);
        produitService.ajouterProduit("table", 45);

        Assertions.assertAll("Verification de la recupéaration par Id",
                () -> Assertions.assertFalse(produitService.listeProduits().isEmpty(), "La liste ne devrait pas être vide"),
                () -> assertEquals(2, produitService.listeProduits().size(), "La liste devrait contenir deux produits"));
    }


    @Test
    @DisplayName("Mise à jour d'un produit")
    void testUpdateProduit() {
        Produit produit = produitService.ajouterProduit("chaise", 23);
        produitService.mettreAJourProduit(produit.getId(), "mobile", 23);
        Produit produitMisAJour = produitService.trouverUnProduitParId(produit.getId());

        Assertions.assertAll("Vérification après mise à jour du produit",
                () -> assertEquals("mobile", produitMisAJour.getName(), "Le nom du produit doit être mis à jour"),
                () -> assertEquals(23, produitMisAJour.getPrice(), "Le prix du produit doit rester inchangé")
        );
    }


}
