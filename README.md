# ⚙️ Requisitos

#### Java JDK 11+ (Recomendado: Amazon Corretto, OpenJDK)

#### Gradle 7.6+ (o versión compatible)

#### IDE (IntelliJ IDEA, Eclipse, VS Code con extensiones Java)

#### Git (para clonar el repositorio)

# 🚀 Instalación

#### 1. Clonar el repositorio:

```bash
git clone https://github.com/garceteclaudio/restassured-base-project.git
cd restassured-base-project/
```

#### 2. Instalar dependencias:

```bash
gradle build
```

# 📂 Estructura del Proyecto

```text
restassured-base-project/
├── src/
│   ├── main/java/          # Código fuente (opcional)
│   └── test/java/          # Pruebas
│       ├── api/            # Clases para pruebas API
│       ├── runners/        # Runners de pruebas (TestNG/JUnit)
│       └── resources/      # Archivos de configuración (ej: .json)
├── build.gradle            # Configuración de Gradle
├── gradlew                 # Wrapper de Gradle (Linux/Mac)
├── gradlew.bat             # Wrapper de Gradle (Windows)
└── README.md               # Este archivo
```

# ▶️ Ejecución de Pruebas

#### 1. Ejecutar todas las pruebas (Terminal)

```bash
gradle test
```

#### 2. Ejecutar una clase específica

```bash
gradle test --tests "com.tuproyecto.api.LoginTest"
```

# 📝 Ejemplo de Prueba

#### Ejemplo: Prueba de API REST

```java
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class UserAPITest {
    private static final String BASE_URL = "https://api.example.com/users";

    @Test
    public void testGetUserById() {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .when()
                .get(BASE_URL + "/1");

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue());
    }

    @Test
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"John Doe\", \"email\": \"john@example.com\" }";

        Response response = RestAssured.given()
                .body(requestBody)
                .header("Content-Type", "application/json")
                .when()
                .post(BASE_URL);

        response.then()
                .statusCode(201)
                .body("id", notNullValue());
    }
}
```

# ⚡ Configuración Avanzada

#### 1. build.gradle (Dependencias clave)

```groovy
plugins {
    id 'java'
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'io.rest-assured:rest-assured:5.3.0'
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.9.2'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.9.2'
    testImplementation 'org.hamcrest:hamcrest:2.2'
}

test {
    useJUnitPlatform()
}
```
#### 2. Variables de Entorno
#### Crea un archivo src/test/resources/config.properties:
```properties
base.url=https://api.example.com
api.key=tu-api-key
```

#### Uso en Java:
```java
import java.util.Properties;
import java.io.InputStream;

public class ConfigLoader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }
}

// Uso:
String baseUrl = ConfigLoader.getProperty("base.url");
```

# 🔁 Integración con CI/CD

#### Ejemplo para GitHub Actions (.github/workflows/cypress.yml)

```yaml
name: Java CI
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 11
        uses: actions/setup-java@v3
        with:
          java-version: '11'
          distribution: 'temurin'
      - name: Grant execute permission for gradlew
        run: chmod +x gradlew
      - name: Run tests
        run: ./gradlew test
```

# 🤝 Contribución

#### 1. Haz un fork del proyecto.

#### 2. Crea una rama (git checkout -b feature/nueva-funcionalidad).

#### 3. Realiza tus cambios y haz commit (git commit -m "Añade nueva prueba").

#### 4. Haz push a la rama (git push origin feature/nueva-funcionalidad).

#### 5. Abre un Pull Request.
