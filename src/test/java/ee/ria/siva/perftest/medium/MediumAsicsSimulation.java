package ee.ria.siva.perftest.medium;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseJsonSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class MediumAsicsSimulation extends BaseJsonSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "medium-2sig";
    }

    @Override
    protected ScenarioBuilder getScenarioBuilder() {

        return scenario("medium 2sig asics")
                .exec(http("POST /validate")
                        .post("/validate")
                        .body(validationRequestFor("ASICS.asics"))
                        .asJson()
                        .check(HttpDsl.status().is(200))
                        .check(getTotalSignatureCheck(2))
                        .check(getValidSignatureCheck(2)));

    }

}