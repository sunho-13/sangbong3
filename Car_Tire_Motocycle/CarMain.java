package com.softagape.sbgradlewebinit.models;

public class CarMain {
    public static void main(String[] args) {
        Car car1 = new Car(new Tire());
        car1.getTire().roll();

//        Car car2 = new Car(new KTier());
//        car2.getTire().roll();
//
//        Car car3 = new Car(new KTier());
//        car3.getTire().roll();

        car1.setTire(new KTire());
        car1.getTire().roll();

        car1.setTire(new HTire());
        car1.getTire().roll();

        Motocycle motocycle = new Motocycle();
        motocycle.run(new Tire());
        motocycle.run(new KTire());
        motocycle.run(new HTire());
        motocycle.check(car1);
        motocycle.check(new HTire());
    }
}
