package com.young.businessmine

import com.billy.cc.core.component.CC
import com.billy.cc.core.component.IComponent

class ComponentMine :IComponent {
    override fun onCall(cc: CC?): Boolean {
        return false
    }

    override fun getName(): String {
        return "mine"
    }
}