package com.chertilov.core_api.mediators

import com.chertilov.core_api.database.DatabaseProvider
import com.chertilov.core_api.network.NetworkProvider

interface ProvidersFacade : AppProvider, MediatorProvider, DatabaseProvider, NetworkProvider