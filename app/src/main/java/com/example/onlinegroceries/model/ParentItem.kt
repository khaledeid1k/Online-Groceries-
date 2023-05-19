package com.example.onlinegroceries.model

import com.example.onlinegroceries.base.BaseInteractionInter
import com.example.onlinegroceries.ui.home.ItemStandInteractionInter
import com.example.onlinegroceries.ui.home.ItemSleepInteractionInter

sealed class ParentItem(val pTag  :Tag?,listener: BaseInteractionInter? ) {
    object HEADER : ParentItem(null,null)
    data class MEN_CLOTHING(
        val menTag:Tag
        , val listener: ItemStandInteractionInter
    ) : ParentItem(menTag,listener)
    data class ELECTRONICS(val  electronicsTag  :Tag,
                           val listener: ItemStandInteractionInter
    )
        : ParentItem(electronicsTag,listener)
    data class JEWELRIES(val JewelriesTag  :Tag,
                         val listener: ItemSleepInteractionInter
    )
        : ParentItem(JewelriesTag,listener)
    data class WOMEN_CLOTHING (val WomenTag  :Tag,
                               val listener: ItemSleepInteractionInter
    )
        : ParentItem(WomenTag,listener)
}
