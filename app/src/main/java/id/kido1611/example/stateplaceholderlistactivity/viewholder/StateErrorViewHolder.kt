package id.kido1611.example.stateplaceholderlistactivity.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kido1611.example.stateplaceholderlistactivity.databinding.StateErrorViewBinding

class StateErrorViewHolder(
    private val binding: StateErrorViewBinding,
    private val onRetry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener {
            onRetry.invoke()
        }
    }

    fun bind(message: String) {
        binding.tvMessage.text = message
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup, onRetry: () -> Unit): StateErrorViewHolder {
            val binding = StateErrorViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return StateErrorViewHolder(binding, onRetry)
        }
    }
}