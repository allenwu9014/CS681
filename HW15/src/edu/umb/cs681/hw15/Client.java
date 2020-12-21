package edu.umb.cs681.hw15;

public class Client {
    public static void main(String[] args) {
        AdmissionControl admissionControl = new AdmissionControl();
        EntranceHandler entranceHandler = new EntranceHandler(admissionControl);
        ExitHandler exitHandler = new ExitHandler(admissionControl);
        MonitorHandler monitorHandler = new MonitorHandler(admissionControl);

        Thread entranceThread = new Thread(entranceHandler);
        entranceThread.start();
        Thread exitThread = new Thread(exitHandler);
        exitThread.start();
        Thread monitorThread = new Thread(monitorHandler);
        monitorThread.start();

        try{
            Thread.sleep(2000);
        } catch (Exception e) {
           e.printStackTrace();
        }

        entranceHandler.setDone();
        exitHandler.setDone();
       monitorHandler.setDone();
        entranceThread.interrupt();
        exitThread.interrupt();
        monitorThread.interrupt();
        try {
            entranceThread.join();
            exitThread.join();
            monitorThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

