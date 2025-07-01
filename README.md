## Running the MkDocs server
You will need a JDK (17+) and a Python (3.10+) installation.

### Installing the Python dependencies
Run `pip install -r requirements.txt`

### Running MkDocs
You can run the server by running `mvn compile exec:java -Dexec.mainClass=io.github.freya022.mkdocs.MkDocsLauncherKt -Dexec.args='mkdocs serve'`,
either in your terminal (if you have Maven installed),
or in your IDE (press `CTRL` twice in IntelliJ to open the command bar).

If you wish to contribute to the link server, you can instead run the main method of `LinkServer` from your IDE, 
and *then* start MkDocs with `mkdocs serve`.

### Special macros

- `{{ wiki_stub }}` creates an admonition about wiki stubs, which are here for feature discovery, but have little to no content

#### Documentation macros
- `[[ClassRef]]` creates a link to the documentation of the referenced class
- `[[ClassRef#functionRef]]` creates a link to the documentation of the referenced function
- `[[ClassRef#propertyRef]]` creates a link to the documentation of the referenced property
- `[[functionRef]]` creates a link to the documentation of the referenced top-level function (extension functions are top level)
- `[[propertyRef]]` creates a link to the documentation of the referenced top-level property (extension properties are top level)

## Running the wiki bot

### Additional requirements

* A PostgreSQL database
* Your bot token

### Configuration
Duplicate the `config-template` folder as `dev-config`,
and edit the `config.json`, with your bot token, prefixes, owner ID and the database details.

You can then run the `Main` class.
