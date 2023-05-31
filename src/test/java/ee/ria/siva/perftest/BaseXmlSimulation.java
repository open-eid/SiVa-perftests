package ee.ria.siva.perftest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

import io.gatling.javaapi.core.Body.WithString;
import io.gatling.javaapi.core.CheckBuilder;
import io.gatling.javaapi.core.CoreDsl;

public abstract class BaseXmlSimulation extends BaseSimulation {

    protected static CheckBuilder.Final SIGNATURE_CHECK = CoreDsl
            .xpath("//ns3:ValidSignaturesCount",
                    Collections.singletonMap("ns3", "http://soap.webapp.siva.openeid.ee/response/"))
            .is("2");
    protected static CheckBuilder.Final VALID_SIGNATURE_CHECK = CoreDsl
            .xpath("//ns3:SignaturesCount",
                    Collections.singletonMap("ns3", "http://soap.webapp.siva.openeid.ee/response/"))
            .is("2");

    protected WithString compileRequestTemplate(String templateName, String inputFileName) {
        String templateFile = new String(readFileFromTestResources(templateName), StandardCharsets.UTF_8);
        String encodedInputFile = readFileFromTestResourcesAndEncodeToBase64String(inputFileName);

        templateFile = templateFile.replaceFirst("\\{\\{FILE_NAME\\}\\}", inputFileName);
        templateFile = templateFile.replaceFirst("\\{\\{FILE_BASE64\\}\\}", encodedInputFile);

        return CoreDsl.StringBody(templateFile);
    }

    @Override
    protected String getContentType() {
        return "application/XML";
    }

}
