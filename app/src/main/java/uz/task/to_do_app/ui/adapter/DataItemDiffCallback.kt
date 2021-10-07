package uz.task.to_do_app.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import uz.task.to_do_app.db.Data

class DataItemDiffCallback : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
        oldItem == newItem
}