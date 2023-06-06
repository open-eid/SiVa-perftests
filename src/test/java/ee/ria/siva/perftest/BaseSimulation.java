package ee.ria.siva.perftest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Objects;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public abstract class BaseSimulation extends Simulation {

    private static final String BASE_URL = System.getProperty("baseUrl");

    public BaseSimulation() {
        HttpProtocolBuilder httpProtocol = HttpDsl.http
                .baseUrl(BASE_URL)
                .disableUrlEncoding()
                .disableFollowRedirect()
                .silentResources()
                .contentTypeHeader(getContentType());

        OpenInjectionStep.ConstantRate.ConstantRateOpenInjectionStep[] rate = new OpenInjectionStep.ConstantRate.ConstantRateOpenInjectionStep[] {
                CoreDsl.constantUsersPerSec(5).during(60),
                CoreDsl.constantUsersPerSec(10).during(60),
                CoreDsl.constantUsersPerSec(20).during(60),
                CoreDsl.constantUsersPerSec(30).during(60),
                CoreDsl.constantUsersPerSec(40).during(60),
                CoreDsl.constantUsersPerSec(50).during(60)
        };

        setUp(getScenarioBuilder().injectOpen(rate)).protocols(httpProtocol);
    }

    protected abstract ScenarioBuilder getScenarioBuilder();

    protected abstract String getTestFilesDirectory();

    protected abstract String getContentType();

    protected byte[] readFileFromTestResources(String filename) {
        String path = "/test-files/" + getTestFilesDirectory() + "/" + filename;
        try (InputStream in = Objects.requireNonNull(
                BaseSimulation.class.getResourceAsStream(path))) {
            return in.readAllBytes();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load test file: " + path, e);
        }
    }

    protected String readFileFromTestResourcesAndEncodeToBase64String(String fileName) {
        return Base64.getEncoder().encodeToString(readFileFromTestResources(fileName));
    }

}