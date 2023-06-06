package ee.ria.siva.perftest.hashcode;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseJsonSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class HashcodeWithHashSimulation extends BaseJsonSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "hashcode";
    }

    @Override
    protected ScenarioBuilder getScenarioBuilder() {

        return scenario("hashcode 2sig with hash")
                .exec(http("POST /validateHashcode")
                        .post("/validateHashcode")
                        .body(compileRequestTemplate("body.template.json", "signatures0.xml",
                                "signatures1.xml"))
                        .asJson()
                        .check(HttpDsl.status().is(200))
                        .check(SIGNATURE_CHECK)
                        .check(VALID_SIGNATURE_CHECK));

    }

}