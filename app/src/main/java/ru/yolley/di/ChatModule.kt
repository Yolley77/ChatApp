package ru.yolley.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yolley.data.ChatRepository
import ru.yolley.data.IChatRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatModule {

    @Binds
    abstract fun bindChatRepository(impl: ChatRepository): IChatRepository

}