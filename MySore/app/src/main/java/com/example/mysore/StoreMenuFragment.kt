package com.example.mysore

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysore.Adapter.StoreAdapter
import com.example.mysore.Connection.APIStore
import com.example.mysore.Interfaces.SupportFragmentManager
import com.example.mysore.Modelos.Items
import java.io.IOException

class StoreMenuFragment : Fragment() {

    private val TAG = "Store Warning"
    private lateinit var rootview: View
    private lateinit var listener: SupportFragmentManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as StoreActivity
        }catch (e: IOException){
            Log.d(TAG,"onAttach es null")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_store_menu, container, false)

        APIStore().sacardatosV2 {
            setRecyclerView()
        }
        // Create the observer which updates the UI.
        //val nameObserver = Observer<ArrayList<Items>> { newName ->
            // Update the UI, in this case, a TextView.
        //    setRecyclerView()
        //}
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        //APIStore().listaObservable.observe(this, nameObserver)


        return rootview
    }

    fun setRecyclerView(){
        val recyclerView = rootview.findViewById<RecyclerView>(R.id.store_recycler_view)
        //val adapter = StoreAdapter(APIStore().listaDeItems, this.requireContext())//aqui va la lista de digimones
        val adapter = StoreAdapter(APIStore().listaObservable.value, this.requireContext())
        adapter.setOnItemClickListener(object: StoreAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                listener.setFragment(ShoopingCarMenuFragment(),
                                    position.toString(),
                                    APIStore().listaDeItems[position].precio.toString())
            }
        })
        recyclerView.adapter =  adapter
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StoreMenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            StoreMenuFragment().apply {

            }
    }
}