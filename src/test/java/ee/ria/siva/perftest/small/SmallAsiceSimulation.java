package ee.ria.siva.perftest.small;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseJsonSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class SmallAsiceSimulation extends BaseJsonSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "small-2sig";
    }

    @Override
    protected ScenarioBuilder getScenarioBuilder() {

        return scenario("small 2sig asice")
                .exec(http("POST /validate")
                        .post("/validate")
                        .body(validationRequestFor("ASICE.asice"))
                        .asJson()
                        .check(HttpDsl.status().is(200))
                        .check(getTotalSignatureCheck(2))
                        .check(getValidSignatureCheck(2)));

    }

}