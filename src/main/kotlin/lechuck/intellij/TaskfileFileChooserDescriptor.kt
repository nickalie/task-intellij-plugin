package lechuck.intellij

import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.fileChooser.FileElement
import com.intellij.openapi.vfs.VirtualFile

class TaskfileFileChooserDescriptor : FileChooserDescriptor(true, false, false, false, false, false) {
    init {
        title = "Select Taskfile"
    }

    override fun isFileVisible(file: VirtualFile, showHiddenFiles: Boolean): Boolean {
        if (!showHiddenFiles && FileElement.isFileHidden(file)) {
            return false
        }
        if (file.isDirectory) {
            return true
        }
        val ext = file.extension
        return if ("yaml".equals(ext, ignoreCase = true) || "yml".equals(ext, ignoreCase = true)) {
            true
        } else super.isFileVisible(file, showHiddenFiles)
    }

    override fun isFileSelectable(file: VirtualFile?): Boolean {
        if (file != null) {
            return !file.isDirectory && isFileVisible(file, true)
        }
        return false
    }
}