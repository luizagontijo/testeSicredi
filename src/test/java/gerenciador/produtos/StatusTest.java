package gerenciador.produtos;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

@Epic("API Tests")
@Feature("Buscar o status da aplicação")
public class StatusTest {

    @Test
    @DisplayName("CT 01 - Verificar o Status da Aplicação")
    @Description("Este teste verifica o status da aplicação e alguns detalhes adicionais.")
    @Step("Verificar o Status da Aplicação")
    public void testChecarStatusAplicacao() {
        given()
            .when()
                .get("https://dummyjson.com/test")
            .then()
                .statusCode(200)
                .body("status", equalTo("ok"))
                .body("method", equalTo("GET"))
                .log().all();
    }
}
