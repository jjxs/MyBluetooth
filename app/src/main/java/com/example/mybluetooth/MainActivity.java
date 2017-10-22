package com.example.mybluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import utils.BlueUtils;

import static com.example.mybluetooth.R.id.btn_goto_setting;

public class MainActivity extends BasePrintActivity implements View.OnClickListener{

    Button setting;
    int mSelectedPosition = -1;
    private ListView listView_blue;
    private DeviceListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addBuleSheBei();
    }

    private void addBuleSheBei() {
        List<BluetoothDevice> pairedDevices = BlueUtils.getPairedDevices();
        myAdapter.clear();
        myAdapter.addAll(pairedDevices);
        resetTextName(pairedDevices);
    }

    private void resetTextName(List<BluetoothDevice> pairedDevices) {
        if (pairedDevices.size()>0){
            setting.setText("配对更多设备");
        }else{
            setting.setText("还未配对打印机，去设置");
        }
    }

    private void initView() {
        setting = (Button) findViewById(btn_goto_setting);
        listView_blue = (ListView) findViewById(R.id.listView_blue);
        listView_blue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectedPosition=position;
                myAdapter.notifyDataSetChanged();
            }
        });
        myAdapter = new DeviceListAdapter(this);
        listView_blue.setAdapter(myAdapter);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case btn_goto_setting:
                startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                break;
        }
    }

    class DeviceListAdapter extends ArrayAdapter<BluetoothDevice>{

        public DeviceListAdapter(@NonNull Context context) {
            super(context,0);
        }



        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            BluetoothDevice divices = getItem(position);
            if (convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_bluetooth_device, parent, false);
            }
            TextView tv_device_name = (TextView) convertView.findViewById(R.id.tv_device_name);
            CheckBox cbDevice = (CheckBox) convertView.findViewById(R.id.cb_device);
            tv_device_name.setText(divices.getName());
            cbDevice.setChecked(position==mSelectedPosition);
            return convertView;

        }
    }
}
