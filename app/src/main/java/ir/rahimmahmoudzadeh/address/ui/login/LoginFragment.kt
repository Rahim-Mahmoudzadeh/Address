package ir.rahimmahmoudzadeh.address.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.rahimmahmoudzadeh.address.R
import ir.rahimmahmoudzadeh.address.data.sharedPreferences.UserSave
import ir.rahimmahmoudzadeh.address.databinding.LoginFragmentBinding
import ir.rahimmahmoudzadeh.address.utils.Resource
import ir.rahimmahmoudzadeh.address.utils.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    val viewModel: LoginViewModel by viewModel()
    lateinit var saveUser: UserSave
    override fun onAttach(context: Context) {
        super.onAttach(context)
        saveUser = UserSave(context)
        if (saveUser.getMobile().isNotEmpty() || saveUser.getPassword()
                .isNotEmpty()
        ) {
            saveUser.delete()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            if (!checkEmpty()) {
                saveUser()
                checkUser()
            }
        }
    }

    private fun checkUser() {
        viewModel.checkUser().observe(viewLifecycleOwner) {
            it?.let { resources ->
                when (resources) {
                    is Resource.Loading -> {
                        binding.progressBarLogin.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBarLogin.visibility = View.GONE
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    is Resource.Error -> {
                        binding.progressBarLogin.visibility = View.GONE
                        showSnackBar(binding.root, resources.message.toString())
                    }
                }
            }
        }

    }

    private fun checkEmpty(): Boolean {
        var errorTextFiled = 0
        context?.let { context ->
            if (binding.tietLoginUsername.text.isNullOrEmpty()) {
                errorTextFiled = errorTextFiled.plus(1)
                binding.tilLoginAddress.error = context.getString(R.string.error_textInput)
            } else {
                binding.tilLoginAddress.error = ""
            }

            if (binding.tietLoginPassword.text.isNullOrEmpty()) {
                errorTextFiled = errorTextFiled.plus(1)
                binding.tilLoginPassword.error = context.getString(R.string.error_textInput)
            } else {
                binding.tilLoginPassword.error = ""
            }
        }
        return errorTextFiled > 0
    }

    private fun saveUser() {
        if (!saveUser.getMobile().isNullOrEmpty() || !saveUser.getPassword().isNullOrEmpty()) {
            saveUser.delete()
        }
        saveUser.saveUser(
            binding.tietLoginUsername.text.toString(),
            binding.tietLoginPassword.text.toString()
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}