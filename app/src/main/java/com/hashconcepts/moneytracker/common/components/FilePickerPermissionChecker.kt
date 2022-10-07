package com.hashconcepts.moneytracker.common.components

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.common.Constants
import timber.log.Timber
import java.io.File

/**
 * @created 07/10/2022 - 10:46 AM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FilePickerPermissionChecker(
    filePickerOption: String,
    onFilePickerLaunchResult: (Uri?) -> Unit,
) {
    val storagePermission = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    val cameraPermission = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val showPermissionRationale = remember { mutableStateOf(ShowRationale()) }
    val context = LocalContext.current
    var imageUri: Uri? = null


    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            run {
                onFilePickerLaunchResult(uri)
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            run {
                if (success) {
                    onFilePickerLaunchResult(imageUri)
                }
            }
        }
    )

    val documentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree(),
        onResult = { documentUri ->
            run {
                documentUri?.let {
                    onFilePickerLaunchResult(it)
                }
            }
        }
    )

    when(filePickerOption) {
        Constants.FILE_PICKER_OPTION_CAMERA -> {
            if (cameraPermission.status.isGranted) {
                val uri = ComposeFileProvider.getImageUri(context)
                imageUri = uri

                SideEffect {
                    cameraLauncher.launch(uri)
                }
            } else if (cameraPermission.status.shouldShowRationale) {
                showPermissionRationale.value = showPermissionRationale.value.copy(
                    showDialog = true,
                    message = "Money Tracker Requires this Camera permission to access your phones Camera.",
                    icon = R.drawable.ic_camera,
                    permission = Constants.FILE_PICKER_OPTION_CAMERA
                )
            } else {
                SideEffect {
                    cameraPermission.launchPermissionRequest()
                }
            }
        }
        Constants.FILE_PICKER_OPTION_GALLERY -> {
            if (storagePermission.status.isGranted) {
                SideEffect {
                    galleryLauncher.launch("image/*")
                }
            } else if (storagePermission.status.shouldShowRationale) {
                showPermissionRationale.value = showPermissionRationale.value.copy(
                    showDialog = true,
                    message = "Money Tracker Requires this Storage permission to access images in your phones Gallery.",
                    icon = R.drawable.ic_gallery,
                    permission = Constants.FILE_PICKER_OPTION_GALLERY
                )
            } else {
                SideEffect {
                    storagePermission.launchPermissionRequest()
                }
            }
        }
        Constants.FILE_PICKER_OPTION_DOCUMENT -> {
            if (storagePermission.status.isGranted) {
                SideEffect {
                    documentLauncher.launch(null)
                }
            } else if (storagePermission.status.shouldShowRationale) {
                showPermissionRationale.value = showPermissionRationale.value.copy(
                    showDialog = true,
                    message = "Money Tracker Requires this Storage permission to access document in your phones Gallery.",
                    icon = R.drawable.ic_file,
                    permission = Constants.FILE_PICKER_OPTION_DOCUMENT
                )
            } else {
                SideEffect {
                    storagePermission.launchPermissionRequest()
                }
            }
        }
    }

    if (showPermissionRationale.value.showDialog) {
        PermissionRationaleDialog(
            message = showPermissionRationale.value.message,
            icon = showPermissionRationale.value.icon,
            onRequestPermission = {
                showPermissionRationale.value =
                    showPermissionRationale.value.copy(showDialog = false)
                when (showPermissionRationale.value.permission) {
                    Constants.FILE_PICKER_OPTION_GALLERY,
                    Constants.FILE_PICKER_OPTION_DOCUMENT -> storagePermission.launchPermissionRequest()
                    Constants.FILE_PICKER_OPTION_CAMERA -> cameraPermission.launchPermissionRequest()
                }
            },
            onDismissRequest = {
                showPermissionRationale.value =
                    showPermissionRationale.value.copy(showDialog = false)
            }
        )
    }
}

class ComposeFileProvider : FileProvider(
    R.xml.file_paths
) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory,
            )
            val authority = context.packageName + ".fileProvider"
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}