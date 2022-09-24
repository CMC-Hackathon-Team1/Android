package com.team1.projectteam1.presentation.home.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel: ViewModel() {
    var selectedCategory: MutableLiveData<String> = MutableLiveData("")

}