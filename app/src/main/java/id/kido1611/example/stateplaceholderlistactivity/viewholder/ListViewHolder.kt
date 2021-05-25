package id.kido1611.example.stateplaceholderlistactivity.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kido1611.example.stateplaceholderlistactivity.ListItem
import id.kido1611.example.stateplaceholderlistactivity.databinding.ListViewBinding

class ListViewHolder(
    private val binding: ListViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ListViewHolder {
            val binding = ListViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return ListViewHolder(binding)
        }
    }

    fun bind(data: ListItem) {
        binding.listItem = data
        binding.executePendingBindings()
    }
}