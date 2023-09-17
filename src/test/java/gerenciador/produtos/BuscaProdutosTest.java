package gerenciador.produtos;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

@Epic("API Tests")
@Feature("Buscar todos os produtos")
public class BuscaProdutosTest {

    @Test
    @DisplayName("CT 11 - Buscar Produtos na API sem autenticação")
    @Description("Este teste verifica que a lista de produtos retornada pela API não está vazia.")
    @Step("Verificar Produtos na API")
    public void testBuscaProdutos() {
        given()
            .when()
                .get("https://dummyjson.com/products")
            .then()
                .statusCode(200)
                .body("products", notNullValue());
    }
}

