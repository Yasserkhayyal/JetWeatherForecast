package com.bawp.jetweatherforecast.data.model.locations

import com.google.gson.annotations.SerializedName

data class GeoLocationItem(
    val country: String? = null, // GB
    val lat: Double? = null, // 51.5073219
    @SerializedName("local_names")
    val localNames: LocalNames? = null,
    val lon: Double? = null, // -0.1276474
    val name: String? = null, // London
    val state: String? = null // England
) {
    data class LocalNames(
        val ab: String? = null, // Лондон
        val af: String? = null, // Londen
        val am: String? = null, // ለንደን
        val an: String? = null, // Londres
        val ar: String? = null, // لندن
        val ascii: String? = null, // London
        val av: String? = null, // Лондон
        val ay: String? = null, // London
        val az: String? = null, // London
        val ba: String? = null, // Лондон
        val be: String? = null, // Лондан
        val bg: String? = null, // Лондон
        val bh: String? = null, // लंदन
        val bi: String? = null, // London
        val bm: String? = null, // London
        val bn: String? = null, // লন্ডন
        val bo: String? = null, // ལོན་ཊོན།
        val br: String? = null, // Londrez
        val bs: String? = null, // London
        val ca: String? = null, // Londres
        val ce: String? = null, // Лондон
        val co: String? = null, // Londra
        val cr: String? = null, // ᓬᐊᐣᑕᐣ
        val cs: String? = null, // Londýn
        val cu: String? = null, // Лондонъ
        val cv: String? = null, // Лондон
        val cy: String? = null, // Llundain
        val da: String? = null, // London
        val de: String? = null, // London
        val ee: String? = null, // London
        val el: String? = null, // Λονδίνο
        val en: String? = null, // London
        val eo: String? = null, // Londono
        val es: String? = null, // Londres
        val et: String? = null, // London
        val eu: String? = null, // Londres
        val fa: String? = null, // لندن
        val featureName: String? = null, // London
        val ff: String? = null, // London
        val fi: String? = null, // Lontoo
        val fj: String? = null, // Lodoni
        val fo: String? = null, // London
        val fr: String? = null, // Londres
        val fy: String? = null, // Londen
        val ga: String? = null, // Londain
        val gd: String? = null, // Lunnainn
        val gl: String? = null, // Londres
        val gn: String? = null, // Lóndyre
        val gu: String? = null, // લંડન
        val gv: String? = null, // Lunnin
        val ha: String? = null, // Landan
        val he: String? = null, // לונדון
        val hi: String? = null, // लंदन
        val hr: String? = null, // London
        val ht: String? = null, // Lonn
        val hu: String? = null, // London
        val hy: String? = null, // Լոնդոն
        val ia: String? = null, // London
        val id: String? = null, // London
        val ie: String? = null, // London
        val ig: String? = null, // London
        val io: String? = null, // London
        val isX: String? = null, // London
        val `it`: String? = null, // Londra
        val iu: String? = null, // ᓚᓐᑕᓐ
        val ja: String? = null, // ロンドン
        val jv: String? = null, // London
        val ka: String? = null, // ლონდონი
        val kk: String? = null, // Лондон
        val kl: String? = null, // London
        val km: String? = null, // ឡុងដ៍
        val kn: String? = null, // ಲಂಡನ್
        val ko: String? = null, // 런던
        val ku: String? = null, // London
        val kv: String? = null, // Лондон
        val kw: String? = null, // Loundres
        val ky: String? = null, // Лондон
        val lb: String? = null, // London
        val li: String? = null, // Londe
        val ln: String? = null, // Lóndɛlɛ
        val lo: String? = null, // ລອນດອນ
        val lt: String? = null, // Londonas
        val lv: String? = null, // Londona
        val mg: String? = null, // Lôndôna
        val mi: String? = null, // Rānana
        val mk: String? = null, // Лондон
        val ml: String? = null, // ലണ്ടൻ
        val mn: String? = null, // Лондон
        val mr: String? = null, // लंडन
        val ms: String? = null, // London
        val mt: String? = null, // Londra
        val my: String? = null, // လန်ဒန်မြို့
        val na: String? = null, // London
        val ne: String? = null, // लन्डन
        val nl: String? = null, // Londen
        val nn: String? = null, // London
        val no: String? = null, // London
        val nv: String? = null, // Tooh Dineʼé Bikin Haalʼá
        val ny: String? = null, // London
        val oc: String? = null, // Londres
        val oj: String? = null, // Baketigweyaang
        val om: String? = null, // Landan
        val or: String? = null, // ଲଣ୍ଡନ
        val os: String? = null, // Лондон
        val pa: String? = null, // ਲੰਡਨ
        val pl: String? = null, // Londyn
        val ps: String? = null, // لندن
        val pt: String? = null, // Londres
        val qu: String? = null, // London
        val rm: String? = null, // Londra
        val ro: String? = null, // Londra
        val ru: String? = null, // Лондон
        val sa: String? = null, // लन्डन्
        val sc: String? = null, // Londra
        val sd: String? = null, // لنڊن
        val se: String? = null, // London
        val sh: String? = null, // London
        val si: String? = null, // ලන්ඩන්
        val sk: String? = null, // Londýn
        val sl: String? = null, // London
        val sm: String? = null, // Lonetona
        val sn: String? = null, // London
        val so: String? = null, // London
        val sq: String? = null, // Londra
        val sr: String? = null, // Лондон
        val st: String? = null, // London
        val su: String? = null, // London
        val sv: String? = null, // London
        val sw: String? = null, // London
        val ta: String? = null, // இலண்டன்
        val te: String? = null, // లండన్
        val tg: String? = null, // Лондон
        val th: String? = null, // ลอนดอน
        val tk: String? = null, // London
        val tl: String? = null, // Londres
        val to: String? = null, // Lonitoni
        val tr: String? = null, // Londra
        val tt: String? = null, // Лондон
        val tw: String? = null, // London
        val ug: String? = null, // لوندۇن
        val uk: String? = null, // Лондон
        val ur: String? = null, // علاقہ لندن
        val uz: String? = null, // London
        val vi: String? = null, // Luân Đôn
        val vo: String? = null, // London
        val wa: String? = null, // Londe
        val wo: String? = null, // Londar
        val yi: String? = null, // לאנדאן
        val yo: String? = null, // Lọndọnu
        val zh: String? = null, // 伦敦
        val zu: String? = null // ILondon
    )
}