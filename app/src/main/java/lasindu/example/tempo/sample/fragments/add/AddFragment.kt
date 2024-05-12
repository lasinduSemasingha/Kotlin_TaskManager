package lasindu.example.tempo.sample.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import lasindu.example.tempo.sample.R
import lasindu.example.tempo.sample.model.Task
import lasindu.example.tempo.sample.viewmodel.TaskViewModel
import lasindu.example.tempo.sample.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    
    private lateinit var binding: FragmentAddBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        val prioritySpinner: Spinner = view.findViewById(R.id.prioritySpinner)
        
        //Inserting data
        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        
        binding.saveBTN.setOnClickListener{
            insertDataToDataBase()
        }
        

        val priorities = arrayOf("Priority", "Low", "Medium", "High")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        prioritySpinner.adapter = adapter

        prioritySpinner.setSelection(0, false)

        prioritySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (position == 0) {
                    Toast.makeText(requireContext(), "Please select a priority", Toast.LENGTH_SHORT).show()
                } else {

                    val selectedPriority = priorities[position]

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        return view
    }

    private fun insertDataToDataBase() {
        val name = binding.name.text.toString()
        val description = binding.description.text.toString()
        val deadline = binding.deadline.text.toString()
        val priority = binding.prioritySpinner.selectedItem.toString()

        if(inputCheck(name, description, deadline, priority)) {
            //Create user Object
            val task = Task(0, name, description, deadline, priority)
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Successful added!", Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(name: String, description: String, deadline: String, priority: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(description) && TextUtils.isEmpty(deadline) && TextUtils.isEmpty(priority))
    }
}
