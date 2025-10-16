// StorageConnection.kt

package org.operatorfoundation.ion

import org.operatorfoundation.ion.storage.Storage

interface StorageConnection {
    fun readStorage(): Storage?
    fun writeStorage(x: Storage)
}
