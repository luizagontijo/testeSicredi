package gerenciador.produtos;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

@Epic("API Tests")
@Feature("Criação de token para Autenticação")
public class GerarTokenTest {

    @Test
    @DisplayName("CT 05 - Status ao gerar token")
    @Description("Este teste verifica o status da API ao gerar o token.")
    @Step("Verificar status na API")
    public void testStatusToken() {
        given()
                .contentType("application/json")
                .body("{\"username\": \"kminchelle\",\n" + "\"password\": \"0lelplR\"}")
            .when()
                .post("https://dummyjson.com/auth/login")
            .then()
                // Nas documentação está código 201, mas está vindo 200
                .statusCode(201);
    }

    @Test
    @DisplayName("CT 06 - Corpo da resposta ao gerar token")
    @Description("Este teste verifica os campos da resposta da API ao gerar o token.")
    @Step("Verificar corpos da resposta na API")
    public void testBodyToken() {
        given()
                .contentType("application/json")
                .body("{\"username\": \"kminchelle\",\n" + "\"password\": \"0lelplR\"}")
            .when()
                .post("https://dummyjson.com/auth/login")
            .then()
                .body("id", equalTo(15))
                .body("username", equalTo("kminchelle"))
                .body("email", equalTo("kminchelle@qq.com"))
                .body("firstName", equalTo("Jeanne"))
                .body("lastName", equalTo("Halvorson"))
                .body("gender", equalTo("female"))
                .body("image", equalTo("https://robohash.org/autquiaut.png"))
                .body("token", notNullValue());
    }

    //poderia ter testes de tentativa de gerar token sem usuario ou sem senha?
}
