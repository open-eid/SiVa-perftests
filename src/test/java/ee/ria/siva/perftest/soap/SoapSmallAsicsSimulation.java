package ee.ria.siva.perftest.soap;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseXmlSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class SoapSmallAsicsSimulation extends BaseXmlSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "small-2sig";
    }

    @Override
    protected ScenarioBuilder getScenarioBuilder() {

        return scenario("soap small 2sig asics")
                .exec(http("POST /soap/validationWebService")
                        .post("/soap/validationWebService")
                        .body(compileRequestTemplate("body.template.xml", "ASICS.asics"))
                        .asXml()
                        .check(HttpDsl.status().is(200))
                        .check(SIGNATURE_CHECK)
                        .check(VALID_SIGNATURE_CHECK));

    }

}