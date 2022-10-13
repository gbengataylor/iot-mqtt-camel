package org.acme;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@RegisterForReflection
@Named("vitalsigns")
public class VitalSigns {

    @ConfigProperty(name = "vitalsigns.minbpm", defaultValue = "50")
    protected int minBPM;
    @ConfigProperty(name = "vitalsigns.maxbpm", defaultValue = "180")
    protected int maxBPM;
    
    @Override
    public String toString() {
        return "VitalSigns [bpm=" + bpm + "]";
    }

    public int bpm;
    
    public VitalSigns generate() {
     Random random = new Random();
       VitalSigns vitalSigns = new VitalSigns();
       vitalSigns.bpm = random.nextInt(maxBPM - minBPM) + minBPM;
       return vitalSigns;
    } 

}