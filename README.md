<img src="doc/img/eu_regional_development_fund_horizontal.jpg" width="350" height="200" alt="European Union European Regional Development Fund"></img>

# SiVa Performance Tests
The goal is to determine how SiVa service handles requests under increasing load over time.
Gatling is used to implement load tests for REST/SOAP interface.

For each container type there is separate simulation available since the business logic and underlying mechanics for 
validating specific container types are vastly different. Following test data is used: 

* BDOC-TS file with two valid signatures (~100KB and 5MB)
* BDOC-TM file with two valid signatures (~100KB and 5MB)
* PDF file with two valid signatures (~200KB and 5MB)
* DDOC file with two valid signatures (~300KB and 5MB)
* ASIC-S file with two valid signatures (~20KB and 50KB)

Each of the files is validated through REST interface. SOAP interface is used with small files for a comparison. 
It is evaluated that the interface (REST or SOAP) do not play noticeable effect on overall results.

Each of the tested files follow the same test plan:

* Five concurrent requests are made per second
* This load is held for period of time
* Concurrent requests are increased by five until 50 concurrent requests per second is achieved
* Latency and throughput is measured on each concurrent request steps

## Prerequisites

* Java 17 JDK
* Checkout [SiVa](https://github.com/open-eid/SiVa) and follow instructions in README.md to bring up required services.

## Running tests

The load tests can be run using the following command:

```Shell
./run.sh {baseUrl} {simulationClass}
```

| Parameter | Mandatory | Description | Example |
| --------- | --------- | ----------- | ------- |
| `baseUrl` | YES | SiVa base URL | `https://localhost:8080` |
| `simulationClass` | YES | Fully qualified class name of a simulation. | `ee.ria.siva.perftest.small.SmallAsiceSimulation` |

Available simulations are as follows:

| Class name                                                        | Description                                                               |
| ----------------------------------------------------------------- | ------------------------------------------------------------------------- |
| `ee.ria.siva.perftest.hashcode.HashcodeWithHashSimulation`        | Scenario for POST /validateHashcode with hashcode                         |
| `ee.ria.siva.perftest.hashcode.HashcodeWithoutHashSimulation`     | Scenario for POST /validateHashcode without hashcode                      |
| `ee.ria.siva.perftest.medium.MediumAsiceSimulation`               | Scenario for POST /validate with medium sized `.asice`                    |
| `ee.ria.siva.perftest.medium.MediumAsicsSimulation`               | Scenario for POST /validate with medium sized `.asics`                    |
| `ee.ria.siva.perftest.medium.MediumBdocSimulation`                | Scenario for POST /validate with medium sized `.bdoc`                     |
| `ee.ria.siva.perftest.medium.MediumDdocSimulation`                | Scenario for POST /validate with medium sized `.ddoc`                     |
| `ee.ria.siva.perftest.medium.MediumPdfSimulation`                 | Scenario for POST /validate with medium sized `.pdf`                      |
| `ee.ria.siva.perftest.small.SmallAsiceSimulation`                 | Scenario for POST /validate with small sized `.asice`                     |
| `ee.ria.siva.perftest.small.SmallAsicsSimulation`                 | Scenario for POST /validate with small sized `.asics`                     |
| `ee.ria.siva.perftest.small.SmallBdocSimulation`                  | Scenario for POST /validate with small sized `.bdoc`                      |
| `ee.ria.siva.perftest.small.SmallDdocSimulation`                  | Scenario for POST /validate with small sized `.ddoc`                      |
| `ee.ria.siva.perftest.small.SmallPdfSimulation`                   | Scenario for POST /validate with small sized `.pdf`                       |
| `ee.ria.siva.perftest.soap.SoapSmallAsiceSimulation`              | Scenario for POST /soap/validationWebService with small sized `.asice`    |
| `ee.ria.siva.perftest.soap.SoapSmallAsicsSimulation`              | Scenario for POST /soap/validationWebService with small sized `.asics`    |
| `ee.ria.siva.perftest.soap.SoapSmallBdocSimulation`               | Scenario for POST /soap/validationWebService with small sized `.bdoc`     |
| `ee.ria.siva.perftest.soap.SoapSmallDdocSimulation`               | Scenario for POST /soap/validationWebService with small sized `.ddoc`     |
| `ee.ria.siva.perftest.soap.SoapSmallPdfSimulation`                | Scenario for POST /soap/validationWebService with small sized `.pdf`      |
| `ee.ria.siva.perftest.xades.XadesLtTmSimulation`                  | Scenario for POST /validateHashcode with xades TM payload                 |
| `ee.ria.siva.perftest.xades.XadesLtTsSimulation`                  | Scenario for POST /validateHashcode with xades TS payload                 |
| `ee.ria.siva.perftest.xades.XadesLtTsMultipleDatafilesSimulation` | Scenario for POST /validateHashcode with xades TS multiple datafiles      |

For example: 

```Shell
./run.sh http://localhost:8080 ee.ria.siva.perftest.small.SmallAsiceSimulation
```

Simulation scenarios are run in 6 steps, each step taking `60` seconds and executing a certain amount of RPS (requests per second):

| Step # | RPS | Duration |
| ------ | --- | -------- |
| `1`    | 5   | 60       |
| `2`    | 10  | 60       |
| `3`    | 20  | 60       |
| `4`    | 30  | 60       |
| `5`    | 40  | 60       |
| `6`    | 50  | 60       |

A total of 9300 requests are made in the span of 6 minutes for each simulation.

## Generated reports

[Gatling reports](https://gatling.io/docs/gatling/reference/current/stats/reports/) are generated in `target/gatling`
folder and can be accessed by opening in runs folder `index.html` in browser.

Use `./mvnw clean` to clear previous test results.
