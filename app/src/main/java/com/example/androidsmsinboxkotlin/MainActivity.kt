package com.example.androidsmsinboxkotlin

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Telephony
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val array = arrayOf(Telephony.Sms._ID, Telephony.Sms.BODY, Telephony.Sms.ADDRESS)
    val list = ArrayList<SMS>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkSelfPermission(android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(android.Manifest.permission.READ_SMS), 100)
        }else{
            getMessage()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissions[0] == android.Manifest.permission.READ_SMS &&
            requestCode == 100 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getMessage()
        }
    }

    fun getMessage(){
        val uri = Uri.parse("content://sms/inbox")
        val cursor = contentResolver.query(uri, array, null, null, null)
        cursor.moveToFirst()
        val id = cursor.getColumnIndex(Telephony.Sms._ID)
        val body = cursor.getColumnIndex(Telephony.Sms.BODY)
        val address = cursor.getColumnIndex(Telephony.Sms.ADDRESS)
        do {
            list.add(SMS(cursor.getLong(id), cursor.getString(body), cursor.getString(address)))
        } while (cursor.moveToNext())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListAdapter(list)
    }
}
