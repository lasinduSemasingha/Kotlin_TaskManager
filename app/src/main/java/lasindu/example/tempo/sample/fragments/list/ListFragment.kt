package lasindu.example.tempo.sample.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import lasindu.example.tempo.sample.R
import lasindu.example.tempo.sample.viewmodel.TaskViewModel
import lasindu.example.tempo.sample.databinding.FragmentListBinding

class ListFragment : Fragment() {

    // Inflate the layout using View Binding
    private lateinit var binding: FragmentListBinding
    private lateinit var mTaskModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        //recyclerview
        val adapter = ListAdapter()
        val recyclerView = binding.recycleView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTaskModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskModel.readAllData.observe(viewLifecycleOwner, Observer{user ->
            adapter.setData(user)
        })

        binding.floatingBTN.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }
}
