package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ProducerRoute extends RouteBuilder {

    @ConfigProperty(name = "mqtt.comsumer.on", defaultValue = "false")
    protected boolean mqttConsumerOn;

    @Override
    public void configure() throws Exception {
        from("timer:GenerateVitalSigns?period={{message.period}}")
            .bean("vitalsigns" , "generate")
            .marshal().json()
          //  .log("sending ${body} to {{mqtt.producer.url}}")
            .to("paho-mqtt5:{{mqtt.producer.topic}}?brokerUrl={{mqtt.producer.url}}");

        // move consumer to another project -- comment out or set to true intentionally
        if(mqttConsumerOn) {
            from("paho-mqtt5:{{mqtt.producer.topic}}?brokerUrl={{mqtt.producer.url}}")
                .log("received: ${body}");
        }
    }

}