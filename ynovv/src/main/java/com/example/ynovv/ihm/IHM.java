package com.example.ynovv.ihm;

import com.example.ynovv.entity.Author;
import com.example.ynovv.entity.Book;
import com.example.ynovv.entity.Genre;
import com.example.ynovv.entity.Publisher;
import com.example.ynovv.repository.BookRepository;
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
public class IHM implements CommandLineRunner {

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
       // create();

        // read();

       // delete();

      //  Scanner sc = new Scanner(System.in);
        //System.out.println("Donne moi un mot Jean Louis : ");
        //String test = sc.next();
/*        System.out.println("Donne moi ton age Jean Louis : ");
        Integer age = sc.nextInt();
        System.out.println("Le mot est : " + test);
        System.out.println("Ton age est : " + age);*/

    }

    private void create(){


        Author author = new Author();
        author.setName("Victor Hugo");

        Author author1 = new Author();
        author1.setName("Charles Baudelaire");

        author = authorService.save(author);
        author1 = authorService.save(author1);

        System.out.println("Auteur1 : " + author + " auteur2 :  " + author1);

        Publisher publisher = new Publisher();
        publisher.setName("Grasset");
        Publisher publisher1 = new Publisher();
        publisher1.setName("Gallimard");

        publisher = publisherService.save(publisher);
        publisher1 = publisherService.save(publisher1);


        Genre genre = new Genre();
        Genre genre1 = new Genre();

        genre.setName("Roman Historique");
        genre1.setName("Poesie");

        genre = genreService.save(genre);
        genre1 = genreService.save(genre1);

        Book book = new Book();
        Book book1 = new Book();

        book.setTitle("Les miserables");
        book1.setTitle("Les Fleurs du mal");

        book.setAuthor(author);
        book1.setAuthor(author1);

        book.getGenres().add(genre);
        book1.getGenres().add(genre1);

        book.setPublisher(publisher);
        book1.setPublisher(publisher1);

         book = bookService.save(book);
         book1 = bookService.save(book1);

        System.out.println(" Book : " + book + " Book1 : " + book1);


    }

    private void read() {
        System.out.println("Lecture des données de test...");

        System.out.println("\nListe des auteurs:");
        List<Author> authors = authorService.findAll();
        authors.forEach(author -> System.out.println("ID: " + author.getId() + ", Nom: " + author.getName()));

        System.out.println("\nListe des éditeurs:");
        List<Publisher> publishers = publisherService.findAll();
        publishers.forEach(publisher -> System.out.println("ID: " + publisher.getId() + ", Nom: " + publisher.getName()));

        System.out.println("\nListe des genres:");
        List<Genre> genres = genreService.findAll();
        genres.forEach(genre -> System.out.println("ID: " + genre.getId() + ", Nom: " + genre.getName()));

        System.out.println("\nListe des livres:");
        List<Book> books = bookService.findAll();
        books.forEach(book -> {
            System.out.println("ID: " + book.getId() + ", Titre: " + book.getTitle() +
                    ", Auteur: " + (book.getAuthor() != null ? book.getAuthor().getName() : "Inconnu") +
                    ", Éditeur: " + (book.getPublisher() != null ? book.getPublisher().getName() : "Inconnu"));

        });
    }


    private void delete() {
        // Exemple de suppression d'un auteur par ID
        Long authorId = 2L;
        authorService.deleteById(authorId);
        System.out.println("Auteur et ses livres supprimés avec succès.");

        // Exemple de suppression d'un livre par ID
        Long bookId = 1L;
        bookService.deleteById(bookId);
        System.out.println("Livre supprimé avec succès.");

        // Exemple de suppression d'un genre par ID
        Long genreId = 1L;
        genreService.deleteById(genreId);
        System.out.println("Genre supprimé avec succès. Les associations de livres ont été mises à jour.");
    }


}
