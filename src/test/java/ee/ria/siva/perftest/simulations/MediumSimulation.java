package ee.ria.siva.perftest.simulations;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseJsonSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class MediumSimulation extends BaseJsonSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "medium-2sig";
    }

    @Override
    protected ScenarioBuilder[] getScenarioBuilder() {

        return new ScenarioBuilder[] {
                scenario("medium 2sig asice")
                        .exec(http("POST /validate")
                                .post("/validate")
                                .body(validationRequestFor("ASICE.asice"))
                                .asJson()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK)),

                scenario("medium 2sig asics")
                        .exec(http("POST /validate")
                                .post("/validate")
                                .body(validationRequestFor("ASICS.asics"))
                                .asJson()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK)),

                scenario("medium 2sig bdoc")
                        .exec(http("POST /validate")
                                .post("/validate")
                                .body(validationRequestFor("BDOC-TM.bdoc"))
                                .asJson()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK)),

                scenario("medium 2sig ddoc")
                        .exec(http("POST /validate")
                                .post("/validate")
                                .body(validationRequestFor("DDOC.ddoc"))
                                .asJson()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK)),

                scenario("medium 2sig pdf")
                        .exec(http("POST /validate")
                                .post("/validate")
                                .body(validationRequestFor("PDF.pdf"))
                                .asJson()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK))

        };

    }

}
