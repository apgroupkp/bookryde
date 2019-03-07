package com.example.win7.bookryde.User

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.Interface.OnDialog
import com.example.win7.bookryde.MainActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.Utils
import com.example.win7.bookryde.databinding.ActivitRegisterBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class RegisterActivity : BaseActivity(),View.OnClickListener {
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
        }
    fun init() {
        setFullScreen()
        binding.imguser.setOnClickListener(this)
        binding.register.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.imguser -> {
               optionDialog()
            }
            R.id.register->{
                validation()
            }
            else->super.onClick(v)
        }
    }

    private fun optionDialog() {
        val items = arrayOf(getString(R.string.take_photo), getString(R.string.choose_from_gallery), getString(R.string.cancel))
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Photo")
        builder.setItems(items) { dialog, item ->
            //change static string while adding arabic
            if (items[item] == "Take Photo") {
                dialog.dismiss()
                if (Utils.checkPermission(this, Manifest.permission.CAMERA)
                    && Utils.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && Utils.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    cameraIntent()
                } else {
                    ActivityCompat.requestPermissions(this as Activity, arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CAMERA_PERMISION)
                }
            } else if (items[item] == "Choose from Gallery") {
                userChoosenTask = "Choose from Gallery"
                if (Utils.checkPermission(this, Manifest.permission.CAMERA)
                    && Utils.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && Utils.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    opengallery()
                } else {
                    ActivityCompat.requestPermissions(this as Activity, arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_STORAGE_PERMISION)
                }
            } else if (items[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()
    }
    private fun cameraIntent() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_PIC_REQUEST)
    }
    private fun opengallery()
    {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE)
    }
    private fun validation() {
        if (binding.edtfirstName.text.toString().isEmpty()) {
            showSimpleDialog(getString(R.string.please_enter_firstname), object : OnDialog {
                override fun onOk() {

                }
            })

        } else if (binding.edtlastName.text.toString().isEmpty()) {
            showSimpleDialog(getString(R.string.plaese_enter_last_name), object : OnDialog {
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
        }  else if ((binding.edtMobileNo.text.toString().isEmpty())) {

            showSimpleDialog(getString(R.string.mobile_no_digit), object : OnDialog {
                override fun onOk() {
                }
            })

        }else if (binding.edtPassword.text.toString().isEmpty()) {
            showSimpleDialog(getString(R.string.please_enter_password), object : OnDialog {
                override fun onOk() {
                }
            })
        }  else if (binding.edtPassword.text.length<8) {
            showSimpleDialog(getString(R.string.password_validation), object : OnDialog {
                override fun onOk() {
                }
            })

        }else if (binding.edtConfirmPassword.text.toString().isEmpty()) {
            showSimpleDialog(getString(R.string.plaese_enter_confirm_password), object : OnDialog {
                override fun onOk() {
                }
            })
        }
        else if (binding.edtConfirmPassword.text.length<8) {
            showSimpleDialog(getString(R.string.confirm_password_validation), object : OnDialog {
                override fun onOk() {
                }
            })
        } else if (binding.edtConfirmPassword.text.toString() != binding.edtPassword.text.toString()) {

            showSimpleDialog(getString(R.string.confirm_password_same_as_password), object : OnDialog {
                override fun onOk() {
                }
            })
        } else {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                 selectGalleryImage(data!!)
            } else if (requestCode == CAMERA_PIC_REQUEST) {
                captureImageResult(data!!)
            }
        }
    }
    private fun captureImageResult(data: Intent) {
        val img = data.extras!!.get("data") as Bitmap
        val bytes = ByteArrayOutputStream()
        val now = Date()
        val timeStamp = android.text.format.DateFormat.format("yyyy_MM_dd_hh_mm_ss", now)
        val destination = Environment.getExternalStorageDirectory().toString() + "/" + "BookRyde"
        val dir = File(destination)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val file = File(dir, timeStamp.toString() + ".png")
        val fo: FileOutputStream
        try {
            fo = FileOutputStream(file)
            img.compress(Bitmap.CompressFormat.JPEG, 100, fo)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val tempUri = getImageUri(applicationContext, img)
        binding.imguser.setImageURI(tempUri as Uri?)
        binding.imgCamera.visibility = View.VISIBLE


    }
    private fun selectGalleryImage(data: Intent) {
        var bm: Bitmap? = null
        try {
            bm = MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, data.data)

        } catch (e: IOException) {
            e.printStackTrace()
        }
        val tempUri = getImageUri(applicationContext, bm!!)
        binding.imguser.setImageURI(tempUri as Uri?)
        binding.imgCamera.visibility = View.VISIBLE

    }
    private fun getImageUri(inContext: Context?, inImage: Bitmap): Any {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext?.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
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

}
