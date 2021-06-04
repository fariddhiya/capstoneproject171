package com.dicoding.capstoneproject.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capstoneproject.databinding.FragmentBookmarkBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private lateinit var adapter: BookmarkAdapter
    private lateinit var binding: FragmentBookmarkBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookmarkBinding.inflate(layoutInflater, container, false  )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        touchHelper()
        bookmarkViewModel.getBookmarks().observe(viewLifecycleOwner){
            if (it.isNotEmpty()) {
                adapter = BookmarkAdapter()
                adapter.differ.submitList(it)
                binding.bookmarkRv.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = this@BookmarkFragment.adapter
                    setHasFixedSize(true)

                }
            }
        }
    }
    private fun touchHelper() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val data = adapter.differ.currentList[position]

                bookmarkViewModel.deleteFromBookmark(data)

                Snackbar.make(
                    binding.root,
                    "Movie Deleted From Favourite",
                    Snackbar.LENGTH_LONG
                ).apply {
                    setAction("UNDO") {
                        bookmarkViewModel.insertToBookmark(data)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.bookmarkRv)
        }
    }
}