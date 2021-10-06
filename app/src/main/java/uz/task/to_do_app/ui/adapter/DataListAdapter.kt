package uz.task.to_do_app.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_data.view.*
import uz.task.to_do_app.R
import uz.task.to_do_app.db.Data
import uz.task.to_do_app.getPriorityColor

class DataListAdapter(private val context: Context) :
    BaseAdapter() {

    private val data = arrayListOf<Data>()

    var deleteListener: ((data: Data) -> Unit)? = null
    var editListener: ((data: Data) -> Unit)? = null

    fun setData(d: List<Data>) {
        data.clear()
        data.addAll(d)
        data.sortBy { it.priority }
        notifyDataSetChanged()
    }

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Data = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val cvView = convertView
            ?: LayoutInflater.from(context).inflate(
                R.layout.item_data, parent, false
            )

        data[position].apply {
            cvView.name.text = name
            cvView.date.text = date
            cvView.priority.setBackgroundColor(getPriorityColor(priority))
        }

        cvView.apply {
            edit.setOnClickListener {
                editListener?.invoke(data[position])
            }
            delete.setOnClickListener {
                deleteListener?.invoke(data[position])
            }
        }

        return cvView
    }
}