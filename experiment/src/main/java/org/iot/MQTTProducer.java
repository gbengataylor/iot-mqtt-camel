package org.iot;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.paho.PahoConstants;

public class MQTTProducer extends RouteBuilder{
    private  int count = 0;
    @Override
    public void configure() throws Exception {
        // TODO Auto-generated method stub
       // from("timer:SendMQTT?repeatCount={{producer.max.messages}}&period={{producer.send.milliseconds}}") // send X messages every 1 millisec 
        from("timer:SendMQTT?repeatCount={{producer.max.messages}}&delay=-1") // send X messagesas soon as possible -> some messages get lost
        .setHeader(PahoConstants.CAMEL_PAHO_OVERRIDE_TOPIC, simple(Integer.toString(++count)))
        .setBody(simple(Integer.toString(count)))
            .to("paho-mqtt5:{{mqtt.queue}}?brokerUrl={{mqtt.url}}");  // paho v5
         //   .to("paho:{{mqtt.queue}}?brokerUrl={{mqtt.url}}"); //paho mqtt

        //.log("${body} ${header." + PahoConstants.CAMEL_PAHO_OVERRIDE_TOPIC + "}");
    }

}
