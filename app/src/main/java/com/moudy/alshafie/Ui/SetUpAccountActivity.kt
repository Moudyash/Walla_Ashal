package com.moudy.alshafie.Ui

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color.red
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.moudy.alshafie.DataBase.UserDatabaseHelper
import com.moudy.alshafie.R
import com.moudy.alshafie.databinding.ActivitySetUpAccountBinding
import java.io.ByteArrayOutputStream

class SetUpAccountActivity : AppCompatActivity() {

     private lateinit var binding:ActivitySetUpAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetUpAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PackageManager.PERMISSION_GRANTED)
        val dbHelper = UserDatabaseHelper(this)
        val db = dbHelper.writableDatabase
        binding.finish.setOnClickListener(){
            val username =binding.usernameed.text.toString()
            if (binding.usernameed.text.toString().trim().isEmpty()) {
                binding.usernamelay.error = "This field cannot be empty"
                binding.usernamelay.requestFocus()
            } else {
                val username = binding.usernameed.text.toString()
                // Get the Bitmap from the CircleImageView
                val bitmap = (binding.profilePhoto.drawable as BitmapDrawable).bitmap

                // Create a ByteArrayOutputStream to write the Bitmap to
                val stream = ByteArrayOutputStream()

                // Compress the Bitmap to PNG format and write it to the ByteArrayOutputStream
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

                // Get the byte array from the ByteArrayOutputStream
                val userPhotoBytes = stream.toByteArray()

                // Close the ByteArrayOutputStream to release its resources
                stream.close()

                val values = ContentValues().apply {
                    put(UserDatabaseHelper.COL_USERNAME, username)
                    put(UserDatabaseHelper.COL_USERPHOTO, userPhotoBytes)
                }

                db.insert(UserDatabaseHelper.TABLE_NAME, null, values)
                startActivity(Intent(this@SetUpAccountActivity, WelcomeActivity::class.java).putExtra("username", username))
            }

        }

        binding.editProfileImage.setOnClickListener(){

            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)


        }

        binding.selectPhoto.setOnClickListener(){

            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)


        }
        binding.girl.setOnClickListener(){

            binding.profilePhoto.setImageDrawable(getDrawable(R.drawable.girl))
        }
        binding.boy.setOnClickListener(){
            binding.profilePhoto.setImageDrawable(getDrawable(R.drawable.boy))
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageuri = data.data
            binding.profilePhoto.setImageURI(imageuri)
        }
    }

}