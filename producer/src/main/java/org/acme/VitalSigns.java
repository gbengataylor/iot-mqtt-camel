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

    protected int bpm;
    protected Random random = new Random();

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }
    public VitalSigns generate() {
       VitalSigns vitalSigns = new VitalSigns();
       vitalSigns.bpm = random.nextInt(maxBPM - minBPM) + minBPM;
       return vitalSigns;
    } 

    @Override
    public String toString() {
        return "VitalSigns [bpm=" + bpm + "]";
    }
}