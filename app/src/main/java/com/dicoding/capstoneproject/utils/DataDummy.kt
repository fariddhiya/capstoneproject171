package com.dicoding.capstoneproject.utils

import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.model.MemberCapstone

object DataDummy {
    fun generateMemberCapstone(): ArrayList<MemberCapstone>{
        val member = ArrayList<MemberCapstone>()
        member.add(
            MemberCapstone(
                "Benaya Caesario Perdana",
                "C0080913",
                "Cloud Computing",
                R.drawable.benaya,
            )
        )
        member.add(
            MemberCapstone(
                "Aldrich Jevon Gunawan",
                "C0080910",
                "Cloud Computing",
                R.drawable.jevon,
            )
        )
        member.add(
            MemberCapstone(
                "Farid Dhiya Ul Arif",
                "A2242171",
                "Mobile Developer",
                R.drawable.farid,
            )
        )
        member.add(
            MemberCapstone(
                "Salsa Nur Fitra",
                "A1221579",
                "Mobile Developer",
                R.drawable.salsa
            )
        )
        member.add(
            MemberCapstone(
                "Stanley Pratama",
                "M0101068",
                "Machine Learning",
                R.drawable.stanley,
            )
        )
        member.add(
            MemberCapstone(
                "Priska Natasha",
                "M0101066",
                "Machine Learning",
                R.drawable.priska,
            )
        )
        return member
    }
}