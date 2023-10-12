package com.example.ecoguardians

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

const val ARG_PARAM_POSITION_1 = "param1"
const val ARG_PARAM_POSITION_2 = "param2"
class Map : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkMyPosition()


        return inflater.inflate(R.layout.fragment_map, container, false)
    }
    private fun checkMyPosition(){
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