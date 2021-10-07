package uz.task.to_do_app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_data.view.*
import uz.task.to_do_app.R
import uz.task.to_do_app.db.Data
import uz.task.to_do_app.getPriorityColor

class MyListAdapter(diffCallback: DataItemDiffCallback) :
    ListAdapter<Data, MyListAdapter.ViewHolder>(diffCallback) {

    var deleteListener: ((data: Data) -> Unit)? = null
    var editListener: ((data: Data) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false))

    override fun onBindViewHolder(holder: MyListAdapter.ViewHolder, position: Int) =
        holder.bindData(getItem(position))

    inner class ViewHolder(private var cvView: View) : RecyclerView.ViewHolder(cvView) {

        fun bindData(data: Data) {

            data.apply {
                cvView.name.text = name
                cvView.date.text = date
                cvView.priority.setBackgroundColor(getPriorityColor(priority))
            }

            cvView.apply {
                edit.setOnClickListener {
                    editListener?.invoke(data)
                }
                delete.setOnClickListener {
                    deleteListener?.invoke(data)
                }
            }
        }

    }
}