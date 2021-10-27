package com.example.bartrend.utils.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.sql.Blob

fun Blob.toBitmap(): Bitmap =
    BitmapFactory.decodeStream(binaryStream)