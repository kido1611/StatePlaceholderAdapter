package id.kido1611.example.stateplaceholderlistactivity.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kido1611.example.stateplaceholderlistactivity.databinding.StateLoadingViewBinding

class StateLoadingViewHolder(
    binding: StateLoadingViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): StateLoadingViewHolder {
            val binding = StateLoadingViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return StateLoadingViewHolder(binding)
        }
    }
}