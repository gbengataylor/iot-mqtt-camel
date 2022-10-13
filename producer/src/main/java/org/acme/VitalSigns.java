package org.acme;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@RegisterForReflection
@Named("vitalsigns")
public class VitalSigns {

    @Override
    public String toString() {
        return "VitalSigns [bpm=" + bpm + "]";
    }

    public int bpm;
    
    public VitalSigns generate() {
     Random random = new Random();
       VitalSigns vitalSigns = new VitalSigns();
       vitalSigns.bpm = random.nextInt(180 - 50) + 50;
       return vitalSigns;
    } 

}