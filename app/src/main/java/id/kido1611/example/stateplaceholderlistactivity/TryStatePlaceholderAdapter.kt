package id.kido1611.example.stateplaceholderlistactivity

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kido1611.example.stateplaceholderlistactivity.adapter.StatePlaceholderAdapter
import id.kido1611.example.stateplaceholderlistactivity.viewholder.ListViewHolder
import id.kido1611.example.stateplaceholderlistactivity.viewholder.StateErrorViewHolder
import id.kido1611.example.stateplaceholderlistactivity.viewholder.StateLoadingViewHolder

class TryStatePlaceholderAdapter(
    private val onRetry: () -> Unit
) : StatePlaceholderAdapter<ListItem>() {
    override fun getPlaceholderLoadingCount(): Int = 2

    override fun getSuccessViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ListViewHolder.create(parent)
    }

    override fun getLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return StateLoadingViewHolder.create(parent)
    }

    override fun getErrorViewHolder(
        parent: ViewGroup
    ): RecyclerView.ViewHolder {
        return StateErrorViewHolder.create(parent, onRetry)
    }

    override fun bindSuccessViewHolder(holder: RecyclerView.ViewHolder, data: ListItem) {
        if (holder is ListViewHolder) {
            holder.bind(data)
        }
    }

    override fun bindErrorViewHolder(holder: RecyclerView.ViewHolder, message: String) {
        if (holder is StateErrorViewHolder) {
            holder.bind(message)
        }
    }

    override fun getSuccessExtraViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return StateLoadingViewHolder.create(parent)
    }

    override fun hasExtraItem(): Boolean = true
}