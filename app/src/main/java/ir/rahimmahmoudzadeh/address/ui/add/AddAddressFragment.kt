package ir.rahimmahmoudzadeh.address.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.rahimmahmoudzadeh.address.R
import ir.rahimmahmoudzadeh.address.databinding.AddLocationBinding
import org.koin.android.ext.android.get

class AddAddressFragment : Fragment() {
    private var _binding: AddLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddLocationGoToMap.setOnClickListener {
            if (!checkEmpty())
            {
                findNavController().navigate(R.id.action_addLocation_to_mapFragment)
            }
        }

    }

    private fun checkEmpty():Boolean {
        var errorTextField = 0
        if (binding.tietAddName.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.tilAddName.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else {
            binding.tilAddName.error = ""
        }
        if (binding.tietAddLastName.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.tilAddLastName.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else {
            binding.tilAddLastName.error = ""
        }
        if (binding.tietAddPhoneNumber.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.tilAddPhoneNumber.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else {
            binding.tilAddPhoneNumber.error = ""
        }
        if (binding.tietAddLandlinePhone.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.tilAddLandlinePhone.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else {
            binding.tilAddLandlinePhone.error = ""
        }
        if (binding.tietAddAddress.text.isNullOrEmpty()) {
            errorTextField = errorTextField.plus(1)
            binding.tilLoginAddress.error =
                requireContext().resources.getString(R.string.error_textInput)
        } else {
            binding.tilLoginAddress.error = ""
        }
        return errorTextField > 0
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}