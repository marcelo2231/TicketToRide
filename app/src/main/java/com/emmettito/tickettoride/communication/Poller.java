package com.emmettito.tickettoride.communication;

import com.emmettito.tickettoride.Client;

import java.util.Observable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Poller extends Observable {
    private Thread pollerThread;
    private CountDownLatch threadDoneSignal;
    private String url;
    private String requestBody;
    private ClientCommunicator client;
    private Client clientInstance;
    private String response;

    public Poller(String url) {
        this.url = url;
        this.requestBody = null;
    }

    public Poller(String url, String requestBody) {
        this.url = url;
        this.requestBody = requestBody;
    }

    private void setUpPoller(int initialDelaySec, int delaySec, boolean fixedRate) {
        boolean first = true;
        long sleepTime = delaySec * 1000L;
        while (true) {
            try {
                if (first) {
                    if (initialDelaySec > 0) {
                        Thread.sleep(initialDelaySec * 1000L);
                    }
                    first = false;
                }
                else if (sleepTime > 0)
                    Thread.sleep(sleepTime);

                long startMillis = System.currentTimeMillis();
                if (requestBody != null) {
                    poll(requestBody);
                }
                else {
                    poll();
                }
                sleepTime = fixedRate ? delaySec * 1000L - (System.currentTimeMillis() - startMillis) : delaySec * 1000L;
            }
            catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        threadDoneSignal.countDown();
    }

    private void poll() throws Exception {
        client = new ClientCommunicator();
        clientInstance = Client.getInstance();
        response = client.doInBackground(url, "GET", "");
        setChanged();
        notifyObservers(response);
    }

    private void poll(String requestBody) throws Exception {
        client = new ClientCommunicator();
        clientInstance = Client.getInstance();
        response = client.doInBackground(url, "POST", requestBody);
        setChanged();
        notifyObservers(response);
    }

    private String getThreadName() {
        return getClass().getName();
    }

    public void start() {
        start(0, 3600, false);
    }

    public void start(final int delaySec) {
        start(0, delaySec, false);
    }

    public void start(final int initialDelaySec, final int delaySec, final boolean fixedRate) {
        threadDoneSignal = new CountDownLatch(1);
        pollerThread = new Thread(new Runnable() {
            public  void run() {
                setUpPoller(initialDelaySec, delaySec, fixedRate);
            }
        }, getThreadName());
        pollerThread.start();
    }

    public void shutdown() {
        boolean done = false;
        int numTries = 0;
        try {
            while (!done) {
                pollerThread.interrupt();
                done = threadDoneSignal.await(10, TimeUnit.SECONDS);

                if (!done) {
                    numTries = numTries + 1;
                }
            }
        }
        catch (InterruptedException e) {
                // ignore InterruptedException here
        }
    }

    public String getResponse() {
        return response;
    }
}
