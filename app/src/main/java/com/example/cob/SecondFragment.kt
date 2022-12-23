package com.example.cob

import android.hardware.camera2.params.Capability
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cob.databinding.FragmentSecondBinding
import com.example.cob.ui.capabilities.SelectCapabilityActivity
import com.example.democlashofbattle.utils.loadImage
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: EditViewModel
    private var _binding: FragmentSecondBinding ? = null
    // This property is only valid between onCreateView and
    // onDestroyView.



    private val selectCapabilityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { it.data?.let { intent ->
            val value = SelectCapabilityActivity.extractResultData(intent)
            //viewModel.updateCapability(value.first, value.second)
        }
    }


    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        return binding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentPlayer.observe(viewLifecycleOwner) {
            binding.nom.setText(it.name)
            binding.url.setText(it.imageUrl)
            loadImage(binding.image, it.imageUrl)
        }

        binding.valider.setOnClickListener {
            lifecycleScope.launch {
                viewModel.updatePlayer(binding.nom.text.toString(), binding.url.text.toString())
            }

            findNavController().popBackStack()
        }

    }




}