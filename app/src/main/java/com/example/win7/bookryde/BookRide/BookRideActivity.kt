package com.example.win7.bookryde.BookRide

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.win7.bookryde.BaseActivity
import com.example.win7.bookryde.Model.VehicleData
import com.example.win7.bookryde.Notification.NotificationListActivity
import com.example.win7.bookryde.R
import com.example.win7.bookryde.Utils
import com.example.win7.bookryde.databinding.ActivityBookRideBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.location.LocationServices

class BookRideActivity : BaseActivity() ,View.OnClickListener , OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener,
    GoogleMap.OnMarkerDragListener,
    GoogleMap.OnMapLongClickListener{
    lateinit var binding:ActivityBookRideBinding
    private val vehicleList: ArrayList<VehicleData> = arrayListOf()
    lateinit var bookRideAdapter: BookRideAdapter
    private var locationManager : LocationManager? = null
    private var mMap: GoogleMap? = null
    private var longitude: Double = 0.toDouble()
    private var latitude: Double = 0.toDouble()

    private val googleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookRideBinding.inflate(layoutInflater, baseBinding.frmContainer, true)
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?;

        try {
            // Request location updates

            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener);
        } catch(ex: SecurityException) {
            Log.d("myTag", "Security Exception, no location available");
        }
        init()
    }
    fun init(){
        setNavigationView()
        setToolbarTitle(getString(R.string.book_ride))
        setNotificationButton(true)
        baseBinding.imgNotification.setOnClickListener(this)
        setVehicleData()



    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.imgNotification -> {
                startActivity(Intent(me, NotificationListActivity::class.java))
            }
            R.id.btnRideLater->{

            }
        }
    }
    fun setVehicleData(){
        vehicleList.clear()
        var item = VehicleData()
        item.vehicleName="MINI"
        item.vehicleImage=getDrawable(R.drawable.cab1)
        vehicleList.add(item)
        var item1 = VehicleData()
        item1.vehicleName="MICRO"
        item1.vehicleImage=getDrawable(R.drawable.cab1)
        vehicleList.add(item1)

        var item2 = VehicleData()
        item2.vehicleName="SUV"
        item2.vehicleImage=getDrawable(R.drawable.cab1)
        vehicleList.add(item2)

        var item3 = VehicleData()
        item3.vehicleName="SEDAN"
        item3.vehicleImage=getDrawable(R.drawable.cab1)
        vehicleList.add(item3)

        bookRideAdapter = BookRideAdapter(this@BookRideActivity,vehicleList)
        val mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recCablist.layoutManager = mLayoutManager
        binding.recCablist.adapter = bookRideAdapter
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
          /*  thetext.setText("" + location.longitude + ":" + location.latitude);*/
            Utils.openMap(me,location.latitude,location.longitude,getString(R.string.your_loaction))
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        //Creating a random coordinate
        val latLng = LatLng(-34.0, 151.0)
        //Adding marker to that coordinate
        mMap!!.addMarker(MarkerOptions().position(latLng).draggable(true))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        //Setting onMarkerDragListener to track the marker drag
        mMap!!.setOnMarkerDragListener(this)
        //Adding a long click listener to the map
        mMap!!.setOnMapLongClickListener(this)
    }

    override fun onConnected(p0: Bundle?) {
        getCurrentLocation()
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMarkerDragEnd(marker: Marker?) {
        latitude = marker!!.position.latitude
        longitude = marker.position.longitude

        //Moving the map
        moveMap();
    }

    override fun onMarkerDragStart(p0: Marker?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMarkerDrag(p0: Marker?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapLongClick(latLng: LatLng?) {
        mMap!!.clear()

        mMap!!.addMarker(
            MarkerOptions()
                .position(latLng!!)
                .draggable(true)
        )
    }


    private fun getCurrentLocation() {
        mMap!!.clear()
        //Creating a location object
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        mMap!!.isMyLocationEnabled = true
        val location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.longitude
            latitude = location.latitude
            moveMap()
        }
    }

    private fun moveMap() {
        val latLng = LatLng(latitude, longitude)
        mMap!!.addMarker(
            MarkerOptions()
                .position(latLng) //setting position
                .draggable(true) //Making the marker draggable
                .title("Current Location")
        )
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))

        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(15f))

    }

}
