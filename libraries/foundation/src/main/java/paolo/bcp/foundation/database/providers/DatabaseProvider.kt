package paolo.bcp.foundation.database.providers

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import paolo.bcp.foundation.database.AppDatabase


class DatabaseProvider(
    val context: Context
) {

    private var database: AppDatabase? = null

    fun getInstance(): AppDatabase =
        database ?: synchronized(this) {
            database ?: buildDatabase().also { database = it }
        }

    private fun buildDatabase(): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.NAME)
            .addMigrations(
                migration1to2
            )
            .createFromAsset("database/initial_bcp_challenge_db")
            .build()

    private val migration1to2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE `currency` ADD COLUMN `abbreviation` TEXT NOT NULL DEFAULT ''")
            database.execSQL("UPDATE `currency` SET `abbreviation` = 'SOL' WHERE id = 1")
            database.execSQL("UPDATE `currency` SET `abbreviation` = 'USD' WHERE id = 2")
            database.execSQL("UPDATE `currency` SET `abbreviation` = 'JPY' WHERE id = 3")
            database.execSQL("UPDATE `currency` SET `abbreviation` = 'CAD' WHERE id = 4")
        }
    }
}