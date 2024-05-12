package lasindu.example.tempo.sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import lasindu.example.tempo.sample.R
import lasindu.example.tempo.sample.databinding.FragmentHomeBinding
import lasindu.example.tempo.sample.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    findNavController().navigate(R.id.action_taskFragment_to_homeFragment)
                    true
                }
                else -> false
            }
        }

        return view
    }
}