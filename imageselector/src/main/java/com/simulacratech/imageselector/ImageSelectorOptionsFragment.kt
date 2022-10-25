package com.simulacratech.imageselector

import android.content.ActivityNotFoundException
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.PopupWindow
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.simulacratech.base.utility.showAlert
import com.simulacratech.base.utility.showLog
import com.simulacratech.imageselector.databinding.FragmentImageSelectorOptionsBinding
import java.io.File
import java.util.*

class ImageSelectorOptionsFragment : Fragment() {

    private var imageUri: Uri? = null
    private var _binding: FragmentImageSelectorOptionsBinding? = null
    private val binding get() = _binding!!
    private var list = ArrayList<Uri>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageSelectorOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewLayout.visibility = View.GONE

        binding.gallery.setOnClickListener {
            openGalleryForImage()
        }
        binding.camera.setOnClickListener {
            openCameraForImage()
        }

        binding.btHazardImageAddMore.setOnClickListener {
            val popUpView: View = LayoutInflater.from(activity).inflate(R.layout.popup_view, null)
            val popupWindow = PopupWindow(
                popUpView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
            )
            val btnCamera: Button = popUpView.findViewById(R.id.button_popup)
            val btnDismiss: Button = popUpView.findViewById(R.id.button_dismiss)
            val btnGalley: Button = popUpView.findViewById(R.id.button_gallery)

            popupWindow.showAtLocation(popUpView, Gravity.BOTTOM, 0, 0)

            popupWindow.animationStyle = R.style.popup_window_animation
            popupWindow.isFocusable = true
            popupWindow.isOutsideTouchable = false

            btnCamera.setOnClickListener {
                openCameraForImage()
                popupWindow.dismiss()
            }
            btnDismiss.setOnClickListener {
                popupWindow.dismiss()
            }
            btnGalley.setOnClickListener {
                openGalleryForImage()
                popupWindow.dismiss()
            }
        }
    }

    private fun openCameraForImage() {
        imageUri = createImageUri()!!
        try {
            takePicture.launch(imageUri)
        } catch (aNFE: ActivityNotFoundException) {
            aNFE.showLog()
            requireActivity().showAlert(getString(R.string.camera_app_not_available))
        }
    }

    private fun openGalleryForImage() {
        try {
            getGalleryResult.launch("image/*")
        } catch (aNFE: ActivityNotFoundException) {
            aNFE.showLog()
            requireActivity().showAlert(getString(R.string.gallery_app_not_available))
        }
    }

    private val getGalleryResult = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        if (it != null) {
            list.add(it)
            callRecycler()
        }
    }
    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture())
    { success: Boolean ->
        if (success) {
            list.add(imageUri!!)
            callRecycler()
        }
    }

    private fun createImageUri(): Uri? {
        val image =
            File(activity?.applicationContext?.filesDir, UUID.randomUUID().toString() + ".png")
        return FileProvider.getUriForFile(
            requireActivity().applicationContext, "com.aidash.ivms.fileProvider",
            image
        )
    }

    private fun callRecycler() {
        binding.mainLayout.visibility = View.GONE
        binding.recyclerViewLayout.visibility = View.VISIBLE
        binding.rvHazardImageRecycler.layoutManager = LinearLayoutManager(context)
        binding.rvHazardImageRecycler.setHasFixedSize(true)
        class RemovingClass : ImageInterface {
            override fun onClick(position: Int) {
                list.removeAt(position)
                if (list.isEmpty()) {
                    binding.mainLayout.visibility = View.VISIBLE
                    binding.recyclerViewLayout.visibility = View.GONE
                } else {
                    callRecycler()
                }
            }
        }

        val imageInterface: ImageInterface = RemovingClass()
        binding.rvHazardImageRecycler.adapter = ImageRecyclerAdaptor(list, imageInterface)
    }

    fun getSelectedImages() = list
}