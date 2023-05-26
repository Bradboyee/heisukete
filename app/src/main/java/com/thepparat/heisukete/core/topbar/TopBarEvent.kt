package com.thepparat.heisukete.core.topbar

sealed class TopBarEvent {
    data class ShowMessage(val message: String) : TopBarEvent()
}