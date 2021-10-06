package uz.task.to_do_app.ui

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject
import uz.task.to_do_app.db.Dao
import uz.task.to_do_app.db.Data

class AppViewModel(
    private val context: Context,
    private val dao: Dao
) : ViewModel(), KoinComponent {

    val data: MutableLiveData<List<Data>> by inject()

    @SuppressLint("HardwareIds")
    private val phoneId =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    private val firebaseDatabase = FirebaseDatabase.getInstance().getReference(phoneId)

    fun loadData() {
        dao.getData()?.let {
            data.value = it
        }
    }

    fun addData(data: Data) {
        dao.insert(data)
        firebaseDatabase.child("${data.id}").setValue(data).addOnCompleteListener {
            Toast.makeText(context, "Successfully added to firebase database", Toast.LENGTH_SHORT)
                .show()
        }.addOnFailureListener {
            Toast.makeText(context, "Add failed", Toast.LENGTH_SHORT)
                .show()
        }
        loadData()
    }

    fun deleteData(data: Data) {
        dao.delete(data)
        firebaseDatabase.child("${data.id}").removeValue().addOnCompleteListener {
            Toast.makeText(context, "Successfully deleted from firebase database", Toast.LENGTH_SHORT)
                .show()
        }.addOnFailureListener {
            Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT)
                .show()
        }
        loadData()
    }

    fun updateData(data: Data) {
        dao.update(data)
        firebaseDatabase.child("${data.id}").setValue(data).addOnCanceledListener {
            Toast.makeText(context, "Successfully updated on firebase database", Toast.LENGTH_SHORT)
                .show()
        }.addOnFailureListener {
            Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT)
                .show()
        }
        loadData()
    }
}