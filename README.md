## Running the MkDocs server
You will need a JDK (25+) and a Python (3.10+) installation.

1. Install [uv](https://docs.astral.sh/uv/getting-started/installation/)
2. Install the dependencies with `uv sync`
3. Make sure you run the development link server separately from Gradle, in IntelliJ, go to `File | Settings | Advanced Settings` and disable "Run using Gradle"
4. Go to [LinkServerMain](link-server/src/main/kotlin/dev/freya02/link/server/LinkServerMain.kt) and run it
5. Run `uv run mkdocs serve --livereload`

## Special macros

- `{{ wiki_stub }}` creates an admonition about wiki stubs, which are here for feature discovery, but have little to no content

## Documentation macros
- `[[ClassRef]]` creates a link to the documentation of the referenced class
- `[[ClassRef#functionRef]]` creates a link to the documentation of the referenced function
- `[[ClassRef#propertyRef]]` creates a link to the documentation of the referenced property
- `[[functionRef]]` creates a link to the documentation of the referenced top-level function (extension functions are top level)
- `[[propertyRef]]` creates a link to the documentation of the referenced top-level property (extension properties are top level)

Class references use simple names (only the class's name, no package)
Member references only use the member's name (such as `BotCommands#create`)

## Running the wiki bot

### Additional requirements

* A PostgreSQL database
* Your bot token

### Configuration
Duplicate the `config-template` folder as `dev-config`,
and edit the `config.json`, with your bot token, prefixes, owner ID and the database details.

You can then run the `Main` class.
