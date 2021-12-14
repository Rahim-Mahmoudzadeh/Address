package ir.rahimmahmoudzadeh.address.ui.add.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.rahimmahmoudzadeh.address.databinding.MapFragmentBinding

class MapFragment : Fragment() {
    private var _binding: MapFragmentBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= MapFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}