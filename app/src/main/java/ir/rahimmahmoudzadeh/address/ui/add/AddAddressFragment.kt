package ir.rahimmahmoudzadeh.address.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.rahimmahmoudzadeh.address.databinding.AddLocationBinding
import org.koin.android.ext.android.get

class AddAddressFragment:Fragment() {
    private var _binding:AddLocationBinding?=null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=AddLocationBinding.inflate(inflater,container,false)
        return binding.root
    }
}