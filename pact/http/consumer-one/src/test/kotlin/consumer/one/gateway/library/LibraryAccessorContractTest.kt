package consumer.one.gateway.library

import au.com.dius.pact.consumer.MockServer
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.dsl.newArray
import au.com.dius.pact.consumer.dsl.newJsonObject
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.PactSpecVersion.V3
import au.com.dius.pact.core.model.annotations.Pact
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

// This consumer-side test defines an interaction using a generic 'given' state callback
// Configuring the pact broker connect in the code is not necessary, the created pacts can
// be uploaded with gradle's `pactPublish` task

@ExtendWith(PactConsumerTestExt::class)
internal class LibraryAccessorContractTest {

    val settings = LibraryAccessorSettings(url = "http://localhost")
    val cut = LibraryAccessor(settings)

    @Pact(provider = "provider", consumer = "consumer-one")
    fun getExistingBookPact(pact: PactDslWithProvider) = pact
        .given("Getting book with any ID returns Clean Code")
        .uponReceiving("get single book")
        .path("/books/b3fc0be8-463e-4875-9629-67921a1e00f4")
        .method("GET")
        .headers(mapOf("Accept" to "application/json"))
        .willRespondWith()
        .status(200)
        .headers(mapOf("Content-Type" to "application/json"))
        .body(
            newJsonObject {
                stringType("isbn", "9780132350884")
                stringType("title", "Clean Code")
                newArray("authors") {
                    stringType("Robert C. Martin")
                    stringType("Dean Wampler")
                }
            }
        )
        .toPact()

    @Test
    @PactTestFor(pactMethod = "getExistingBookPact", pactVersion = V3)
    fun `get single existing book interaction`(mockServer: MockServer) {
        settings.url = mockServer.getUrl() // change url at runtime

        val book = cut.getBook("b3fc0be8-463e-4875-9629-67921a1e00f4")!!

        assertThat(book.isbn).isEqualTo("9780132350884")
        assertThat(book.title).isEqualTo("Clean Code")
        assertThat(book.authors).isEqualTo(listOf("Robert C. Martin", "Dean Wampler"))
    }

}
