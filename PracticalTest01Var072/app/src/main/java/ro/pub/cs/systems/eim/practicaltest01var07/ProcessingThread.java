package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by student on 04.04.2017.
 */
public class ProcessingThread extends  Thread{

    private Context context = null;
    private boolean isRunning = true;
    private int sum = 0;


    public ProcessingThread(Context context, int sum) {
        this.context = context;

    this.sum = sum;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("SEND");
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + sum );
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
