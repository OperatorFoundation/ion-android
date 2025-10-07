// StorageConnection.kt

package ion

import ion.storage.Storage

interface StorageConnection {
    fun readStorage(): Storage?
    fun writeStorage(x: Storage)
}
