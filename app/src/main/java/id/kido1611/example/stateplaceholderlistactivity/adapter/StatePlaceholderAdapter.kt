package id.kido1611.example.stateplaceholderlistactivity.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kido1611.example.stateplaceholderlistactivity.UiState

abstract class StatePlaceholderAdapter<DataType> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val STATE_ERROR = 1
        const val STATE_IDLE = 2
        const val STATE_LOADING = 3
        const val STATE_SUCCESS = 4
        const val STATE_SUCCESS_EXTRA = 5
    }

    private val _items = mutableListOf<DataType>()
    private var _uiState: UiState<List<DataType>> = UiState.Idle

    fun setState(state: UiState<List<DataType>>) {
        _uiState = state
        if (state is UiState.Success) {
            _items.clear()
            _items.addAll(state.data)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            STATE_IDLE -> getSuccessViewHolder(parent)
            STATE_LOADING -> getLoadingViewHolder(parent)
            STATE_ERROR -> getErrorViewHolder(parent)
            STATE_SUCCESS -> getSuccessViewHolder(parent)
            STATE_SUCCESS_EXTRA -> getSuccessExtraViewHolder(parent)
            else -> throw IllegalArgumentException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (_uiState) {
            is UiState.Error -> {
                bindErrorViewHolder(holder, (_uiState as UiState.Error).message)
            }
            UiState.Idle -> {

            }
            UiState.Loading -> {

            }
            is UiState.Success -> {
                if (position < _items.size) {
                    val data = _items[position]
                    bindSuccessViewHolder(holder, data)
                }
            }
        }
    }

    override fun getItemCount(): Int = when (_uiState) {
        is UiState.Error -> 1
        UiState.Idle -> 0
        UiState.Loading -> getPlaceholderLoadingCount()
        is UiState.Success -> if (hasExtraItem()) {
            _items.size + 1
        } else {
            _items.size
        }
    }

    override fun getItemViewType(position: Int): Int = when (_uiState) {
        is UiState.Error -> STATE_ERROR
        UiState.Idle -> STATE_IDLE
        UiState.Loading -> STATE_LOADING
        is UiState.Success -> if ((itemCount - 1) == position) {
            STATE_SUCCESS_EXTRA
        } else {
            STATE_SUCCESS
        }
    }

    abstract fun getPlaceholderLoadingCount(): Int

    abstract fun getSuccessViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun getSuccessExtraViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun getLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun getErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    abstract fun bindSuccessViewHolder(holder: RecyclerView.ViewHolder, data: DataType)
    abstract fun bindErrorViewHolder(holder: RecyclerView.ViewHolder, message: String)

    abstract fun hasExtraItem(): Boolean
}