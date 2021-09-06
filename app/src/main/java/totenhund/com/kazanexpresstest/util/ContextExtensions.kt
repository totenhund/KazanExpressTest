package totenhund.com.kazanexpresstest.util

import android.content.Context
import android.net.ConnectivityManager

fun Context.isConnected(): Boolean{
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo?.isConnectedOrConnecting == true
}