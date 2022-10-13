package org.iot;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class MyMQTTRoute extends RouteBuilder {
    private static int count = 0;
    @Override
    public void configure() throws Exception {
        // from("timer:sourceSystemProduceFileSteadily?period=10000")
        //  .setBody(constant("Produced with via the Camel Quarkus FILE extension"))
        //  .log("${body} {{mqtt.url}}");

  

         from("paho-mqtt5:{{mqtt.queue}}?brokerUrl={{mqtt.url}}")
        //    .log("Received message : ${body}"); 
         .aggregate(new AggregationStrategy() {
            @Override
            public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                count++;
                if(oldExchange == null) {
                    return newExchange;
                }
                oldExchange.getIn().setBody(count);
                return oldExchange;
            }           
         }).constant(true)
         .completionInterval(60000) // use this when delay =-1
        // .completionSize("{{producer.max.messages}}") // use this when period is set 
         .log("${body}");
       //.log(Integer.toString(count));
    }
    
}