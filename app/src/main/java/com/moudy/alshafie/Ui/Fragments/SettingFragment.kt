package com.moudy.alshafie.Ui.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.moudy.alshafie.DataBase.UserDatabaseHelper
import com.moudy.alshafie.DataBase.UserDatabaseHelper.Companion.COL_ID
import com.moudy.alshafie.DataBase.UserDatabaseHelper.Companion.COL_USERPHOTO
import com.moudy.alshafie.DataBase.UserDatabaseHelper.Companion.TABLE_NAME
import com.moudy.alshafie.MainActivity
import com.moudy.alshafie.R
import com.moudy.alshafie.Ui.SettingsActivitys.*
import com.moudy.alshafie.databinding.FragmentFavoriteBinding
import com.moudy.alshafie.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var myDbHelper: UserDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        context?.let {
            myDbHelper = UserDatabaseHelper(it)
        }
        var username = ""
        val cursor = myDbHelper.readableDatabase.rawQuery("SELECT username FROM users", null)
        if (cursor != null && cursor.moveToFirst()) {
            username = cursor.getString(0)
            cursor.close()
        }
        binding.usernametv.text = username
        onclicklay()
        binding.contactlay.setOnClickListener(){
            startActivity(Intent(requireContext(), ContactUs::class.java))

        }
        binding.darkmodeswitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Enable dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // Disable dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.helplay.setOnClickListener(){
            startActivity(Intent(requireContext(), Help::class.java))

        }
        binding.langlay.setOnClickListener(){
            startActivity(Intent(requireContext(), ContactUs::class.java))

        }
        binding.privacylay.setOnClickListener(){
            startActivity(Intent(requireContext(), PrivacyPolicy::class.java))

        }
        binding.termlay.setOnClickListener(){
            startActivity(Intent(requireContext(), TermsofService::class.java))

        }

        binding.ratelay.setOnClickListener(){
            startActivity(Intent(requireContext(), RateUs::class.java))

        }
        return binding.root

    }

    @SuppressLint("Range")
    private fun onclicklay() {

        val db = myDbHelper.readableDatabase

//Select query to retrieve the photo of the user with the given userId
        val selectQuery = "SELECT $COL_USERPHOTO FROM $TABLE_NAME "

//Execute the query and retrieve the result cursor
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            //Retrieve the photo bytes from the cursor
            val photoBytes = cursor.getBlob(cursor.getColumnIndex(COL_USERPHOTO))

            //Convert the photo bytes to a bitmap
            val photoBitmap = BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.size)

            //Set the photo bitmap as the profile photo
            binding.setprofilePhoto.setImageBitmap(photoBitmap)
        }

//Close the cursor and the database
        cursor.close()
        db.close()

    }

}

