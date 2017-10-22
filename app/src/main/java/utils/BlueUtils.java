package utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Fangzheng on 2017/10/21.
 */

public class BlueUtils {

    //查找所有已配对的设备
    public static List<BluetoothDevice> getPairedDevices(){
        List deviceList= new ArrayList();
        Set<BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                deviceList.add(device);
            }
        }
        return deviceList;
    }
}
