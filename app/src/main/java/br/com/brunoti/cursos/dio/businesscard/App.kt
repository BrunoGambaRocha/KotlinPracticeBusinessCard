package br.com.brunoti.cursos.dio.businesscard

import android.app.Application
import br.com.brunoti.cursos.dio.businesscard.data.AppDatabase
import br.com.brunoti.cursos.dio.businesscard.data.BusinessCardRepository

class App : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}