package com.example.dass

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var isReadPermissionGranted = false
    private var isLocationPermissionGranted = false
    private var isRecordPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn= findViewById<Button>(R.id.button4)

        btn.setOnClickListener(){
//            val intent = Intent(this,pa)
            val intent = Intent(this, page1::class.java)
            startActivity(intent)
        }




        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
            { permissions ->
                isReadPermissionGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE]
                    ?: isReadPermissionGranted
                isLocationPermissionGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION]
                    ?: isLocationPermissionGranted
                isRecordPermissionGranted =
                    permissions[Manifest.permission.RECORD_AUDIO] ?: isRecordPermissionGranted


            }

        reqPermit()
//        class LetterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//            val button = view.findViewById<Button>(R.id.button4)
//        }
//      val button4: Button = findViewById(R.id.button)
//        button4.setOnClickListener {
//
//        }
//
// button4.setOnClickListener {
//    val intent = Intent(this@MainActivity, page1::class.java)
//    startActivity(intent)
//
//    }
    }
    private fun reqPermit() {
        isReadPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        isLocationPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        isRecordPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED


//
//    isContactsPermissionGranted = ContextCompat.checkSelfPermission(
//        this,
//        Manifest.permission.WRITE_CONTACTS
//    ) == PackageManager.PERMISSION_GRANTED


        val permissionRequest: MutableList<String> = ArrayList()

        if (!isReadPermissionGranted) {
            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            //  permissionRequest.add(Manifest.permission.READ_CONTACTS)
        }
        if (!isRecordPermissionGranted) {
            permissionRequest.add(Manifest.permission.RECORD_AUDIO)
            //  permissionRequest.add(Manifest.permission.READ_CONTACTS)
        }
        if (!isLocationPermissionGranted) {
            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
            //  permissionRequest.add(Manifest.permission.READ_CONTACTS)
        }





        if (permissionRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionRequest.toTypedArray())
        }
    }
}
