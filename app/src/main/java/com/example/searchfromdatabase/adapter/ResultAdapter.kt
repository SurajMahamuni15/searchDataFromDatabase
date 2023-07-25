package com.example.searchfromdatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.searchfromdatabase.databinding.SearchResultItemBinding
import com.example.searchfromdatabase.model.VideoInfoData
import com.example.searchfromdatabase.viewHolder.ResultViewHolder

class ResultAdapter :
    RecyclerView.Adapter<ResultViewHolder>() {
    private var data: List<VideoInfoData> = ArrayList<VideoInfoData>().apply {
        add(VideoInfoData("001", "No Data Available!"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val binding =
            SearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.binding.title.text = data[position].subtitle
    }

    fun setData(newData: List<VideoInfoData>) {
        diffUtilCall(newData)
    }

    private fun diffUtilCall(newData: List<VideoInfoData>) {
        val diffCallback = CourseDiffUtil(
            data,
            newData
        )
        val diffCourses = DiffUtil.calculateDiff(diffCallback)
        data = newData
        diffCourses.dispatchUpdatesTo(this)
    }

    class CourseDiffUtil(var oldData: List<VideoInfoData>, var newData: List<VideoInfoData>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }

    }
}