package com.krak.krakowautobusy.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.DataInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream






class Database(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    companion object {

        private const  val NAME_DATABASE_IN_ASSETS_FOLDER="manu"
        private const val NAME_DATABASE_OUTPUT="busDatabase"
        private const val KILO_BYTE=1024
        private const val ZERO_SIZE=0
        private const val COPY_WRITE_POSITION=0
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "busDatabase"


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

        private fun isDatabaseExist(context: Context, dbName: String): Boolean {
            val dbFile = context.getDatabasePath(dbName)
            return dbFile.exists()
        }

        private  fun importdb( context: Context) {

            try {
                if(!isDatabaseExist(context,NAME_DATABASE_OUTPUT)){
                    copyFileDatabaseFromAssetsToDatabaseFolder(context)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private var instance: Database? = null

        fun getInstance(context: Context): Database {



            if (instance == null) {
                importdb(context)
                instance = Database(context)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}