package com.steru.suntime.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.steru.suntime.R
import com.steru.suntime.ui.vm.MainViewModel
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class DateListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sun_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.itemUpdated.observe(viewLifecycleOwner, {
            recyclerView?.adapter?.notifyItemChanged(it)
        })

        recyclerView = view.findViewById(R.id.sunTimeList)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = SunTimeListAdapter(
            viewModel.sunDataList,
            context)

    }
}