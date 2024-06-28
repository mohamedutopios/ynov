### Qu'est-ce que Selenium ?

**Selenium** est un outil open-source utilisé pour automatiser les tests des applications web. Il permet de simuler des interactions avec un navigateur web de la même manière qu'un utilisateur le ferait manuellement. Selenium est largement utilisé pour tester des applications web afin de s'assurer qu'elles fonctionnent correctement sur différents navigateurs et systèmes d'exploitation.

### Que permet-il ?

Selenium permet d'automatiser les tâches suivantes :

1. **Tests fonctionnels** : Automatisation des scénarios de test pour vérifier que l'application se comporte comme prévu.
2. **Tests de régression** : Réexécution des tests pour s'assurer que les modifications du code n'ont pas introduit de nouveaux bugs.
3. **Tests sur différents navigateurs** : Vérification de la compatibilité de l'application avec différents navigateurs (Chrome, Firefox, Safari, etc.).
4. **Simulations d'utilisateur** : Interaction avec l'application comme un utilisateur réel le ferait, en cliquant sur des boutons, remplissant des formulaires, naviguant entre les pages, etc.
5. **Tests de performance** : Mesure des temps de réponse et des performances de l'application sous charge.

### Mise en place de Selenium avec Spring Boot et Thymeleaf

#### Prérequis

1. **Spring Boot** : Assurez-vous d'avoir un projet Spring Boot configuré.
2. **Thymeleaf** : Utilisé comme moteur de template dans votre application Spring Boot.
3. **Selenium** : Bibliothèque de test à ajouter à votre projet.

#### Étapes pour l'intégration

##### 1. Ajouter les dépendances Selenium dans `pom.xml`

Ajoutez les dépendances Selenium et WebDriverManager (facilite la gestion des drivers pour différents navigateurs) dans votre fichier `pom.xml` :

```xml
<dependencies>
    <!-- Other dependencies -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.0.0</version>
    </dependency>
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.0.0</version>
    </dependency>
</dependencies>
```

##### 2. Configurer un test Selenium

Créez une classe de test pour les tests Selenium. Par exemple, vous pouvez créer un fichier `SeleniumTest.java` dans le dossier `src/test/java` :

```java
package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHomePage() {
        driver.get("http://localhost:8080");
        String title = driver.getTitle();
        assertThat(title).isEqualTo("Expected Title");
    }
}
```

##### 3. Configurer le serveur pour les tests

Assurez-vous que votre application Spring Boot est en cours d'exécution pendant l'exécution des tests Selenium. Vous pouvez le faire manuellement en démarrant l'application ou en configurant votre test pour démarrer le serveur Spring Boot.

##### 4. Exécuter les tests

Vous pouvez exécuter vos tests Selenium en utilisant Maven ou un IDE comme IntelliJ IDEA ou Eclipse. Pour Maven, utilisez la commande suivante :

```sh
mvn test
```

### Exemple complet

Voici un exemple complet de l'intégration de Selenium avec une application Spring Boot utilisant Thymeleaf.

1. **Spring Boot Controller** :

```java
package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf with Selenium!");
        return "home";
    }
}
```

2. **Thymeleaf Template (home.html)** :

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Home</title>
</head>
<body>
    <h1 th:text="${message}"></h1>
</body>
</html>
```

3. **Test Selenium (SeleniumTest.java)** :

```java
package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHomePage() {
        driver.get("http://localhost:8080");
        WebElement messageElement = driver.findElement(By.tagName("h1"));
        String message = messageElement.getText();
        assertThat(message).isEqualTo("Welcome to Thymeleaf with Selenium!");
    }
}
```
