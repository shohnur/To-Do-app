package uz.task.to_do_app.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.dialog.view.*
import uz.task.to_do_app.R
import uz.task.to_do_app.db.Data
import java.text.SimpleDateFormat
import java.util.*

class AddEditDialog(context: Context, data: Data? = null) : AlertDialog(context) {

    var listener: ((data: Data) -> Unit)? = null

    var p = arrayListOf("CRITIC", "IMPORTANT", "NOImportant")

    var chosedPriority = ""

    init {

        val view = LayoutInflater.from(context).inflate(
            R.layout.dialog, null, false
        )


        view.apply {
            if (data != null) {
                title.text = "Edit"
                name.setText(data.name)
                add.text = "Edit"

                when (data.priority) {
                    p[1] -> Collections.swap(p, 0, 1)
                    p[2] -> Collections.swap(p, 0, 2)
                    else -> {
                    }
                }
            } else {
                title.text = "Add"
            }

            chosedPriority = p[0]
            spinner.setItems(p)
            spinner.setOnItemSelectedListener { _, _, _, item ->
                chosedPriority = item.toString()
            }

            setCancelable(true)

            add.setOnClickListener {
                if (name.text.isBlank()) name.error = "This field must be filled"
                else {
                    listener?.invoke(
                        Data(
                            data?.id ?: 0,
                            name.text.toString(),
                            getDate(),
                            chosedPriority
                        )
                    )
                    cancel()
                }
            }
        }

        setView(view)

    }

    private fun getDate(): String {
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(Date())
    }

}