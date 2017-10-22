package com.example.mybluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BasePrintActivity extends AppCompatActivity {

    private BluetoothStateReceiver mBluetoothStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReceiver();
    }

    /**
    * 蓝牙状态回调
    * */
    public void onBluetoothStateChanged(Intent intent) {

    }

    private void initReceiver() {

         mBluetoothStateReceiver = new BluetoothStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBluetoothStateReceiver,filter);
    }

    class BluetoothStateReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
            switch (state){
                case BluetoothAdapter.STATE_TURNING_ON:
                    toast("蓝牙已打开");
                    break;

                case BluetoothAdapter.STATE_TURNING_OFF:
                    toast("蓝牙已关闭");
                    break;
            }
            //onBluetoothStateChanged(intent);

        }
    }


    private void toast(String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBluetoothStateReceiver);
    }
}
