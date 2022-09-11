package com.signature;

public class PC {

    Motherboard motherboard;
    Case theCase;
    Monitor monitor;

    public PC(Motherboard motherboard, Case theCase, Monitor monitor) {
        this.motherboard = motherboard;
        this.theCase = theCase;
        this.monitor = monitor;
    }

    public void powerOn() {
        theCase.pressPowerButton();
        motherboard.loadProgram("jarvis v1.0");
        drawLogo();
    }

    private void drawLogo() {
        monitor.drawPixelAt(1200,50,"blue");
    }

    private Motherboard getMotherboard() {
        return motherboard;
    }

    private Case getTheCase() {
        return theCase;
    }

    private Monitor getMonitor() {
        return monitor;
    }
}
