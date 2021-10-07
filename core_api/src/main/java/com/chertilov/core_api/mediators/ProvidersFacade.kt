package com.chertilov.core_api.mediators

import com.chertilov.core_api.database.DatabaseProvider

interface ProvidersFacade: AppProvider, MediatorProvider, DatabaseProvider