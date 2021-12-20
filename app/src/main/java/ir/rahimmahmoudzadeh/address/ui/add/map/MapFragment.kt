package ir.rahimmahmoudzadeh.address.ui.add.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mapbox.mapboxsdk.annotations.IconFactory
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import ir.map.sdk_map.MapirStyle
import ir.map.sdk_map.maps.MapView
import ir.rahimmahmoudzadeh.address.R
import ir.rahimmahmoudzadeh.address.databinding.MapFragmentBinding
import ir.rahimmahmoudzadeh.address.ui.MainActivity
import ir.rahimmahmoudzadeh.address.ui.add.AddAddressViewModel
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.showSnackBar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MapFragment : Fragment(), MapboxMap.OnMapLongClickListener {
    private var _binding: MapFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapView: MapView
    private var map: MapboxMap? = null
    private var mapStyle: Style? = null
    private var myMarker: Marker? = null
    private val viewModel by sharedViewModel<AddAddressViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MapFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMap()
        mapView.onCreate(savedInstanceState)

        binding.btnMapSaveLocation.setOnClickListener {
            if (checkMapEmpty())
            {
                saveAddressInSrver()
            }
        }
    }

    private fun saveAddressInSrver() {
        viewModel.checkSaveAddress().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        binding.progressBarMap.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        findNavController().navigate(R.id.action_mapFragment_to_homeFragment)
                    }
                    is Resource.Error -> {
                        showSnackBar(binding.root, resource.message.toString())
                        binding.progressBarMap.visibility = View.GONE
                    }
                }
            }
        }
    }


    private fun setMap() {
        _binding?.let { binding ->
            mapView = binding.mapView
            mapView.getMapAsync { mapboxMap ->
                map = mapboxMap
                map!!.setStyle(
                    Style.Builder()
                        .fromUri(MapirStyle.MAIN_MOBILE_VECTOR_STYLE)
                ) { style ->
                    mapStyle = style
                    map!!.addOnMapLongClickListener(this)
                }
            }
        }
    }

    override fun onMapLongClick(point: LatLng): Boolean {
        addMarker(point)
        viewModel.setAddressLocation(point.latitude, point.longitude)
        return true
    }

    private fun addMarker(position: LatLng) {
        val iconFactory = activity?.let { IconFactory.getInstance(it) }
        val icon = iconFactory?.fromResource(R.drawable.red_marker)
        if (map!!.markers.isNotEmpty()) {
            map!!.clear()
        }
        map!!.addMarker(
            MarkerOptions()
                .position(position)
                .icon(icon)
        )
    }

    private fun checkMapEmpty(): Boolean {
        return !map!!.markers.isNullOrEmpty()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}