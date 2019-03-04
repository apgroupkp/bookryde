package com.example.win7.bookryde

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.AsyncTask
import org.json.JSONObject
import java.lang.reflect.Modifier

open class MyApplication : Application() {

  /*  var countryList: List<Country>? = null
    var areaTypeList: List<AreaType>? = null
    var cityList: List<City>? = null
    var cmsList: List<CMSPages>? = null
    var contactno = null;*/


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
       // Utils.setMyLocale(this, if (Utils.getLanguage(this) == "en") "en" else "ar")
    }

    companion object {
        var mInstance: MyApplication? = null
        var isLTR = true
        var msgCount: Int = 0
       // var filter: AdvanceFilter? = null
//        private var filter: AdvanceFilter? = null

        @Synchronized
        fun getInstance(): MyApplication? {
            return mInstance
        }

      /*  fun getAdvanceFilter(): AdvanceFilter {
            return filter!!
        }

        fun setAdvanceFilter(filter: AdvanceFilter?) {
            MyApplication.filter = filter
        }

        fun getGsonInstance(): Gson {
            return GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC).create()
        }*/


    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        /*Stetho.initializeWithDefaults(this)
//        val dbConfiguration = com.activeandroid.Configuration.Builder(this)
//                .setDatabaseName("Ehjezly.db")
//                .addModelClass(Label::class.java)
////                .addModelClass(Country::class.java)
////                .addModelClass(City::class.java)
////                .addModelClass(CMSPages::class.java)
//                .create()
        ActiveAndroid.initialize(this)
//        if (!Utils.getGCMToken(mInstance!!).isEmpty()) {
//
//        }
//        initialization()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, if (Utils.getLanguageDirection(base) == "ltr") "en" else "ar"))
    }

    private fun initialization() {
        val reqParam = hashMapOf(Pair("action", BaseUrl.INITIALIZATION),
            Pair("user_id", Utils.getUserId(mInstance!!)),
            Pair("device_token", Utils.getGCMToken(mInstance!!)),
            Pair("device_type", "and"),
            Pair("imei", getAndroidId(mInstance!!)),
            Pair("lang", Utils.getLanguage(mInstance!!)))

        PHPServices.doPost(mInstance!!, reqParam, object : PHPServices.OnApiResponse<String> {
            override fun onSuccess(response: Response<JsonObject>) {
                val jsonObject = JSONObject(response.body().toString())
                if (BaseUrl.isSuccess(jsonObject)) {

                    val data = jsonObject.optJSONObject("data")

                    val gson = GsonBuilder().setPrettyPrinting().create()

                    countryList = gson.fromJson(data.optString("country"), object : TypeToken<List<Country>>() {}.type)
                    if (countryList!!.isNotEmpty()) {
                        Utils.setCountryList(mInstance!!.applicationContext, countryList!!)
                    }

                    cityList = gson.fromJson(data.optString("area"), object : TypeToken<List<City>>() {}.type)
                    if (cityList!!.isNotEmpty()) {
                        Utils.setCityList(mInstance!!.applicationContext, cityList!!)
                    }

                    areaTypeList = gson.fromJson(data.optString("tableType"), object : TypeToken<List<AreaType>>() {}.type)
                    if (areaTypeList!!.isNotEmpty()) {
                        Utils.setAreaTypeList(mInstance!!.applicationContext, areaTypeList!!)
                    }

                    if (countryList != null && countryList!!.isNotEmpty() || cityList != null && cityList!!.isNotEmpty()) {
                        saveData()
                    }
                    cmsList = gson.fromJson(data.optString("cms"), object : TypeToken<List<CMSPages>>() {}.type)
                    if (cmsList != null && cmsList!!.isNotEmpty()) {
                        saveData()
                    }
                }

            }

            override fun onFailure(exception: Throwable) {
            }

        })*/
    }

    fun saveData() {
        AsyncTask.execute {
            //            Country().saveCountry(mInstance!!, countryList!!)
//
//            PHPServices.printMsg("countryList", Country().getCountryList(mInstance!!).size.toString())
//
//            City().saveCity(mInstance!!, cityList!!)
//
//            PHPServices.printMsg("cityList", City().getCityList(mInstance!!).size.toString())

           /* CMSPages().saveCms(mInstance!!, cmsList!!)

            PHPServices.printMsg("cmsList", CMSPages().getCmsAll(mInstance!!).size.toString())*/
        }
    }
}
