package com.dicoding.capstoneproject.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.capstoneproject.model.MemberCapstone
import com.dicoding.capstoneproject.utils.DataDummy

class MemberViewModel: ViewModel(){
    fun getMember(): List<MemberCapstone> = DataDummy.generateMemberCapstone()
}