package com.example.krakowautobusy.database

import android.content.Context
import android.util.Log
import java.io.DataInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class LoadDatabase(context: Context) {
    val NAME_DATABASE_IN_ASSETS_FOLDER="manu"
    val NAME_DATABASE_OUTPUT="busDatabase"
    val KILO_BYTE=1024
    val ZERO_SIZE=0
    val COPY_WRITE_POSITION=0


    init {
        importdb(context)
    }
    private fun copyFileDatabaseFromAssetsToDatabaseFolder(context: Context){

        val mInputStream: InputStream = DataInputStream(context.assets.open(NAME_DATABASE_IN_ASSETS_FOLDER))
        val outFileName: String = context.getDatabasePath(
            NAME_DATABASE_OUTPUT
        ).absolutePath

        val mOutputStream: OutputStream = FileOutputStream(outFileName)
        val buffer = ByteArray(KILO_BYTE)
        var length: Int
        while (mInputStream.read(buffer).also { length = it } > ZERO_SIZE) {
            mOutputStream.write(buffer, COPY_WRITE_POSITION, length)
        }
        mOutputStream.flush()
        mOutputStream.close()
        mInputStream.close()

    }

    private fun doesDatabaseExist(context: Context, dbName: String): Boolean {
        val dbFile = context.getDatabasePath(dbName)
        return dbFile.exists()
    }

  private  fun importdb( context: Context) {

        try {
            if(!doesDatabaseExist(context,NAME_DATABASE_OUTPUT)){
                copyFileDatabaseFromAssetsToDatabaseFolder(context)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}