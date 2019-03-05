package com.example.win7.bookryde.User

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.Interface.OnDialog
import com.example.win7.bookryde.MainActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.Utils
import com.example.win7.bookryde.databinding.ActivitRegisterBinding

class RegisterActivity : BaseActivity() {
    lateinit var binding: ActivitRegisterBinding
    private var userChoosenTask :String?=null
    private val REQUEST_CAMERA_PERMISION = 100
    private val REQUEST_STORAGE_PERMISION = 200
    private val CAMERA_PIC_REQUEST = 123
    private val SELECT_FILE = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitRegisterBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        init()
        binding.register.setOnClickListener {

            validation()

        }

        binding.imgCamera.setOnClickListener {
            val items = arrayOf<CharSequence>(
                getString(R.string.take_photo),
                getString(R.string.choose_from_gallery),
                getString(R.string.cancel)
            )
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add Photo")
            builder.setItems(items) { dialog, item ->
                if (items[item] == "Take Photo") {
                if (Utils.checkPermission(this, Manifest.permission.CAMERA)
                    && Utils.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && Utils.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    cameraIntent()
                } else {
                    ActivityCompat.requestPermissions(this as Activity, arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_CAMERA_PERMISION) }
                }
            else if (items[item] == "Choose from Gallery") {
                    userChoosenTask = "Choose from Gallery"
                    dialog.dismiss()
                    if (Utils.checkPermission(this, Manifest.permission.CAMERA)
                        && Utils.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && Utils.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        opengallery()
                    } else {
                        ActivityCompat.requestPermissions(this as Activity, arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_STORAGE_PERMISION)
                    }
                } else if (items[item] == "Cancel") {
                    dialog.dismiss()
                } }
            builder.show()
        }
        }

    private fun opengallery()
    {
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
               // SelectGalleryImage(data!!)
            } else if (requestCode == CAMERA_PIC_REQUEST) {
                //CaptureImageResult(data!!)
            }
        }
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA_PERMISION -> if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cameraIntent()
                } else {
                    Toast.makeText(this, getString(R.string.camera_permission_error), Toast.LENGTH_SHORT).show()
                }
            }
            REQUEST_STORAGE_PERMISION -> if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                      opengallery()

                } else {
                    Toast.makeText(this, getString(R.string.gallery_permission_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun cameraIntent() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_PIC_REQUEST)
    }

    private fun validation() {
        if (binding.edtfirstName.text.toString().isEmpty()) {
            showSimpleDialog(getString(R.string.register_username_length), object : OnDialog {
                override fun onOk() {

                }
            })

        } else if (binding.edtlastName.text.toString().isEmpty()) {
            showSimpleDialog(getString(R.string.register_username_length), object : OnDialog {
                override fun onOk() {
                }
            })

        } else if (binding.edtEmail.text.toString().isEmpty()) {

            showSimpleDialog(getString(R.string.email_validation), object : OnDialog {
                override fun onOk() {
                }
            })

        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {

            showSimpleDialog(getString(R.string.email_pattren), object : OnDialog {
                override fun onOk() {
                }
            })
        } else if (binding.edtPassword.text.toString().isEmpty()) {
            showSimpleDialog(getString(R.string.password_validation), object : OnDialog {
                override fun onOk() {
                }
            })
        } else if (binding.edtConfirmPassword.text.toString() != binding.edtPassword.text.toString()) {

            showSimpleDialog(getString(R.string.confirm_password_same_as_password), object : OnDialog {
                override fun onOk() {
                }
            })
        } else if ((binding.edtMobileNo.text.toString().isEmpty())) {

            showSimpleDialog(getString(R.string.mobile_no_digit), object : OnDialog {
                override fun onOk() {
                }
            })

        } else {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    fun init() {
        setFullScreen()


    }
}
