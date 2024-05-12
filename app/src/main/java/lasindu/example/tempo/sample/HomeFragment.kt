package lasindu.example.tempo.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import lasindu.example.tempo.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        databaseHelper = DatabaseHelper(requireContext())

        val username = arguments?.getString("USERNAME")

        val firstName = getFirstNameFromDatabase(username)

        binding.firstNameTextView.text = "Hi, $firstName"

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_tasks -> {
                    findNavController().navigate(R.id.action_homeFragment_to_taskFragment)
                    true
                }
                else -> false
            }
        }

        return view
    }

    private fun getFirstNameFromDatabase(username: String?): String {

        return if (username != null) {

            databaseHelper.getFirstName(username)
        } else {
            ""
        }
    }
}
