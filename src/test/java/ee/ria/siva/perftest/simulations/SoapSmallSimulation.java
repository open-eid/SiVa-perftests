package ee.ria.siva.perftest.simulations;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseXmlSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class SoapSmallSimulation extends BaseXmlSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "small-2sig";
    }

    @Override
    protected ScenarioBuilder[] getScenarioBuilder() {

        return new ScenarioBuilder[] {
                scenario("soap small 2sig asice")
                        .exec(http("POST /soap/validationWebService")
                                .post("/soap/validationWebService")
                                .body(compileRequestTemplate("body.template.xml", "ASICE.asice"))
                                .asXml()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK)),

                scenario("soap small 2sig asics")
                        .exec(http("POST /soap/validationWebService")
                                .post("/soap/validationWebService")
                                .body(compileRequestTemplate("body.template.xml", "ASICS.asics"))
                                .asXml()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK)),

                scenario("soap small 2sig bdoc")
                        .exec(http("POST /soap/validationWebService")
                                .post("/soap/validationWebService")
                                .body(compileRequestTemplate("body.template.xml", "BDOC-TM.bdoc"))
                                .asXml()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK)),

                scenario("soap small 2sig ddoc")
                        .exec(http("POST /soap/validationWebService")
                                .post("/soap/validationWebService")
                                .body(compileRequestTemplate("body.template.xml", "DDOC.ddoc"))
                                .asXml()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK)),

                scenario("soap small 2sig pdf")
                        .exec(http("POST /soap/validationWebService")
                                .post("/soap/validationWebService")
                                .body(compileRequestTemplate("body.template.xml", "PDF.pdf"))
                                .asXml()
                                .check(HttpDsl.status().is(200))
                                .check(SIGNATURE_CHECK)
                                .check(VALID_SIGNATURE_CHECK))
        };

    }

}
