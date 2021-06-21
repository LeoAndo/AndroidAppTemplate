package com.example.androidapptemplate.features.playground

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapptemplate.R
import com.example.androidapptemplate.core.widget.SimpleListAdapter
import com.example.androidapptemplate.databinding.WebApiTopFragmentBinding
import com.example.androidapptemplate.core.util.ToastHelper
import com.example.androidapptemplate.core.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class PlayGroundTopFragment : Fragment(R.layout.web_api_top_fragment) {
    private val binding by viewBindings(WebApiTopFragmentBinding::bind)

    @Inject
    lateinit var toastHelper: ToastHelper
    private val simpleListAdapter =
        SimpleListAdapter(
            dataset = listOf("Runtime Permissions", "Toast & SnackBar"),
            onItemClicked = {
                toastHelper.showToast("does not implementation.. coming implementation soon.")
            })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            adapter = simpleListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}