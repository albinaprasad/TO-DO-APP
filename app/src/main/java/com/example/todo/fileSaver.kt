package com.example.todo

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutput
import java.io.ObjectOutputStream

class fileSaver {

    val filename = "dataSaved.dat"

    fun writeData(item: ArrayList<String>, context: Context) {

        var dataWriter: FileOutputStream
        var objectWriter: ObjectOutputStream

        dataWriter = context.openFileOutput(filename, Context.MODE_PRIVATE)
        objectWriter = ObjectOutputStream(dataWriter)

        objectWriter.writeObject(item)
        objectWriter.close()

    }

    fun readData(context: Context): ArrayList<String> {
        var data: ArrayList<String>

        try {
            var dataReader: FileInputStream
            dataReader = context.openFileInput(filename)

            var objectReader: ObjectInputStream = ObjectInputStream(dataReader)
            data = objectReader.readObject() as ArrayList<String>


        } catch (e: FileNotFoundException) {
            data = ArrayList<String>()
        }
        return data

    }
}

