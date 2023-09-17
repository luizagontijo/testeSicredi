package gerenciador.produtos;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

@Epic("API Tests")
@Feature("Buscar apenas um produto por id")
public class BuscarProdutosPorIdTest {

    @Test
    @DisplayName("CT 12 - Buscar Produtos por ID na API")
    @Description("Este teste verifica o retorno da API para o produto de ID 1.")
    @Step("Verificar status da resposta e dados do produto")
    public void testBuscaProdutos() {
        given()
            .when()
                .get("https://dummyjson.com/products/1")
            .then()
                .statusCode(200)
                .body("title", equalTo("iPhone 9"))
                .body("description", equalTo("An apple mobile which is nothing like apple"))
                .body("price", equalTo(549))
                .body("discountPercentage", equalTo(12.96f))
                .body("rating", equalTo(4.69f))
                .body("stock", equalTo(94))
                .body("brand", equalTo("Apple"))
                .body("category", equalTo("smartphones"))
                .body("thumbnail", equalTo("https://i.dummyjson.com/data/products/1/thumbnail.jpg"))
                .body("images", contains("https://i.dummyjson.com/data/products/1/1.jpg", "https://i.dummyjson.com/data/products/1/2.jpg", "https://i.dummyjson.com/data/products/1/3.jpg", "https://i.dummyjson.com/data/products/1/4.jpg", "https://i.dummyjson.com/data/products/1/thumbnail.jpg"));
    }

    @Test
    @DisplayName("CT 13 - Buscar Produtos por ID inexistente na API")
    @Description("Este teste verifica mensagem de erro da API ao retornar um produto inexistente.")
    @Step("Verificar mensagem de erro")
    public void testBuscaProdutosIdInexistente() {
        given()
            .when()
                .get("https://dummyjson.com/products/0")
            .then()
                .statusCode(404)
                .body("message", equalTo("Product with id '0' not found"));
    }
}
