package com.anthony.tddpractice.login.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext


@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestExtension: BeforeAllCallback, AfterAllCallback {
    override fun beforeAll(context: ExtensionContext?) {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    override fun afterAll(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}