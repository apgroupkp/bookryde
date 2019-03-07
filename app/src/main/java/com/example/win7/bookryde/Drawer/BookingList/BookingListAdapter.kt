package com.example.win7.bookryde.Drawer.BookingList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.win7.bookryde.Model.BookingHistoryData
import com.example.win7.bookryde.binding
import com.example.win7.bookryde.databinding.RawBookingHistoryItemBinding

class BookingListAdapter (private var mContext: Context, private var bookinglist: MutableList<BookingHistoryData>) : RecyclerView.Adapter<BookingListAdapter.ViewHolder>() {
    internal var Raw_item: Int = 0
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding = RawBookingHistoryItemBinding.inflate(LayoutInflater.from(mContext))
        return ViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return  bookinglist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=bookinglist[position]
        holder.binding!!.txtBookingId.text=item.bookingid
        holder.binding.txtPickAdd.text=item.pickupaddress
        holder.binding.txtDropAdd.text=item.dropaddress
        holder.binding.executePendingBindings()
    }

    class ViewHolder(val binding: RawBookingHistoryItemBinding?) : RecyclerView.ViewHolder(binding?.root!!) {
        fun bind(item: BookingHistoryData) {
            val s=item.datetime
            val words = s!!.split(" ")
            val date = words[0]
            val month= words[1]

        }
    }
}