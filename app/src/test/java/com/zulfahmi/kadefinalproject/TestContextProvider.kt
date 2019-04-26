package com.zulfahmi.kadefinalproject

import com.zulfahmi.kadefinalproject.util.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider: CoroutineContextProvider() {
    override val main: CoroutineContext = Dispatchers.Unconfined
}