package ee.ria.siva.perftest;

import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import io.gatling.javaapi.core.Body.WithString;
import io.gatling.javaapi.core.CheckBuilder;
import io.gatling.javaapi.core.CoreDsl;

public abstract class BaseJsonSimulation extends BaseSimulation {

    public static CheckBuilder.Final SIGNATURE_CHECK = CoreDsl
            .jmesPath("validationReport.validationConclusion.signaturesCount").is("2");
    public static CheckBuilder.Final VALID_SIGNATURE_CHECK = CoreDsl
            .jmesPath("validationReport.validationConclusion.validSignaturesCount").is("2");

    protected WithString validationRequestFor(String fileName) {
        JSONObject jsonObject = new JSONObject();
        String encoded = readFileFromTestResourcesAndEncodeToBase64String(fileName);
        jsonObject.put("document", encoded);
        jsonObject.put("filename", fileName);
        return CoreDsl.StringBody(jsonObject.toString());
    }

    protected WithString compileRequestTemplate(String templateName, String inputFileName1, String inputFileName2) {
        String templateFile = new String(readFileFromTestResources(templateName), StandardCharsets.UTF_8);
        String encodedInputFile1 = readFileFromTestResourcesAndEncodeToBase64String(inputFileName1);
        String encodedInputFile2 = readFileFromTestResourcesAndEncodeToBase64String(inputFileName2);

        templateFile = templateFile.replaceFirst("\\{\\{FILE1_BASE64\\}\\}", encodedInputFile1);
        templateFile = templateFile.replaceFirst("\\{\\{FILE2_BASE64\\}\\}", encodedInputFile2);

        return CoreDsl.StringBody(templateFile);
    }

    @Override
    protected String getContentType() {
        return "application/json";
    }

}
