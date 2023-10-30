package com.example.ecoguardians

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

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
            // Set the desired coordinates
            val coordinates = LatLng(arguments?.getDouble(ARG_PARAM_POSITION_1)!!, arguments?.getDouble(ARG_PARAM_POSITION_2)!!) // San Francisco

            // Add a placeholder to the specified location
            googleMap?.addMarker(MarkerOptions().position(coordinates).title("Posizione Animale"))

            // Zoom to the specified position
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
