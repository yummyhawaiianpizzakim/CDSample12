package com.example.cdsample12.BlueTooth;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BluetoothGuide {

    public static final int INTENT_REQUEST_BLUETOOTH_ENABLE = 0x0701;

    private final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
    private final List<BluetoothGatt> gattList = new ArrayList<>();
    private final HashMap<String, BluetoothDevice> hashDeviceMap = new HashMap<>();
    private final Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    private boolean scanning = false;


    /**
     * System Bluetooth On Check
     */
    public boolean isOn()
    {
        return adapter.isEnabled();
    }

    /**
     * System Bluetooth On
     */
    public void on(AppCompatActivity activity) {
        if (!adapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(intent, INTENT_REQUEST_BLUETOOTH_ENABLE);
        }
    }

    /**
     * System Bluetooth On Result
     */
    public boolean onActivityResult(int requestCode, int resultCode)
    {
        return requestCode == BluetoothGuide.INTENT_REQUEST_BLUETOOTH_ENABLE
                && Activity.RESULT_OK == resultCode;
    }


    /**
     * System Bluetooth Off
     */
    public void off() {
        if (adapter.isEnabled())
            adapter.disable();
    }

    /**
     * Check model for ScanRecodeData
     */
    public interface OnCheckModelListener {
        boolean isChecked(byte[] bytes);
        void scannedDevice(ScanResult result);
    }
    private OnCheckModelListener onCheckModelListener;
    public BluetoothGuide setOnCheckModelListener(OnCheckModelListener onCheckModelListener) {
        this.onCheckModelListener = onCheckModelListener;
        return this;
    }

    private final ScanCallback callback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);

            Map<ParcelUuid, byte[]> serviceDataMap = result.getScanRecord().getServiceData();
            if( serviceDataMap == null ) return;
            if( onCheckModelListener == null ) return;
            for( ParcelUuid parcelUuid : serviceDataMap.keySet() )
            {
                if( onCheckModelListener.isChecked(result.getScanRecord().getServiceData(parcelUuid)))
                {
                    if( !hasDevice(result.getDevice().toString()))
                    {
                        addDevice(result.getDevice().getAddress(), result.getDevice());
                        if( onCheckModelListener  != null )
                        {
                            onCheckModelListener.scannedDevice(result);
                        }
                    }
                    break;
                }
            }
        }
    };


    /**
     * DO NOT CONNECT DEVICE
     */
    private void addDevice(String address, BluetoothDevice device)
    {
        hashDeviceMap.put(address, device);
    }

    /**
     * DO NOT CONNECT DEVICE
     */
    private boolean hasDevice(String address)
    {
        return hashDeviceMap.get(address) != null;
    }

    /**
     * DO NOT CONNECT DEVICE
     */
    public void onComplete()
    {
        hashDeviceMap.clear();
    }


    /**
     * Start Scanc
     */
    public void scanDevices() {
        if (!adapter.isEnabled()) return;
        if (scanning) return;
        BluetoothLeScanner scanner = adapter.getBluetoothLeScanner();

        mainThreadHandler.postDelayed(() -> {
            scanning = false;
            scanner.stopScan(callback);
        }, 2 * 60 * 1000);

        scanning = true;
        scanner.startScan(callback);
    }

    /**
     * Connecting Device
     */
    public void connGATT(Context context, BluetoothDevice device)
    {
        gattList.add(device.connectGatt(context, false, gattCallback));
    }


    /**
     * Disconnected All Device
     */
    public void disconnectGATTAll()
    {
        for (BluetoothGatt bluetoothGatt : gattList) {
            if( bluetoothGatt == null ) continue;
            bluetoothGatt.disconnect();
            bluetoothGatt.close();
        }
        gattList.clear();
    }


    private final BluetoothGattCallback gattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if( status == BluetoothGatt.GATT_FAILURE ) {
                gatt.disconnect();
                gatt.close();
                hashDeviceMap.remove(gatt.getDevice().getAddress());
                return;
            }
            if( status == 133 ) // Unknown Error
            {
                gatt.disconnect();
                gatt.close();
                hashDeviceMap.remove(gatt.getDevice().getAddress());
                return;
            }
            if( newState == BluetoothGatt.STATE_CONNECTED && status == BluetoothGatt.GATT_SUCCESS)
            {
                // "Connected to " + gatt.getDevice().getName()
                gatt.discoverServices();
            }

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            if( status == BluetoothGatt.GATT_SUCCESS)
            {
                List<BluetoothGattService> services = gatt.getServices();
                for (BluetoothGattService service : services) {
                    // "Found service : " + service.getUuid()
                    for (BluetoothGattCharacteristic characteristic : service.getCharacteristics()) {
                        //"Found characteristic : " + characteristic.getUuid()
                        if( hasProperty(characteristic, BluetoothGattCharacteristic.PROPERTY_READ))
                        {
                            // "Read characteristic : " + characteristic.getUuid());
                            gatt.readCharacteristic(characteristic);
                        }

                        if( hasProperty(characteristic, BluetoothGattCharacteristic.PROPERTY_NOTIFY))
                        {
                            // "Register notification for characteristic : " + characteristic.getUuid());
                            gatt.setCharacteristicNotification(characteristic, true);
                        }
                    }
                }
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            if( status == BluetoothGatt.GATT_SUCCESS) {
                if( onReadValueListener == null ) return;
                // This is Background Thread
                mainThreadHandler.post(
                        () ->onReadValueListener.onValue(gatt.getDevice(), onReadValueListener.formatter(characteristic))
                );
            }
        }


        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            if( onNotifyValueListener == null ) return;
            // This is Background Thread
            mainThreadHandler.post(
                    ()->onNotifyValueListener.onValue(gatt.getDevice(), onNotifyValueListener.formatter(characteristic))
            );
        }
    };

    public boolean hasProperty(BluetoothGattCharacteristic characteristic, int property)
    {
        int prop = characteristic.getProperties() & property;
        return prop == property;
    }


    public interface OnNotifyValueListener <T>{
        void onValue(BluetoothDevice deivce, T value);
        T formatter(BluetoothGattCharacteristic characteristic);
    }

    public interface OnReadValueListener <T>{
        void onValue(BluetoothDevice deivce, T value);
        T formatter(BluetoothGattCharacteristic characteristic);
    }

    private OnNotifyValueListener onNotifyValueListener = null;
    public BluetoothGuide setOnNotifyValueListener(OnNotifyValueListener onNotifyValueListener) {
        this.onNotifyValueListener = onNotifyValueListener;
        return this;
    }


    private OnReadValueListener onReadValueListener = null;
    public BluetoothGuide setOnReadValueListener(OnReadValueListener onReadValueListener) {
        this.onReadValueListener = onReadValueListener;
        return this;
    }
}