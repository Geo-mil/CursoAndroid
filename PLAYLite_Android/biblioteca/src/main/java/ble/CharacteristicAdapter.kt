package ble

import android.bluetooth.BluetoothGattCharacteristic
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ble.libraries.printProperties
import com.prilux.biblioteca.R

class CharacteristicAdapter(
    private val items: List<BluetoothGattCharacteristic>,
    private val onClickListener: ((characteristic: BluetoothGattCharacteristic) -> Unit)
) : RecyclerView.Adapter<CharacteristicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.row_characteristic,parent, false)
       /* val view = parent.context.layoutInflater.inflate(
            R.layout.row_characteristic,
            parent,
            false
        )*/
        return ViewHolder(view, onClickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class ViewHolder(
        private val view: View,
        private val onClickListener: ((characteristic: BluetoothGattCharacteristic) -> Unit)
    ) : RecyclerView.ViewHolder(view) {
        val characteristicaUUID: TextView = view.findViewById(R.id.characteristic_uuid)
        val characteristic_properties: TextView = view.findViewById(R.id.characteristic_properties)

        fun bind(characteristic: BluetoothGattCharacteristic) {
            characteristicaUUID.text = characteristic.uuid.toString()
            characteristic_properties.text = characteristic.printProperties()
            view.setOnClickListener { onClickListener.invoke(characteristic) }
        }
    }
}
