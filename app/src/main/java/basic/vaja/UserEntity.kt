package basic.vaja

import androidx.room.Entity
import androidx.room.PrimaryKey

// below line is for setting table name.
@Entity(tableName = "user_data")
class UserEntity(
    var name: String?, var surname: String?, var dateOfBirth: String?, var heartRate: String?,
    var so2: String?, var bodyTemperature: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}