package com.softagape.sbgradlewebinit.models;

public class Car {
    private Tire tire;

    public Tire getTire() {
        return this.tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public Car(Tire tire) {
        this.tire = tire;
    }
}
