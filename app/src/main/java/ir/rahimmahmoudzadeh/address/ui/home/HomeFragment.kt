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
import ir.rahimmahmoudzadeh.address.data.model.LocationInformationEntity
import ir.rahimmahmoudzadeh.address.databinding.HomeBinding
import ir.rahimmahmoudzadeh.address.ui.MainActivity
import ir.rahimmahmoudzadeh.address.utils.AddressFragment
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.showSnackBar
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : AddressFragment() {
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
        getAddress()

        binding.rvHomeAddress.adapter = homeRvAdapter
        binding.rvHomeAddress.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.fabHomeAddLocation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addLocation)
        }
        binding.imageViewrefresh.setOnClickListener {
            viewModel.getAddress()
            getAddress()
        }

    }

    private fun getAddress() {
        viewModel.getAddressLiveData.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        showProgressBar(true)
                    }
                    is Resource.Success -> {
                        showProgressBar(false)
                        homeRvAdapter.location =
                            resource.data as ArrayList<LocationInformationEntity>
                        binding.textViewRefresh.visibility = View.GONE
                        binding.imageViewrefresh.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        showProgressBar(false)
                        showSnackBar(binding.root, resource.message.toString())
                        binding.textViewRefresh.visibility = View.VISIBLE
                        binding.imageViewrefresh.visibility = View.VISIBLE
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