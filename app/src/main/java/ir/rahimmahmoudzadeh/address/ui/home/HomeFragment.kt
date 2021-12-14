package ir.rahimmahmoudzadeh.address.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.rahimmahmoudzadeh.address.R
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.databinding.HomeBinding
import ir.rahimmahmoudzadeh.address.ui.MainActivity
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!

    val viewModel: HomeViewModel by viewModel()
    val mainActivity = MainActivity()
    val homeRvAdapter = HomeRvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mainActivity.isConnected(binding.root, requireContext())) {
            getAddress()
        }
        binding.rvHomeAddress.adapter = homeRvAdapter
        binding.rvHomeAddress.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.fabHomeAddLocation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addLocation)
        }

    }

    private fun getAddress() {
        viewModel.getAddressLiveData().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        homeRvAdapter.location = it.data as ArrayList<LocationInformation>
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        showSnackBar(binding.root, resource.message.toString())
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}