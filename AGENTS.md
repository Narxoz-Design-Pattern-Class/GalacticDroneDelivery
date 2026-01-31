# Repository Guidelines

## Project Structure & Module Organization
- `src/main/java` holds production Java sources under the `edu.narxoz.galactic` package hierarchy.
- `src/test/java` mirrors that hierarchy for JUnit Jupiter test classes.
- Dependencies and build plugins are configured in `pom.xml`; no other modules or assets exist yet, so add folders only when new resources (e.g., `resources/`, `assets/`) are needed.

## Build, Test, and Development Commands
- `mvn clean package`: compiles Java 17 sources, runs tests, and produces the JAR under `target/`.
- `mvn test`: executes the JUnit Jupiter suite; use `-Dtest=...` to limit scope when iterating.
- `mvn exec:java`: runs `edu.narxoz.galactic.Main` via the exec plugin; useful for manual verification before committing.

## Coding Style & Naming Conventions
- Follow standard Java conventions: classes/interfaces in PascalCase, methods/variables in camelCase, constants in UPPER_SNAKE_CASE.
- Use four spaces for indentation and keep line length under 100 characters.
- Keep logic in small, focused methods and prefer descriptive parameter names; avoid trailing whitespace.
- Run `mvn -q` targets through your IDE formatter or `mvn formatter:format` (if added) before pushing to maintain consistency.

## Testing Guidelines
- Tests live under `src/test/java` and rely on JUnit Jupiter (5.10.0); name them `SomethingTest` for clarity.
- Structure tests as `@Test` methods that describe the narrative (`shouldDeliverPayloadWhen...`), and keep fixtures reusable via helper methods.
- Run `mvn test` before committing and include the command output or notable failures in PR descriptions.

## Commit & Pull Request Guidelines
- Commit messages should be imperative (e.g., “Add flight-path calculator”) and mention the intent of the change. Reference issue numbers if available.
- Pull requests need a brief description, list of key changes, and confirmation of the tests run. Include screenshots or logs only when behavior or outputs change noticeably.

## Security & Configuration Tips
- Keep credentials out of the repository; use environment variables or `.gitignore`ed config files for sensitive settings.
- Update `pom.xml` carefully and document added dependencies and plugins in this guide if they affect build/testing.
