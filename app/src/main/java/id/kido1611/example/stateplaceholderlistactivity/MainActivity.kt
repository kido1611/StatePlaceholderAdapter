package id.kido1611.example.stateplaceholderlistactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.kido1611.example.stateplaceholderlistactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val titleAdapter: TryStatePlaceholderAdapter by lazy {
        TryStatePlaceholderAdapter(onRetry = {
            titleAdapter.setState(UiState.Success(getList()))
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rv.apply {
                adapter = titleAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
            }
            btnLoading.setOnClickListener {
                titleAdapter.setState(UiState.Loading)
            }
            btnError.setOnClickListener {
                titleAdapter.setState(UiState.Error("Ada kesalahan"))
            }
            btnSuccess.setOnClickListener {
                titleAdapter.setState(UiState.Success(getList()))
            }
        }
    }

    private fun getList(): List<ListItem> {
        val list = mutableListOf<ListItem>()
        list.add(ListItem("abcde"))
        list.add(ListItem("fghij"))
        list.add(ListItem("lmnop"))
        list.add(ListItem("qrstu"))
        return list
    }
}