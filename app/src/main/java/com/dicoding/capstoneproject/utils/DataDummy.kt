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

//object DataDummy {
//
//    fun generateDummyReport(): List<ReportEntity>{
//        val report = ArrayList<ReportEntity>()
//
//        report.add(
//            ReportEntity(
//                "r1",
//                "Parkir liar mamakan bahu jalan orang lain",
//                "124K",
//                "10",
//                "2 jam yang lalu",
//                "Menunggu"
//            )
//        )
//
//        report.add(
//            ReportEntity(
//                "r2",
//                "Jalan berlubang di jl Rawa Sawah.",
//                "124K",
//                "10",
//                "2 jam yang lalu",
//                "Koordinasi"
//            )
//        )
//
//        report.add(
//            ReportEntity(
//                "r3",
//                "Lampu penerangan jalan tidak menyala di jalan Kedoya Duri Raya.",
//                "124K",
//                "10",
//                "2 jam yang lalu",
//                "Koordinasi"
//            )
//        )
//
//        report.add(
//            ReportEntity(
//                "r4",
//                "Sampah menumpuk di dekat TK Ramai",
//                "124K",
//                "10",
//                "2 jam yang lalu",
//                "Koordinasi"
//            )
//        )
//
//        report.add(
//            ReportEntity(
//                "r5",
//                "Pohon menutupi lampu jalan jadi gelap. Mohon bantuan agar dapat dirapikan supaya tidak menghalangi sinar lampu jalan.",
//                "124K",
//                "10",
//                "2 jam yang lalu",
//                "Koordinasi"
//            )
//        )
//        report.add(
//            ReportEntity(
//                "r6",
//                "Tolong diaspal jalur pejalan kaki yang rusak.",
//                "124K",
//                "10",
//                "2 jam yang lalu",
//                "Koordinasi"
//            )
//        )
//        report.add(
//            ReportEntity(
//                "r7",
//                "Saluran mampet tidak mengalir",
//                "124K",
//                "10",
//                "2 jam yang lalu",
//                "Selesai"
//            )
//        )
//        report.add(
//            ReportEntity(
//                "r8",
//                "Sampah menyangkut di atas tembok.",
//                "124K",
//                "10",
//                "2 jam yang lalu",
//                "Selesai"
//            )
//        )
//        return report
//    }
//}
