package ee.ria.siva.perftest.hashcode;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseJsonSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class HashcodeWithoutHashSimulation extends BaseJsonSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "hashcode";
    }

    @Override
    protected ScenarioBuilder getScenarioBuilder() {

        return scenario("hashcode 2sig without hash")
                .exec(http("POST /validateHashcode")
                        .post("/validateHashcode")
                        .body(compileRequestTemplate("body-no-hash.template.json", "signatures0.xml",
                                "signatures1.xml"))
                        .asJson()
                        .check(HttpDsl.status().is(200))
                        .check(getTotalSignatureCheck(2))
                        .check(getValidSignatureCheck(2)));

    }

}