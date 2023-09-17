package gerenciador.produtos;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

@Epic("API Tests")
@Feature("Buscar produtos com autenticação")
public class BuscarProdutosAutenticadoTest {

    @Test
    @DisplayName("CT 07 - Buscar Produtos na API com token válido")
    @Description("Este teste verifica se a API retorna uma lista de produtos que não esteja vazia.")
    @Step("Verificar Produtos na API - usuário autenticado")
    public void testBuscaProdutoAutenticado() {
        String requestBody = "{\"username\": \"kminchelle\",\n" + "\"password\": \"0lelplR\"}";

        String token = given()
                .body(requestBody)
                .contentType("application/json")
            .when()
                .post("https://dummyjson.com/auth/login")
            .then()
                .extract()
                .path("token");

        given()
                .header("Authorization", token)
                .contentType("application/json")
            .when()
                .get("https://dummyjson.com/auth/products")
            .then()
                .statusCode(200)
                .body("products", notNullValue());
    }

    @Test
    @DisplayName("CT 08 - Buscar Produtos na API sem token")
    @Description("Este teste verifica se a API retorna erro de autenticação.")
    @Step("Verificar erro ao buscar Produtos na API - usuário não autenticado")
    public void testBuscaProdutoSemToken() {
        given()
                .header("Authorization", "")
                .contentType("application/json")
            .when()
                .get("https://dummyjson.com/auth/products")
            .then()
                .statusCode(403)
                .body("message", equalTo("Authentication Problem"));
    }

    @Test
    @DisplayName("CT 09 - Buscar Produtos na API com token expirado")
    @Description("Este teste verifica se a API retorna erro de token expirado.")
    @Step("Verificar erro ao buscar Produtos na API - usuário com token expirado")
    public void testBuscaProdutoTokenExpirado() {
        given()
                .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTUsInVzZXJuYW1lIjoia21pbmNoZWxsZSIsImVtYWlsIjoia21pbmNoZWxsZUBxcS5jb20iLCJmaXJzdE5hbWUiOiJKZWFubmUiLCJsYXN0TmFtZSI6IkhhbHZvcnNvbiIsImdlbmRlciI6ImZlbWFsZSIsImltYWdlIjoiaHR0cHM6Ly9yb2JvaGFzaC5vcmcvYXV0cXVpYXV0LnBuZyIsImlhdCI6MTY5NDg2NTUzOSwiZXhwIjoxNjk0ODY5MTM5fQ.e3F6Ub5BgkJFO8AjFinSelwXoUBn1-26-wc3H7ZibEw")
                .contentType("application/json")
            .when()
                .get("https://dummyjson.com/auth/products")
            .then()
                .statusCode(401)
                .body("name", equalTo("JsonWebTokenError"))
                .body("message", equalTo("Invalid/Expired Token!"));

                //está diferente da documentação. abaixo os retornos que são recebidos:
                //.body("name", equalTo("TokenExpiredError"))
                //.body("message", equalTo("Token Expired!"))
    }
}
