package com.example.cob

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Database
import com.example.cob.api.PlayerApi
import com.example.cob.database.AppDatabase
import com.example.cob.databinding.FragmentLoginBinding
import com.example.cob.models.User
import com.example.cob.utils.toListOfPlayers
import kotlinx.coroutines.launch
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var  intent: Intent

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        intent = Intent(context, MainActivity::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            val userConect = AppDatabase.INSTANCE?.playerDao()?.get()
            if( userConect?.size!! > 0)
                startActivity(intent)
        }

        binding.buttonFirst.setOnClickListener() {
            val nameUser = binding.editText.text.toString()

            lifecycleScope.launch {

                val listName: List<String> = PlayerApi.service.getAll().toListOfPlayers().map {
                        t -> t.name.uppercase()
                }

                if(listName.indexOf(nameUser.uppercase()) != -1) {
                    lifecycleScope.launch {
                        AppDatabase.INSTANCE?.playerDao()?.insert(User(nameUser))
                        startActivity(intent)
                    }
                }else {
                    binding.editText.text.clear()
                    Toast.makeText(context, "Choisissez Un Nom valide S'il vous plait", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}