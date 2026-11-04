package com.example.lab12_maps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import androidx.compose.ui.platform.LocalContext


@Composable
fun MapScreen() {
    val arequipaLocation = LatLng(-16.4040102, -71.559611) // Arequipa, Perú
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(arequipaLocation, 12f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Añadir GoogleMap al layout
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Añadir marcador en Arequipa
            Marker(
                state = rememberMarkerState(position = arequipaLocation),
                icon = bitmapDescriptorFromVector(context, R.drawable.gato),

                title = "Arequipa, Perú"
            )
        }
    }

}
fun bitmapDescriptorFromVector(context: Context, resId: Int): BitmapDescriptor {
    val drawable = ContextCompat.getDrawable(context, resId)!!
    val width = drawable.intrinsicWidth
    val height = drawable.intrinsicHeight
    drawable.setBounds(0, 0, width, height)
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}