package com.orangeocean.rijksmuseum.data


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.orangeocean.rijksmuseum.service.cache.database.AppDatabase
import com.orangeocean.rijksmuseum.service.cache.database.ArtObjectDao
import com.orangeocean.rijksmuseum.service.cache.entity.ArtObjectCacheEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlantDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var artObjectDao: ArtObjectDao
    private val artObject1 = ArtObjectCacheEntity("1", "T1", "Tb1", "IU1", "AN1")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        artObjectDao = database.artObjectDao()

        // Insert plants in non-alphabetical order to test that results are sorted by name
        artObjectDao.insert(artObject1)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGet() {
        /*
        val artObjects = getValue(artObjectDao.get("AN1"))
        assertThat(artObjects.size, equalTo(1))

        // Ensure plant list is sorted by name
        assertThat(artObjects[0], equalTo(artObject1))*/
    }
}
