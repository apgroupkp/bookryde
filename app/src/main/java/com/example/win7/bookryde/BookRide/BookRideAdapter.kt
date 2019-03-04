package com.example.win7.bookryde.BookRide

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.win7.bookryde.Model.VehicleData
import com.example.win7.bookryde.databinding.RawCabListItemBinding

class BookRideAdapter(private var mContext: Context, private var vehicleList: MutableList<VehicleData>) : RecyclerView.Adapter<BookRideAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = RawCabListItemBinding.inflate(LayoutInflater.from(mContext))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vehicleList[position])
        holder.binding?.root?.setOnClickListener {
          /*  mContext.startActivity(
                Intent(mContext, BookingHistoryDetailActivity::class.java)
                    .putExtra("data", pastbookingList[position]))*/
        }
    }


    override fun getItemCount(): Int {
        return vehicleList.size
    }
    class ViewHolder(val binding: RawCabListItemBinding?) : RecyclerView.ViewHolder(binding?.root!!) {
        fun bind(item: VehicleData) {
            binding!!.txtCabName.text=item.vehicleName
            binding.imgCabItem.setImageDrawable(item.vehicleImage)
            binding.executePendingBindings()
        }
    }
}