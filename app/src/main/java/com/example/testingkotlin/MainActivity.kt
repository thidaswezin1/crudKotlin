package com.example.testingkotlin

import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.content.DialogInterface
import android.content.Intent


class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var btnTest: Button
    private lateinit var btnTest1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnTest = findViewById(R.id.button)
        btnTest.setOnClickListener(this)
        btnTest1 = findViewById<Button>(R.id.button2)
        btnTest1.setOnClickListener(this)


    }
    override fun onClick(view: View){
        if(view==btnTest) {

            val alertDialog = AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle("Are you sure to Exit")
                //set message
                .setMessage("If yes then application will close")

                .setNeutralButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                    //set what should happen when negative button is clicked
                    Toast.makeText(applicationContext, "Hello Kotlin", Toast.LENGTH_LONG).show()
                })
                .show()
        }
        else if(view==btnTest1){
            //Toast.makeText(applicationContext, "Welcome to Kotlin", Toast.LENGTH_LONG).show()
            val intent = Intent(this,NextActivity::class.java)
            startActivity(intent)

        }
    }
}