package ee.ria.siva.perftest;

import java.util.Collections;

import io.gatling.javaapi.core.CheckBuilder;
import io.gatling.javaapi.core.CoreDsl;

public abstract class BaseXmlSimulation extends BaseSimulation {

    protected static CheckBuilder.Final getTotalSignatureCheck(int count) {
        return CoreDsl
                .xpath("//ns3:SignaturesCount",
                        Collections.singletonMap("ns3", "http://soap.webapp.siva.openeid.ee/response/"))
                .is(Integer.toString(count));
    }

    protected static CheckBuilder.Final getValidSignatureCheck(int count) {
        return CoreDsl
                .xpath("//ns3:ValidSignaturesCount",
                        Collections.singletonMap("ns3", "http://soap.webapp.siva.openeid.ee/response/"))
                .is(Integer.toString(count));
    }

    @Override
    protected String getContentType() {
        return "application/XML";
    }

}
