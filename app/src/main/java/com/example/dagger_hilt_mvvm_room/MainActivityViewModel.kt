package com.example.dagger_hilt_mvvm_room

import android.service.autofill.UserData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger_hilt_mvvm_room.db.RoomRepository
import com.example.dagger_hilt_mvvm_room.db.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RoomRepository): ViewModel(){
    lateinit var userData: MutableLiveData<List<UserEntity>>

    init {
        userData = MutableLiveData()
        loadRecords()
    }

    fun getRecordObserver(): MutableLiveData<List<UserEntity>> {
        return userData
    }

    fun loadRecords(){
        val list = repository.getRecords()
        userData.postValue(list)
    }

    fun  inserRecord(userEntity: UserEntity){
        repository.insertRecord(userEntity)
        loadRecords()
    }
}