package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class ProducerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:GenerateVitalSigns?period={{message.period}}")
            .bean("vitalsigns" , "generate")
            .marshal().json()
            .log("${body}")
            .to("paho-mqtt5:{{mqtt.producer.topic}}?brokerURL={{mqtt.producer.url}}");
    }

}