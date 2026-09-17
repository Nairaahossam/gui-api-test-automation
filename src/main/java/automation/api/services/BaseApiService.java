package automation.api.services;

import automation.api.config.ApiConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiService {

    protected final RequestSpecification requestSpec;

    protected BaseApiService() {
        this.requestSpec = new RequestSpecBuilder()
                .setBaseUri(ApiConfigReader.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }
}
