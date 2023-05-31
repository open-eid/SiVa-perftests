<img src="doc/img/eu_regional_development_fund_horizontal.jpg" width="350" height="200" alt="European Union European Regional Development Fund"></img>

# SiVa Performance Tests

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
| `simulationClass` | YES | Fully qualified class name of a simulation. | `ee.ria.siva.perftest.simulations.HashcodeSimulation` |

Available simulations are as follows:

| Class name                                             | Description |
| ------------------------------------------------------ | ----------- |
| `ee.ria.siva.perftest.simulations.HashcodeSimulation`  | Contains 2 scenarios for POST /validateHashcode, one with the hashcode and one without |
| `ee.ria.siva.perftest.simulations.MediumSimulation`    | Contains 5 scenarios for POST /validate, each with either a medium sized `.asice`, `.asics`, `.bdoc`, `.ddoc` or `.pdf` payload |
| `ee.ria.siva.perftest.simulations.SmallSimulation`     | Contains 5 scenarios for POST /validate, each with either a small sized `.asice`, `.asics`, `.bdoc`, `.ddoc` or `.pdf` payload |
| `ee.ria.siva.perftest.simulations.SoapSmallSimulation` | Contains 5 scenarios for POST /soap/validationWebService, each with either a small sized `.asice`, `.asics`, `.bdoc`, `.ddoc` or `.pdf` payload |

For example: 

```Shell
./run.sh http://localhost:8080 ee.ria.siva.perftest.simulations.HashcodeSimulation
```

Load simulation scenarios are run in 6 steps, each step taking `60` seconds and executing a certain amount of RPS (requests per second):

| Step # | RPS | Duration |
| ------ | --- | -------- |
| `1`    | 5   | 60       |
| `2`    | 10  | 60       |
| `3`    | 20  | 60       |
| `4`    | 30  | 60       |
| `5`    | 40  | 60       |
| `6`    | 50  | 60       |

A total of 9300 requests are made in the span of 6 minutes for each scenario in a simulation.

## Generated reports

[Gatling reports](https://gatling.io/docs/gatling/reference/current/stats/reports/) are generated in `target/gatling`
folder and can be accessed by opening in runs folder `index.html` in browser.

Use `./mvnw clean` to clear previous test results.
