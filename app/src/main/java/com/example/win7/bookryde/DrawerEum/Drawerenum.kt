package com.example.win7.bookryde.DrawerEum

import com.example.win7.bookryde.R
import java.io.Serializable

enum class DrawerEnum(var title: Int) :Serializable{
    Home(R.string.home),
    Payment(R.string.payment),
    Wallet(R.string.wallet),
    Notifications(R.string.notification),
    Setting(R.string.setting),
    Support(R.string.support),
    BookingHistrory(R.string.booking_history),
    ReferAFriend(R.string.Refer_a_friend),
    AboutApp(R.string.about_app),
    ContactUs(R.string.contactus),
    TermsAndCondition(R.string.terms_and_condition),
    Logout(R.string.logout);

    /*var value: Int
        get() = title
        set(value) {
            title = value
        }*/

   /* fun getValue(): String {
        return Label().getLabel(title)
    }

    override fun toString(): String {
        return getValue()
    }*/
}