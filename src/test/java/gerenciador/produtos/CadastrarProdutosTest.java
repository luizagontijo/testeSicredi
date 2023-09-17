package gerenciador.produtos;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

@Epic("API Tests")
@Feature("Criação de produto")
public class CadastrarProdutosTest {

    @Test
    @DisplayName("CT 10 - Cadastrar Produtos na API")
    @Description("Este teste verifica o cadastro de produtos na API.")
    @Step("Verificar Cadastro de Produtos na API")
    public void testCadastraProdutos() {
        String requestBody = "{\n" +
                "    \"title\": \"Perfume do Mar\",\n" +
                "    \"description\": \"Cheirinho gostoso da brisa de um fim de noite na praia\",\n" +
                "    \"price\": 55,\n" +
                "    \"discountPercentage\": 1.5,\n" +
                "    \"rating\": 3.9,\n" +
                "    \"stock\": 33,\n" +
                "    \"brand\": \"Jequiti\",\n" +
                "    \"category\": \"fragrances\",\n" +
                "    \"thumbnail\": \"https://i.dummyjson.com/data/products/11/thumnail.jpg\"\n" +
                "}";

        given()
                .contentType("application/json")
                .body(requestBody)
            .when()
                .post("https://dummyjson.com/products/add")
            .then()
                .statusCode(200)
                .body("title", equalTo("Perfume do Mar"))
                .body("price", equalTo(55))
                .body("stock", equalTo(33))
                .body("rating", equalTo(3.9f))
                .body("thumbnail", equalTo("https://i.dummyjson.com/data/products/11/thumnail.jpg"))
                .body("description", equalTo("Cheirinho gostoso da brisa de um fim de noite na praia"))
                .body("brand", equalTo("Jequiti"))
                .body("category", equalTo("fragrances"));
    }
}

//deveria ser necessária autenticação para cadastrar produtos
//podia ter uma verificação na lista de produtos, se ele foi adicionado, mas como os dados nao sao persistentes, não foi possivel realizar o teste