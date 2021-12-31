package ir.rahimmahmoudzadeh.address.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.rahimmahmoudzadeh.address.R
import ir.rahimmahmoudzadeh.address.data.model.LocationInformation
import ir.rahimmahmoudzadeh.address.data.model.LocationInformationEntity
import ir.rahimmahmoudzadeh.address.databinding.ListLocationBinding

class HomeRvAdapter: RecyclerView.Adapter<HomeRvAdapter.ViewHolder>() {

    var location = ArrayList<LocationInformationEntity>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ListLocationBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bin(location[position])
    }

    override fun getItemCount(): Int =location.size
    inner class ViewHolder(itemView:ListLocationBinding):RecyclerView.ViewHolder(itemView.root){
        val item=itemView
        fun bin(locationInformation: LocationInformationEntity)
        {
            item.tvListAddressAddress.text=itemView.context.resources.getString(R.string.address_location)+" "+locationInformation.address
            item.tvListAddressLastName.text=itemView.context.resources.getString(R.string.last_name)+" "+locationInformation.first_name+" "+locationInformation.last_name
            item.tvListAddressMobile.text=itemView.context.resources.getString(R.string.mobile)+" "+locationInformation.coordinate_mobile
        }


    }

}