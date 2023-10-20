package com.example.ecoguardians

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

const val ARG_PARAM_POSITION_1 = "param1"
const val ARG_PARAM_POSITION_2 = "param2"
class Map : Fragment(), OnMapReadyCallback {

    private var mapView: MapView? = null
    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //checkMyPosition()
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        mapView = view?.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        if (googleMap != null) {
            // Imposta le coordinate desiderate
            val coordinates = LatLng(arguments?.getDouble(ARG_PARAM_POSITION_1)!!, arguments?.getDouble(ARG_PARAM_POSITION_2)!!) // San Francisco

            // Aggiungi un segnaposto alla posizione specificata
            googleMap?.addMarker(MarkerOptions().position(coordinates).title("Posizione Animale"))

            // Zoom sulla posizione specificata
            googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 12.0f))
        } else {
            Log.e("MapFragment", "GoogleMap is null")
        }
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }
    fun checkMyPosition(){
        Dexter.withContext(requireContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(object :
            PermissionListener {
            override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse) {
                Toast.makeText(requireActivity() , "Permesso concesso", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(permissionDeniedResponse: PermissionDeniedResponse) {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", "com.example.ecoguardians", "")
                intent.data = uri
                startActivity(intent)
            }

            override fun onPermissionRationaleShouldBeShown(permissionRequest : PermissionRequest, permissionToken : PermissionToken) {
                permissionToken.continuePermissionRequest()
            }
        }).check()
    }

    companion object {
        @JvmStatic
        fun newInstance(latitude : Double, longitude: Double) =
            Map().apply {
                arguments = Bundle().apply {
                    putDouble(ARG_PARAM_POSITION_1, latitude)
                    putDouble(ARG_PARAM_POSITION_2, longitude)
                }
            }
    }
}