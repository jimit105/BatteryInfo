package com.jimit105.batteryinfo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void displayBatteryDetails(View view) {

        TextView details = (TextView)findViewById(R.id.batteryTextView);
        String info = "";

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);


        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, 0);

        info += "Status: ";
        switch (status){
            case BatteryManager.BATTERY_STATUS_CHARGING:
                info += "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                info += "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                info += "Full";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                info += "Not Charging";
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                info += "Unknown";
                break;
            default:
                break;
        }


        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        info += "\nLevel: " + level +"%";

        info += "\nScale: " + batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) +"%";


        int source = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        info += "\nSource: ";
        switch (source){
            case BatteryManager.BATTERY_PLUGGED_AC:
                info += "AC";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                info += "USB";
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                info += "Wireless";
                break;
            default:
                info += "Unplugged";
                break;
        }


        int health = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
        info += "\nHealth: ";
        switch (health){
            case BatteryManager.BATTERY_HEALTH_COLD:
                info += "Cold";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                info += "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                info += "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                info += "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                info += "Overheated";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                info += "Failed";
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                info += "Unknown";
                break;
            default:
                break;
        }


        info += "\nVoltage: " + (float)batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0) / 1000 + " V";


        info += "\nTemperature: " + (float)batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10 + "\u00b0 C" ;


        info += "\nTechnology: " + batteryStatus.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);


        Toast.makeText(this, "Developed by Jimit Dholakia", Toast.LENGTH_SHORT).show();

        details.setText(info);


    }
}
