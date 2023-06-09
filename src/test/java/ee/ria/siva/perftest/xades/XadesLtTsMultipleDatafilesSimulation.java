package ee.ria.siva.perftest.xades;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import ee.ria.siva.perftest.BaseJsonSimulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpDsl;

public class XadesLtTsMultipleDatafilesSimulation extends BaseJsonSimulation {

    @Override
    protected String getTestFilesDirectory() {
        return "xades";
    }

    @Override
    protected ScenarioBuilder getScenarioBuilder() {

        return scenario("xades LT TS multiple datafiles")
                .exec(http("POST /validateHashcode")
                        .post("/validateHashcode")
                        .body(compileRequestTemplate("body.template.json", "Valid_XAdES_LT_TS_multiple_datafiles.xml"))
                        .asJson()
                        .check(HttpDsl.status().is(200))
                        .check(getTotalSignatureCheck(1))
                        .check(getValidSignatureCheck(0)));

    }

}