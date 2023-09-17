package com.example.caesarappgithub.ui
    import android.os.Bundle
    import android.view.View
    import androidx.appcompat.app.AppCompatActivity
    import androidx.lifecycle.ViewModelProvider
    import androidx.recyclerview.widget.DividerItemDecoration
    import androidx.recyclerview.widget.LinearLayoutManager
    import com.example.caesarappgithub.Itemgithub.Api.DetailUserResponse
    import com.example.caesarappgithub.Itemgithub.Model.SearchViewModel
    import com.example.caesarappgithub.showLoading
    import com.example.caesarappgithub.databinding.ActivityMainBinding
    import com.example.caesarappgithub.ui.adapter.SearchListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        viewModel.isLoading.observe(this) {
            binding.progressBar2.showLoading(it)
        }


        viewModel.isEmpty.observe(this){
            showEmpty(it)
        }

        viewModel.listUserDetail.observe(this) {
            binding.rvCari.adapter = SearchListAdapter(it)
        }

        val edit = binding.textInputLayout.editText
        edit?.setOnEditorActionListener { textView, _, _ ->
            viewModel.searchUser(textView.text.toString())
            false
        }

        val manager = LinearLayoutManager(this)
        binding.rvCari.layoutManager = manager
        val decor = DividerItemDecoration(this, manager.orientation)
        binding.rvCari.addItemDecoration(decor)

    }

    private fun showEmpty(isEmpty: Boolean) {
        binding.tidakadadata.visibility = when (isEmpty) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
        val adapter = SearchListAdapter(listOf<DetailUserResponse>())
        binding.rvCari.adapter = adapter
    }
}
