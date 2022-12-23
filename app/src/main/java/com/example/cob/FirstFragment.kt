package com.example.cob

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cob.database.AppDatabase
import com.example.cob.databinding.FragmentFirstBinding
import com.example.cob.models.User
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: PlayersListViewModel

    private lateinit var adapter: ListPlayerAdapter

    private val database = AppDatabase.INSTANCE?.playerDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(PlayersListViewModel::class.java)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListPlayerAdapter{
            lifecycleScope.launch {
                if(it.name == database?.get()?.name)
                     findNavController().navigate(R.id.action_FirstFragment_to_secondFragment)
                else
                    Log.v("CLICK_CARD", "On lance le combat")
            }
        }

        binding.rvPlayer.adapter = adapter
        viewModel.players?.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        binding.logout.setOnClickListener {
            lifecycleScope.launch {
                val user: User? = database?.get()
                if(user != null){
                    database?.delete(user)
                    activity?.finish()
                }
            }

        }

        viewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}