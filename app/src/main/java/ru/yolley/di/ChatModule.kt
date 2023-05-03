package ru.yolley.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yolley.data.ChatRepository
import ru.yolley.data.IChatRepository
import ru.yolley.data.local.IDataStoreFacade
import ru.yolley.data.local.DataStoreFacade
import ru.yolley.data.remote.IWebSocketHandler
import ru.yolley.data.remote.WebSocketHandler
import ru.yolley.domain.state.ChatStateHolder
import ru.yolley.domain.state.IChatStateHolder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ChatModule {

    @Binds
    @Singleton
    abstract fun bindChatRepository(impl: ChatRepository): IChatRepository

    @Binds
    @Singleton
    abstract fun bindWebSocketHandler(impl: WebSocketHandler): IWebSocketHandler

    @Binds
    @Singleton
    abstract fun bindDataStoreFacade(impl: DataStoreFacade): IDataStoreFacade

    @Binds
    @Singleton
    abstract fun bindChatStateHolder(impl: ChatStateHolder): IChatStateHolder

}