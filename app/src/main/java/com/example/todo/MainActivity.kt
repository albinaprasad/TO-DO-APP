package com.example.todo

import android.content.DialogInterface
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var iltemlist= ArrayList<String>()
    var fileSaver= fileSaver()

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        var view = mainBinding.root


        iltemlist=fileSaver.readData(this)

        var arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,iltemlist)

        mainBinding.listView.adapter = arrayAdapter

        mainBinding.button.setOnClickListener {

            var taskData=mainBinding.editTextText2.text.toString()
          iltemlist.add(taskData)

            mainBinding.editTextText2.setText("")

            fileSaver.writeData(iltemlist,applicationContext)
            arrayAdapter.notifyDataSetChanged()
        }
        mainBinding.listView.setOnItemClickListener{adapterView,view,position,l->

            var alert= AlertDialog.Builder(this)

            alert.setMessage("Do you want to delete this Task?")
            alert.setCancelable(false)
            alert.setNegativeButton ("NO", DialogInterface.OnClickListener{
                dialogueInterface,i-> dialogueInterface.cancel()
            })
            alert.setPositiveButton ("Yes", DialogInterface.OnClickListener{

                dialogueInterface,i->
                iltemlist.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                fileSaver.writeData(iltemlist,applicationContext)
                dialogueInterface.cancel()

            })
            alert.create().show()
        }


        setContentView(view)


    }
}