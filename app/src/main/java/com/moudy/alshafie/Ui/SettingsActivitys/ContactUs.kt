package com.moudy.alshafie.Ui.SettingsActivitys

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.moudy.alshafie.R


class ContactUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        initToolbar()
    }

    private fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    } //   @Override
    //    public void onBackPressed() {
    //        new AlertDialog.Builder(this)
    //                .setTitle("Really Exit?")
    //                .setMessage("Are you sure you want to exit?")
    //                .setNegativeButton(android.R.string.no, null)
    //                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
    //
    //                    public void onClick(DialogInterface arg0, int arg1) {
    //                        Contact_Us.super.onBackPressed();
    //                    }
    //                }).create().show();
    //    }
}