package com.example.dagger_hilt_mvvm_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dagger_hilt_mvvm_room.databinding.ActivityMainBinding
import com.example.dagger_hilt_mvvm_room.db.UserEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel

    //view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val userEntity = UserEntity(name = binding.enterDescriptionEditText.text.toString())
            viewModel.inserRecord(userEntity)
            binding.enterDescriptionEditText.setText("")

        }
        initVM()
    }

    private fun initVM(){
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getRecordObserver().observe(this, object : Observer<List<UserEntity>>{
            override fun onChanged(t: List<UserEntity>?) {
                binding.resultTextView.setText("")
                t?.forEach {
                    binding.resultTextView.append(it.name +"\n")
                }
            }
        })
    }

}