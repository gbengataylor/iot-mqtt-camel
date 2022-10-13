package org.acme;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class ProducerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:GenerateVitalSigns?period=1000")
            .bean("vitalsigns" , "generate")
            .marshal().json()
            .log("${body}");    
    }

}