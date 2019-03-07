package com.example.win7.bookryde.Drawer.BookingList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.win7.bookryde.Model.BookingHistoryData
import com.example.win7.bookryde.R
import com.example.win7.bookryde.databinding.RawDateItemBinding

class BookingDateAdpter (private var mContext: Context,private var bookinglist: MutableList<BookingHistoryData>) : RecyclerView.Adapter<BookingDateAdpter.ViewHolder>() {
    internal var Raw_item: Int = 0
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding = RawDateItemBinding.inflate(LayoutInflater.from(mContext))
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
      return  bookinglist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookinglist[position])
        holder.itemView.setOnClickListener {
            Raw_item = position
            notifyDataSetChanged()
        }
        if (Raw_item == position) {
            holder.binding!!.lilDateItem.background=mContext.getDrawable(R.drawable.selected_date_background)
            holder.binding!!.txtdate.setTextColor(mContext.resources.getColor(R.color.colorWhite))
            holder.binding.txtDateMonth.setTextColor(mContext.resources.getColor(R.color.colorWhite))
        } else {
            holder.binding!!.lilDateItem.background=mContext.getDrawable(R.drawable.date_layout_background)
            holder.binding!!.txtdate.setTextColor(mContext.resources.getColor(R.color.colorPrimaryDark))
            holder.binding.txtDateMonth.setTextColor(mContext.resources.getColor(R.color.colorPrimaryDark))
        }
    }

    class ViewHolder(val binding: RawDateItemBinding?) : RecyclerView.ViewHolder(binding?.root!!) {
        fun bind(item: BookingHistoryData) {
            val s=item.datetime
            val words = s!!.split(" ")
            val date = words[0]
            val month= words[1]
            binding!!.txtdate.text=date
            binding.txtDateMonth.text=month
            binding.executePendingBindings()
        }
    }
}






