package lasindu.example.tempo.sample.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import lasindu.example.tempo.sample.R

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val prioritySpinner: Spinner = view.findViewById(R.id.prioritySpinner)

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
}
