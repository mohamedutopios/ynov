package com.example.ynovv.ihm;

import com.example.ynovv.entity.Author;
import com.example.ynovv.service.AuthorService;
import com.example.ynovv.service.BookService;
import com.example.ynovv.service.GenreService;
import com.example.ynovv.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class IHM2 implements CommandLineRunner {


    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private PublisherService publisherService;

    @Override
    public void run(String... args) throws Exception {
        run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    manageAuthors(scanner);
                    break;
                case 2:
                    // Pour les Livres
                    break;
                case 3:
                    // Pour les genres
                    break;
                case 4:
                    // Pour les editeurs
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choice != 5);
    }


    private void displayMainMenu() {
        System.out.println("\nMenu Principal:");
        System.out.println("1. Gérer les auteurs");
        System.out.println("2. Gérer les livres");
        System.out.println("3. Gérer les genres");
        System.out.println("4. Gérer les éditeurs");
        System.out.println("5. Quitter");
        System.out.print("Choisissez une option: ");
    }

    private void manageAuthors(Scanner scanner) {
        System.out.println("\nGestion des auteurs:");
        System.out.println("1. Ajouter un auteur");
        System.out.println("2. Afficher les auteurs");
        System.out.println("3. Modifier un auteur");
        System.out.println("4. Supprimer un auteur");
        System.out.println("5. Retour au menu principal");
        System.out.print("Choisissez une option: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                createAuthor(scanner);
                break;
            case 2:
                readAuthors();
                break;
            case 3:
                updateAuthor(scanner);
                break;
            case 4:
                deleteAuthor(scanner);
                break;
            case 5:
                return;
            default:
                System.out.println("Option invalide. Veuillez réessayer.");
        }
    }

    private void createAuthor(Scanner scanner) {
        scanner.nextLine(); // Consomme la ligne restante
        System.out.print("Entrez le nom de l'auteur: ");
        String name = scanner.nextLine();
        Author author = new Author();
        author.setName(name);
        authorService.save(author);
        System.out.println("Auteur ajouté avec succès.");
    }

    private void readAuthors() {
        List<Author> authors = authorService.findAll();
        if (authors.isEmpty()) {
            System.out.println("Aucun auteur trouvé.");
        } else {
            System.out.println("\nListe des auteurs:");
            authors.forEach(author -> System.out.println("ID: " + author.getId() + ", Nom: " + author.getName()));
        }
    }

    private void updateAuthor(Scanner scanner) {
        System.out.print("Entrez l'ID de l'auteur à mettre à jour: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consomme la ligne restante
        System.out.print("Entrez le nouveau nom de l'auteur: ");
        String newName = scanner.nextLine();

        Author author = authorService.findById(id);
        if (author == null) {
            System.out.println("Auteur non trouvé.");
        } else {
            author.setName(newName);
            authorService.save(author);
            System.out.println("Auteur mis à jour avec succès.");
        }
    }


    private void deleteAuthor(Scanner scanner) {
        System.out.print("Entrez l'ID de l'auteur à supprimer: ");
        Long id = scanner.nextLong();
        try {
            authorService.deleteById(id);
            System.out.println("Auteur supprimé avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'auteur. Vérifiez que l'auteur existe ou qu'il n'est pas lié à d'autres entités.");
        }
    }

}
