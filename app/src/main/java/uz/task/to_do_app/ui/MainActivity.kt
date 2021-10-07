package uz.task.to_do_app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import uz.task.to_do_app.R
import uz.task.to_do_app.ui.adapter.DataItemDiffCallback
import uz.task.to_do_app.ui.adapter.MyListAdapter
import uz.task.to_do_app.ui.dialog.AddEditDialog

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<AppViewModel>()
    private lateinit var adapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()

        viewModel.loadData()
        viewModel.data.observe(
            this, {
                adapter.submitList(it.sortedBy { it.priority })
            }
        )

        add.setOnClickListener {
            val dialog = AddEditDialog(this)
            dialog.show()
            dialog.listener = {
                viewModel.addData(it)
            }
        }
    }

    private fun initList() {
        adapter = MyListAdapter(DataItemDiffCallback())
        listView.adapter = adapter

        adapter.apply {
            deleteListener = {
                viewModel.deleteData(it)
            }

            editListener = {
                val dialog = AddEditDialog(this@MainActivity, data = it)
                dialog.show()
                dialog.listener = { data ->
                    viewModel.updateData(data)
                }

            }
        }
    }


}