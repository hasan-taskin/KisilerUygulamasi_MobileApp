package com.example.kisileruygulamasi.data.datasource

import android.util.Log
import com.example.kisileruygulamasi.data.entity.Kisiler
import com.example.kisileruygulamasi.room.KisilerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KisilerDataSource(var kisilerDao: KisilerDao) {
    suspend fun kaydet(kisi_ad: String,kisi_tel: String){
        val yeniKisi = Kisiler(0,kisi_ad,kisi_tel)//id si otomatikk artıyor databasede o yüzden  0
        kisilerDao.kaydet(yeniKisi)
    }

    suspend fun guncelle(kisi_id:Int,kisi_ad: String,kisi_tel: String){
        val guncellenenKisi = Kisiler(kisi_id,kisi_ad,kisi_tel) //mevcut veriyi yenisiyle değiştiriyoruz
        kisilerDao.guncelle(guncellenenKisi)
    }

    suspend fun sil(kisi_id:Int){
        val silinenKisi = Kisiler(kisi_id,"","")//boş değer atayarak silme işlemi yapıyoruz
        kisilerDao.sil(silinenKisi)
    }

    suspend fun kisileriYukle() : List<Kisiler> = withContext(Dispatchers.IO) {
        return@withContext kisilerDao.kisileriYukle()
    }

    suspend fun ara(aramaKelimesi:String) : List<Kisiler> = withContext(Dispatchers.IO) {
        return@withContext kisilerDao.ara(aramaKelimesi)
    }
}