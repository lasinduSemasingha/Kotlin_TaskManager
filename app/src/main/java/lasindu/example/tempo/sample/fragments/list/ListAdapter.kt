package lasindu.example.tempo.sample.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lasindu.example.tempo.sample.R
import lasindu.example.tempo.sample.model.Task

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var taskList = emptyList<Task>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val number: TextView = itemView.findViewById(R.id.number)
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)
        val deadline: TextView = itemView.findViewById(R.id.deadline)
        val priority: TextView = itemView.findViewById(R.id.priority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent,false))
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.number.text = currentItem.id.toString()
        holder.name.text = currentItem.name
        holder.description.text = currentItem.description
        holder.deadline.text = currentItem.deadline
        holder.priority.text = currentItem.priority

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(task: List<Task>){
        this.taskList = task
        notifyDataSetChanged()
    }
}