package ee.ria.siva.perftest;

import org.json.JSONObject;

import io.gatling.javaapi.core.Body.WithString;
import io.gatling.javaapi.core.CheckBuilder;
import io.gatling.javaapi.core.CoreDsl;

public abstract class BaseJsonSimulation extends BaseSimulation {

    protected static CheckBuilder.Final getTotalSignatureCheck(int count) {
        return CoreDsl
            .jmesPath("validationReport.validationConclusion.signaturesCount")
            .is(Integer.toString(count));
    }
    protected static CheckBuilder.Final getValidSignatureCheck(int count) {
        return CoreDsl
            .jmesPath("validationReport.validationConclusion.validSignaturesCount")
            .is(Integer.toString(count));
    }

    protected WithString validationRequestFor(String fileName) {
        JSONObject jsonObject = new JSONObject();
        String encoded = readFileFromTestResourcesAndEncodeToBase64String(fileName);
        jsonObject.put("document", encoded);
        jsonObject.put("filename", fileName);
        return CoreDsl.StringBody(jsonObject.toString());
    }

    @Override
    protected String getContentType() {
        return "application/json";
    }

}
