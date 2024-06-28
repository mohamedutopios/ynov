package com.example.unittestmockito.controller;

// Importations des classes nécessaires pour les tests
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.unittestmockito.model.User;
import com.example.unittestmockito.service.UserService;

// Annotation pour indiquer que le test est un test Spring Boot et qu'il doit démarrer un serveur web sur un port aléatoire
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Annotation pour spécifier que le profil "test" doit être utilisé lors de l'exécution de ce test
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    // Injection automatique du TestRestTemplate pour effectuer des requêtes HTTP
    @Autowired
    private TestRestTemplate restTemplate;

    // Injection automatique du UserService pour interagir avec les utilisateurs dans la base de données
    @Autowired
    private UserService userService;

    // Injection automatique de l'environnement pour obtenir les propriétés du serveur
    @Autowired
    private Environment environment;

    // Variable pour stocker l'URL de base de l'API utilisateur
    private String baseUrl;

    // Méthode exécutée avant chaque test pour configurer l'environnement de test
    @BeforeEach
    public void setUp() {
        // Obtention du port local sur lequel le serveur est démarré
        String port = environment.getProperty("local.server.port");
        // Construction de l'URL de base de l'API utilisateur
        baseUrl = "http://localhost:" + port + "/api/users";
        // Suppression de tous les utilisateurs avant chaque test pour garantir un environnement propre
        userService.deleteAllUsers();
    }

    // Test pour vérifier que tous les utilisateurs peuvent être récupérés
    @Test
    public void testGetAllUsers() {
        // Création d'un nouvel utilisateur
        User user = new User(null, "John Doe", "john.doe@example.com");
        // Sauvegarde de l'utilisateur dans la base de données via le service utilisateur
        userService.saveUser(user);

        // Exécution d'une requête GET pour récupérer tous les utilisateurs
        ResponseEntity<User[]> response = restTemplate.getForEntity(baseUrl, User[].class);
        // Vérification que le statut de la réponse est HTTP 200 (OK)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // Vérification que le corps de la réponse n'est pas vide
        assertThat(response.getBody()).isNotEmpty();
    }

    // Test pour vérifier que l'utilisateur peut être récupéré par son ID
    @Test
    public void testGetUserById() {
        // Création d'un nouvel utilisateur
        User user = new User(null, "John Doe", "john.doe@example.com");
        // Sauvegarde de l'utilisateur dans la base de données
        User savedUser = userService.saveUser(user);

        // Exécution d'une requête GET pour récupérer l'utilisateur par son ID
        ResponseEntity<User> response = restTemplate.getForEntity(baseUrl + "/" + savedUser.getId(), User.class);
        // Vérification que le statut de la réponse est HTTP 200 (OK)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // Vérification que le corps de la réponse n'est pas nul
        assertThat(response.getBody()).isNotNull();
        // Vérification que le nom de l'utilisateur dans la réponse est correct
        assertThat(response.getBody().getName()).isEqualTo("John Doe");
    }

    // Test pour vérifier que l'utilisateur peut être créé
    @Test
    public void testCreateUser() {
        // Création d'un nouvel utilisateur
        User user = new User(null, "Jane Doe", "jane.doe@example.com");

        // Exécution d'une requête POST pour créer l'utilisateur
        ResponseEntity<User> response = restTemplate.postForEntity(baseUrl, user, User.class);
        // Vérification que le statut de la réponse est HTTP 201 (Created)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        // Vérification que le corps de la réponse n'est pas nul
        assertThat(response.getBody()).isNotNull();
        // Vérification que l'utilisateur créé a un ID non nul
        assertThat(response.getBody().getId()).isNotNull();
    }

    // Test pour vérifier que l'utilisateur peut être mis à jour
    @Test
    public void testUpdateUser() {
        // Création d'un nouvel utilisateur
        User user = new User(null, "John Doe", "john.doe@example.com");
        // Sauvegarde de l'utilisateur dans la base de données
        User savedUser = userService.saveUser(user);

        // Création d'un utilisateur mis à jour
        User updatedUser = new User(null, "John Smith", "john.smith@example.com");
        // Création d'une entité HTTP pour la requête PUT
        HttpEntity<User> requestEntity = new HttpEntity<>(updatedUser);

        // Exécution d'une requête PUT pour mettre à jour l'utilisateur
        ResponseEntity<User> response = restTemplate.exchange(baseUrl + "/" + savedUser.getId(), HttpMethod.PUT, requestEntity, User.class);
        // Vérification que le statut de la réponse est HTTP 200 (OK)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // Vérification que le corps de la réponse n'est pas nul
        assertThat(response.getBody()).isNotNull();
        // Vérification que le nom de l'utilisateur dans la réponse a été mis à jour
        assertThat(response.getBody().getName()).isEqualTo("John Smith");
    }

    // Test pour vérifier que l'utilisateur peut être supprimé
    @Test
    public void testDeleteUser() {
        // Création d'un nouvel utilisateur
        User user = new User(null, "John Doe", "john.doe@example.com");
        // Sauvegarde de l'utilisateur dans la base de données
        User savedUser = userService.saveUser(user);

        // Exécution d'une requête DELETE pour supprimer l'utilisateur
        ResponseEntity<Void> response = restTemplate.exchange(baseUrl + "/" + savedUser.getId(), HttpMethod.DELETE, null, Void.class);
        // Vérification que le statut de la réponse est HTTP 204 (No Content)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        // Vérification que l'utilisateur a été supprimé de la base de données
        assertThat(userService.getUserById(savedUser.getId())).isEmpty();
    }
}
