package com.dicoding.capstoneproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.capstoneproject.databinding.ItemMemberLayoutBinding
import com.dicoding.capstoneproject.model.MemberCapstone

class MemberAdapter(val context: Context): RecyclerView.Adapter<MemberAdapter.MemberViewHolder>(){
    private var listMember = ArrayList<MemberCapstone>()
    inner class MemberViewHolder(private val binding: ItemMemberLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(member: MemberCapstone){
            with(binding){
                tvDivCapstone.text = member.div
                tvIdCapstone.text = member.idMember.toString()
                tvNameCapstone.text = member.name
                Glide.with(context).load(member.photo).into(civMemberCapstone)
            }
        }
    }

    fun setMember(member: List<MemberCapstone>?){
        if (member == null) return
        this.listMember.clear()
        this.listMember.addAll(member)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val itemMemberLayoutBinding = ItemMemberLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemberViewHolder(itemMemberLayoutBinding)
    }

    override fun onBindViewHolder(memberViewHolder: MemberViewHolder, position: Int) {
        val member = listMember[position]
        memberViewHolder.bind(member)
    }

    override fun getItemCount(): Int {
        return listMember.size
    }
}