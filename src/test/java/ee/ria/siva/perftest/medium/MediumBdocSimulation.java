package ee.ria.siva.perftest.medium;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseJsonSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class MediumBdocSimulation extends BaseJsonSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "medium-2sig";
    }

    @Override
    protected ScenarioBuilder getScenarioBuilder() {

        return scenario("medium 2sig bdoc")
                .exec(http("POST /validate")
                        .post("/validate")
                        .body(validationRequestFor("BDOC-TM.bdoc"))
                        .asJson()
                        .check(HttpDsl.status().is(200))
                        .check(SIGNATURE_CHECK)
                        .check(VALID_SIGNATURE_CHECK));

    }

}