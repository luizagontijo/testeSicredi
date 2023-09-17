package gerenciador.produtos;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;

@Epic("API Tests")
@Feature("Buscar usuário para autenticação")
public class BuscarUsuariosTest {

    @Test
    @DisplayName("CT 02 - Buscar lista de usuários na API")
    @Description("Este teste verifica que a lista de usuários retornada pela API não está vazia.")
    @Step("Verificar Usuários na API")
    public void testBuscaUsuarioParaAutenticacao() {
        given()
            .when()
                .get("https://dummyjson.com/users")
            .then()
                .statusCode(200)
                .body("users", notNullValue());
    }

    @Test
    @DisplayName("CT 03 - Buscar campo \"username\" na lista de usuários na API")
    @Description("Este teste verifica que todos os usuário na lista retornada pela API tem o campo \"username\" e que ele não está vazio.")
    @Step("Verificar campo \"username\" na lista de usuários da API")
    public void testVerificarUsuariosComCampoUsername() {
        given()
            .when()
                .get("https://dummyjson.com/users")
            .then()
                .body("users", notNullValue())
                .body("users.every { it.containsKey('username') }", equalTo(true)) // Verifique se todos os usuários têm o campo "username"
                .body("users.every { it.username != null }", equalTo(true)); //
    }

    @Test
    @DisplayName("CT 04 - Buscar campo \"password\" na lista de usuários na API")
    @Description("Este teste verifica que todos os usuário na lista retornada pela API tem o campo \"password\" e que ele não está vazio.")
    @Step("Verificar campo \"password\" na lista de usuários da API")
    public void testVerificarUsuariosComCampoPassword() {
        given()
            .when()
                .get("https://dummyjson.com/users")
            .then()
                .body("users", notNullValue())
                .body("users.every { it.containsKey('password') }", equalTo(true)) //todos os usuarios tem a chave password
                .body("users.every { it.password != null }", equalTo(true)); //
    }
}
