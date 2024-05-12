package lasindu.example.tempo.sample.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import lasindu.example.tempo.sample.R
import lasindu.example.tempo.sample.databinding.FragmentListBinding

class ListFragment : Fragment() {

    // Inflate the layout using View Binding
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.floatingBTN.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }
}
