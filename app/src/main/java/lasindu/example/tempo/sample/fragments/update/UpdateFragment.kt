package lasindu.example.tempo.sample.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import lasindu.example.tempo.sample.R
import lasindu.example.tempo.sample.databinding.FragmentUpdateBinding
import lasindu.example.tempo.sample.model.Task
import lasindu.example.tempo.sample.viewmodel.TaskViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel
    private var _binding: FragmentUpdateBinding? = null // Declare binding as nullable
    private val binding get() = _binding!! // Use a non-null assert (!!) operator when accessing the binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.name.setText(args.currentUser.name)
        binding.description.setText(args.currentUser.description)
        binding.prioritySpinner.setText(args.currentUser.priority)
        binding.deadline.setText(args.currentUser.deadline)


        binding.saveBTN.setOnClickListener{
            updateItem()
        }

        binding.deleteBTN.setOnClickListener{
            deleteTask()
        }
        setHasOptionsMenu(true)

        return view
    }
    private fun updateItem(){
        val name = binding.name.text.toString()
        val description = binding.description.text.toString()
        val deadline = binding.deadline.text.toString()
        val priority = binding.prioritySpinner.text.toString()

        if(inputCheck(name, description, deadline, priority)) {
            //Create user Object
            val updatedTask = Task(args.currentUser.id, name, description, deadline, priority)
            mTaskViewModel.updateUser(updatedTask)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_LONG).show()
            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }

    }
    private fun inputCheck(name: String, description: String, deadline: String, priority: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(description) && TextUtils.isEmpty(deadline) && TextUtils.isEmpty(priority))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete){
            deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        // Delete the task immediately without showing an AlertDialog
        mTaskViewModel.deleteTask(args.currentUser)
        Toast.makeText(requireContext(), "Successfully removed: ${args.currentUser.name}", Toast.LENGTH_SHORT).show()
        // Navigate back after deletion
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear the binding reference in onDestroyView to avoid memory leaks
    }
}
