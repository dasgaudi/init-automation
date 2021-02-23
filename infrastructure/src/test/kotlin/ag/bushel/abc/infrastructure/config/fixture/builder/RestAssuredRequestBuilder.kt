package ag.bushel.abc.infrastructure.config.fixture.builder

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.fail
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class RestAssuredRequestBuilder(
    val apiPath: String = "/api"
)

fun RestAssuredRequestBuilder.get(path: String): Response {
    return RestAssured.get("$apiPath$path")
}

// inline fun <reified T> RequestBuilder.get(path: String): T {
//    return RestAssured.get(path)
//        .then().extract().`as`(T::class.java)
// }

inline fun <reified T> RestAssuredRequestBuilder.post(path: String, body: T): Response {
    return given()
        .contentType(ContentType.JSON)
        .body(body)
        .post("$apiPath$path")
}

inline fun <reified T> RestAssuredRequestBuilder.put(path: String, body: T): Response {
    return given()
        .contentType(ContentType.JSON)
        .body(body)
        .put("$apiPath$path")
}

fun RestAssuredRequestBuilder.delete(path: String): Response {
    return RestAssured.delete("$apiPath$path")
}

inline fun <reified T> Response.extract(): ResponseEntity<T> {
    val response = this.then().extract()

    return ResponseEntity(
        response.body().`as`(T::class.java),
        HttpStatus.valueOf(response.statusCode())
    )
}

inline fun <T> ResponseEntity<T>.assert(statusCode: Int? = null, block: T.() -> Unit): ResponseEntity<T> {
    statusCode?.let {
        assertThat(this.statusCode).isEqualTo(HttpStatus.valueOf(it))
    }

    this.body?.let {
        it.run {
            block()
        }
    } ?: fail("Response body shouldn't be null")

    return this
}

fun <T> ResponseEntity<T>.body(): T {
    return this.body ?: fail("Response body shouldn't be null")
}
