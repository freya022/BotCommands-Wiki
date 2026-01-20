# Application commands

!!! info "If you wish to handle application commands yourself, you can disable them by disabling [[BApplicationConfigBuilder#enable]]."

The framework supports all application commands:

- Slash commands (in the chat input), including autocomplete
- User context commands (Right-clicking on a user)
- Message context commands (Right-clicking on a message)

Registration of all applications commands is managed automatically, they are added/deleted/updated as your code changes.

## Commands caching

By default, the commands are cached locally to avoid unnecessary updates,
however, you might want to configure the cache in some cases.

??? tip "Logging command changes"

    You can optionally get more info on what changed in your application commands,
    by enabling the `TRACE` logs on `io.github.freya022.botcommands.internal.commands.application.diff.DiffLogger`,
    or any package it is in.

### In development environments

If you develop on multiple machines, your local cache may differ from the commands Discord has,
which can cause updates to not be triggered, in which case you can:

- Disable the cache, using [[BApplicationConfigBuilder#disableCache]]
- Check commands online, by setting the `checkOnline` property of your command cache. (by default, [`fileCache`][[BApplicationConfigBuilder#fileCache]])

### When running in a container

As containers are ephemeral, files in them are typically not retained, which breaks the cache.

To fix that, you can either:

- Use an existing database as the cache, using [[BApplicationConfigBuilder#databaseCache]]
- Or, use a different path, one that's in a persistent storage (like a volume or a bind mount), using [[BApplicationConfigBuilder#fileCache]]
