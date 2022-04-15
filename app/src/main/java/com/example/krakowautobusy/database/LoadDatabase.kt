package com.example.krakowautobusy.database

import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import com.example.krakowautobusy.R
import java.io.*
import java.security.AccessController.getContext

class LoadDatabase {
    public fun importdb(db_path: String, context: Context) {
        try {
            val file = File(context.getApplicationInfo().dataDir + "/databases/" + db_path)
            Log.e(
                "baza",
                "sciezka :" + context.getApplicationInfo().dataDir + "/databases/" + db_path
            )
            //  Log.e("baza","sciezka2:"+context.filesDir)
            context.assets.open("manu")
            val mInputStream: InputStream = DataInputStream(context.assets.open("manu"))

            val outFileName: String = context.getDatabasePath(
                "busDatabase"
            ).getAbsolutePath()
            val mOutputStream: OutputStream = FileOutputStream(outFileName)
            val buffer = ByteArray(1024)
            var length: Int
            while (mInputStream.read(buffer).also { length = it } > 0) {
                mOutputStream.write(buffer, 0, length)
            }
            mOutputStream.flush()
            Log.e("baza", "Ladowanie")
            mOutputStream.close()
            mInputStream.close()
            // CustomMessage(getActivity(), "Database replaced sucessfully")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("baza", "error")
            //CustomLog.showLogD("WORKING_STOP", e.message)
        }
    }
}