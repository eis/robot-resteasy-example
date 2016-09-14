package fi.eis.applications.robot;

import static org.hamcrest.Matchers.equalTo;

import java.net.URISyntaxException;

import io.restassured.http.ContentType;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ActualTester {
    private String persistedInfo = null;

    private String baseURI;
    private String basePath;
    private String acceptHeader;
    public ActualTester() {
        System.err.println(this.getClass() + " created");
    }
    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
    public void setAcceptHeader(String acceptHeader) {
        this.acceptHeader = acceptHeader;
    }

    public void authenticationTest(int statusCode, String responseMessage) {
        try {
            java.net.URI uri = new java.net.URI(baseURI);
            RestAssured.port = uri.getPort();
            RestAssured.baseURI = String.format("%s://%s", uri.getScheme(), uri.getHost());
            RestAssured.basePath = basePath;
            RequestSpecification reqSpec = 
                    io.restassured.RestAssured
                        .given()
                            .log().all()
                            .accept(acceptHeader)
                            .contentType(ContentType.JSON)
                    ;
            ValidatableResponse resp = reqSpec
                .when()
                    .get("")
                .then()
                    .log().all()
                    .statusCode(statusCode);
            if (responseMessage != null) {
                resp.body("Accept", equalTo(responseMessage));
            }
            
            if (this.persistedInfo == null) {
                this.persistedInfo = resp.extract().path("Accept");
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("problem with " + baseURI, e);
        }
    }
}
