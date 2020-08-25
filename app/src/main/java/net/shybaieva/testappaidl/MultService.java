package net.shybaieva.testappaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MultService extends Service {
    public MultService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    IMultiply.Stub myBinder = new IMultiply.Stub() {
        @Override
        public int multiplyTwoValues(int a, int b) throws RemoteException {
            return  a * b;
        }
    };
}
