package com.example.win7.bookryde

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.win7.bookryde.Drawer.AboutAppActivity
import com.example.win7.bookryde.Drawer.BookingList.BookingListActivity
import com.example.win7.bookryde.Drawer.ReferAFriendActivity
import com.example.win7.bookryde.Drawer.SettingActivity
import com.example.win7.bookryde.Drawer.TermsConditionActivity
import com.example.win7.bookryde.DrawerEum.DrawerEnum
import com.example.win7.bookryde.Interface.OnConfirmation
import com.example.win7.bookryde.Model.DrawerItem
import com.example.win7.bookryde.Notification.NotificationListActivity
import com.example.win7.bookryde.User.LoginActivity
import com.example.win7.bookryde.databinding.ActivityBaseBinding
import com.example.win7.bookryde.databinding.RawDrawerItemBinding
import java.util.ArrayList

open class BaseActivity : AbstactBaseActivity(), View.OnClickListener, DrawerLayout.DrawerListener {

    lateinit var baseBinding: ActivityBaseBinding
    private val drawerItemArrayList: ArrayList<DrawerItem> = arrayListOf()
    lateinit var adapter: DrawerRecyclerAdapter
    lateinit var me: BaseActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        init()
    }

    private fun init() {
        me = this
        setToolbar(baseBinding.mToolbar)

    }
    private fun setToolbar(mToolbar: Toolbar) {
        try {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
            window.setWindowAnimations(0)
            setSupportActionBar(mToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white)
            baseBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun setBackButton(boolean: Boolean):Boolean{
        if(boolean){
            baseBinding.imgBack.visibility=View.VISIBLE
        }
        else{
            baseBinding.imgBack.visibility=View.GONE
        }
     return boolean
    }
    fun setNotificationButton(boolean: Boolean):Boolean{
        if(boolean){
            baseBinding.imgNotification.visibility=View.VISIBLE
        }
        else{
            baseBinding.imgNotification.visibility=View.GONE
        }
        return boolean
    }
    protected fun setNavigationView() {
        val drawer = baseBinding.drawerLayout
        drawer.addDrawerListener(this)
        val toggle = ActionBarDrawerToggle(this, drawer, baseBinding.mToolbar, 0, 0)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_drawer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        baseBinding.drawerLayout.setScrimColor(ContextCompat.getColor(me, android.R.color.transparent))
        baseBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        setDrawerData()
    }

    override fun onDrawerStateChanged(newState: Int) {
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        /* if (slideOffset <= .10 && intent1 != null) {
             if (intent1!!.hasExtra("changeLanguage")) {

             }
         }*/

    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerOpened(drawerView: View) {
    }
    fun setFullScreen() {
        try {
            baseBinding.mToolbar.visibility=View.GONE
            supportActionBar?.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    protected fun setToolbarTitle(title: String) {
        baseBinding.toolbarTitle.text = title
    }

    override fun onClick(v: View?) {
       /* when(v?.id) {
            R.id.imgNotification -> {
                startActivity(Intent(me, LoginActivity::class.java))
            }
        }*/
    }

    private fun closeDrawer() {
        baseBinding.drawerLayout.closeDrawers()
    }
    override fun onBackPressed() {
        Utils.hideSoftKeyboard(me)
        super.onBackPressed()
    }
    private fun setDrawerData() {

        drawerItemArrayList.clear()
        var item = DrawerItem()
       // if (!Utils.getUserId(me).isEmpty()) {
            item.drawerEnum = DrawerEnum.Home
        item.itemName=getString(R.string.home)
            item.itemImage=getDrawable(R.drawable.ic_home)
            drawerItemArrayList.add(item)
      /*  } else {
            item = DrawerItem()
            item.drawerEnum = DrawerEnum.Login
            drawerItemArrayList.add(item)
        }*/
        item = DrawerItem()
        item.drawerEnum = DrawerEnum.Payment
        item.itemName=getString(R.string.payment)
        item.itemImage=getDrawable(R.drawable.ic_payment)
        drawerItemArrayList.add(item)

        item = DrawerItem()
        item.drawerEnum = DrawerEnum.Notifications
        item.itemName=getString(R.string.notification)
        item.itemImage=getDrawable(R.drawable.ic_notification_black)
        drawerItemArrayList.add(item)

        item = DrawerItem()
        item.drawerEnum = DrawerEnum.Setting
        item.itemName=getString(R.string.setting)
        item.itemImage=getDrawable(R.drawable.ic_setting)
        drawerItemArrayList.add(item)

        item = DrawerItem()
        item.drawerEnum = DrawerEnum.Support
        item.itemName=getString(R.string.support)
        item.itemImage=getDrawable(R.drawable.ic_support)
        drawerItemArrayList.add(item)

        item = DrawerItem()
        item.drawerEnum = DrawerEnum.BookingHistrory
        item.itemName=getString(R.string.booking_history)
        item.itemImage=getDrawable(R.drawable.history)
        drawerItemArrayList.add(item)
        item = DrawerItem()
        item.drawerEnum = DrawerEnum.ReferAFriend
        item.itemName=getString(R.string.Refer_a_friend)
        item.itemImage=getDrawable(R.drawable.ic_share_black)
        drawerItemArrayList.add(item)

        item = DrawerItem()
        item.drawerEnum = DrawerEnum.AboutApp
        item.itemName=getString(R.string.about_app)
        item.itemImage=getDrawable(R.drawable.ic_aboutapp)
        drawerItemArrayList.add(item)

        item = DrawerItem()
        item.drawerEnum = DrawerEnum.TermsAndCondition
        item.itemName=getString(R.string.terms_and_condition)
        item.itemImage=getDrawable(R.drawable.ic_term_condition)
        drawerItemArrayList.add(item)

        item = DrawerItem()
        item.drawerEnum = DrawerEnum.Logout
        item.itemName=getString(R.string.logout)
        item.itemImage=getDrawable(R.drawable.ic_logout)
        drawerItemArrayList.add(item)

        adapter = DrawerRecyclerAdapter()
        val mLayoutManager = LinearLayoutManager(this)
        baseBinding.drawerRecycler.layoutManager = mLayoutManager
        baseBinding.drawerRecycler.adapter = adapter

    }
    inner class DrawerRecyclerAdapter : RecyclerView.Adapter<DrawerRecyclerAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val rawDrawerItemBinding = RawDrawerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(rawDrawerItemBinding)
        }

        override fun getItemCount(): Int {
            return drawerItemArrayList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data: DrawerItem = drawerItemArrayList[position]
            holder.bind(data)
            holder.rawDrawerItemBinding.executePendingBindings()
            holder.rawDrawerItemBinding.root.setOnClickListener {
                when (data.drawerEnum) {
                    DrawerEnum.Home -> {
                        drawerItemArrayList[position].isChecked = !drawerItemArrayList[position].isChecked
                        notifyDataSetChanged()
                        holder.rawDrawerItemBinding.executePendingBindings()
                        startActivity(Intent(me, MainActivity::class.java))
                    }
                    DrawerEnum.Payment -> {
                      //  startActivity(Intent(me, LoginActivity::class.java))
                        closeDrawer()
                    }
                    DrawerEnum.Wallet -> {
                     //   startActivity(Intent(me, TermsConditionsActivity::class.java))
                        closeDrawer()
                    }
                    DrawerEnum.Notifications -> {
                        startActivity(Intent(me, NotificationListActivity::class.java))
                        /*if (Utils.getNotificationStatus() == "") {
                            Utils.setNotificationStatus("0")
                            val status = Utils.getNotificationStatus()
                            setNotificationOnOff(status.toInt())
                        } else {
                            val status = Utils.getNotificationStatus()
                            setNotificationOnOff(status.toInt())
                        }*/

                        closeDrawer()
                    }
                    DrawerEnum.Setting -> {
                        startActivity(Intent(me, SettingActivity::class.java))
                        closeDrawer()
                    }
                    DrawerEnum.Support -> {
                        //startActivity(Intent(me, AboutAppActivity::class.java))
                        closeDrawer()
                    }
                    DrawerEnum.BookingHistrory -> {
                        startActivity(Intent(me, BookingListActivity::class.java))
                        closeDrawer()
                    }
                    DrawerEnum.ReferAFriend -> {
                        startActivity(Intent(me, ReferAFriendActivity::class.java))
                        closeDrawer()
                    }
                    DrawerEnum.AboutApp -> {
                        startActivity(Intent(me, AboutAppActivity::class.java))
                        closeDrawer()
                    }

                    DrawerEnum.TermsAndCondition -> {
                        startActivity(Intent(me, TermsConditionActivity::class.java))
                       // Utils.makeCall(me, Utils.getContactNo(me))
                        closeDrawer()
                    }
                    DrawerEnum.Logout -> {
                        showConfirmationDialog(getString(R.string.are_you_sure_want_to_logout), object :
                            OnConfirmation {
                            override fun onYes() {
                               // logOut()
                                closeDrawer()
                            }

                            override fun onNo() {
                                closeDrawer()
                            }

                        })


                    }
                    else -> {
                    }
                }
            }
            if (drawerItemArrayList[position].isChecked) {
               /* if (!Utils.getUserId(me).isEmpty()) {
                    holder.rawDrawerItemBinding.linChild.tv_logout.visibility = View.VISIBLE
                    holder.rawDrawerItemBinding.linChild.view.visibility = View.VISIBLE
                }*/
            }
           /* holder.rawDrawerItemBinding.linChild.tv_logout.setOnClickListener {
                closeDrawer()
                showConfirmationDialog(Label().getLabel(R.string.are_you_sure_want_to_logout), object : OnConfirmation {
                    override fun onYes() {
                        logOut()
                    }

                    override fun onNo() {

                    }

                })
            }*/


           /* holder.rawDrawerItemBinding.linChild.tv_my_profile.setOnClickListener {
                startActivity(Intent(me, MyProfileActivity::class.java))
                drawerItemArrayList[position].isChecked = !drawerItemArrayList[position].isChecked
                notifyDataSetChanged()
                holder.rawDrawerItemBinding.executePendingBindings()
                closeDrawer()
            }
            holder.rawDrawerItemBinding.linChild.tv_change_password.setOnClickListener {
                startActivity(Intent(me, ChangePasswordActivity::class.java))
                drawerItemArrayList[position].isChecked = !drawerItemArrayList[position].isChecked
                notifyDataSetChanged()
                holder.rawDrawerItemBinding.executePendingBindings()
                closeDrawer()
            }
            holder.rawDrawerItemBinding.linChild.tv_booking_history.setOnClickListener {
                startActivity(Intent(me, BookingHistoryListActivity::class.java))
                drawerItemArrayList[position].isChecked = !drawerItemArrayList[position].isChecked
                notifyDataSetChanged()
                holder.rawDrawerItemBinding.executePendingBindings()
                closeDrawer()
            }
            holder.rawDrawerItemBinding.linChild.tv_my_fav.setOnClickListener {
                startActivity(Intent(me, MyFavouriteActivity::class.java))
                drawerItemArrayList[position].isChecked = !drawerItemArrayList[position].isChecked
                notifyDataSetChanged()
                holder.rawDrawerItemBinding.executePendingBindings()
                closeDrawer()
            }
            holder.rawDrawerItemBinding.tglBtnNotification.setOnClickListener {
                //   setNotificationOnOff(if (holder.rawDrawerItemBinding.tglBtnNotification.isChecked) 1 else 0)

                if (Utils.getNotificationStatus() == "1") {
                    !holder.rawDrawerItemBinding.tglBtnNotification.isChecked
                    Utils.setNotificationStatus("0")
                } else {
                    Utils.setNotificationStatus("1")
                    holder.rawDrawerItemBinding.tglBtnNotification.isChecked
                }

                closeDrawer()
            }*/

        }

        inner class ViewHolder(val rawDrawerItemBinding: RawDrawerItemBinding) : RecyclerView.ViewHolder(rawDrawerItemBinding.root) {

            fun bind(item: DrawerItem) {
                rawDrawerItemBinding.imgItem.setImageDrawable(item.itemImage)
                rawDrawerItemBinding.txtdrweritem.text = item.itemName
                binding.executePendingBindings()
            }
        }
    }
}

