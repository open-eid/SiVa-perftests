package ee.ria.siva.perftest.small;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseJsonSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class SmallDdocSimulation extends BaseJsonSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "small-2sig";
    }

    @Override
    protected ScenarioBuilder getScenarioBuilder() {

        return scenario("small 2sig ddoc")
                .exec(http("POST /validate")
                        .post("/validate")
                        .body(validationRequestFor("DDOC.ddoc"))
                        .asJson()
                        .check(HttpDsl.status().is(200))
                        .check(SIGNATURE_CHECK)
                        .check(VALID_SIGNATURE_CHECK));

    }

}