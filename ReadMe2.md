
## <a name="commits"></a> Git Commit Guidelines

We have very precise rules over how our git commit messages can be formatted.  This leads to **more
readable messages** that are easy to follow when looking through the **project history**.  But also,
we use the git commit messages to **generate the AngularJS change log**.

The commit message formatting can be added using a typical git workflow or through the use of a CLI
wizard ([Commitizen](https://github.com/commitizen/cz-cli)). To use the wizard, run `yarn run commit`
in your terminal after staging your changes in git.

### Commit Message Format
Each commit message consists of a **header**, a **body** and a **footer**.  The header has a special
format that includes a **type**, a **scope** and a **subject**:

```
<type>(<scope>): <subject>
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

The **header** is mandatory and the **scope** of the header is optional.

Any line of the commit message cannot be longer than 100 characters! This allows the message to be easier
to read on GitHub as well as in various git tools.

### Revert
If the commit reverts a previous commit, it should begin with `revert: `, followed by the header
of the reverted commit.
In the body it should say: `This reverts commit <hash>.`, where the hash is the SHA of the commit
being reverted.

### Type
Must be one of the following:

* **feat**: A new feature
* **fix**: A bug fix
* **docs**: Documentation only changes
* **style**: Changes that do not affect the meaning of the code (white-space, formatting, missing
  semi-colons, etc)
* **refactor**: A code change that neither fixes a bug nor adds a feature
* **perf**: A code change that improves performance
* **test**: Adding missing or correcting existing tests
* **chore**: Changes to the build process or auxiliary tools and libraries such as documentation
  generation

### Scope
The scope could be anything specifying place of the commit change. For example `$location`,
`$browser`, `$compile`, `$rootScope`, `ngHref`, `ngClick`, `ngView`, etc...

You can use `*` when the change affects more than a single scope.

### Subject
The subject contains succinct description of the change:

* use the imperative, present tense: "change" not "changed" nor "changes"
* don't capitalize first letter
* no dot (.) at the end

### Body
Just as in the **subject**, use the imperative, present tense: "change" not "changed" nor "changes".
The body should include the motivation for the change and contrast this with previous behavior.

### Footer
The footer should contain any information about **Breaking Changes** and is also the place to
[reference GitHub issues that this commit closes][closing-issues].

**Breaking Changes** should start with the word `BREAKING CHANGE:` with a space or two newlines.
The rest of the commit message is then used for this.

A detailed explanation can be found in this [document][commit-message-format].

## <a name="documentation"></a> Writing Documentation

The AngularJS project uses a form of [jsdoc](http://usejsdoc.org/) called ngdoc for all of its code
documentation.

This means that all the docs are stored inline in the source code and so are kept in sync as it
changes.

There is also extra content (the developer guide, error pages, the tutorial,
and misceallenous pages) that live inside the AngularJS repository as markdown files.

This means that since we generate the documentation from the source code, we can easily provide
version-specific documentation by simply checking out a version of AngularJS and running the build.

Extracting the source code documentation, processing and building the docs is handled by the
documentation generation tool [Dgeni][dgeni].

### Building and viewing the docs locally
The docs can be built from scratch using grunt:

```shell
yarn grunt docs
```

This defers the doc-building task to `gulp`.

Note that the docs app is using the local build files to run. This means you might first have to run
the build:

```shell
yarn grunt build
```

(This is also necessary if you are making changes to minErrors).

To view the docs, see [Running a Local Development Web Server](#local-server).

### Writing jsdoc
The ngdoc utility has basic support for many of the standard jsdoc directives.  But in particular it
is interested in the following block tags:

* `@name name` - the name of the ngdoc document
* `@param {type} name description` - describes a parameter of a function
* `@returns {type} description` - describes what a function returns
* `@requires` - normally indicates that a JavaScript module is required; in an Angular service it is
  used to describe what other services this service relies on
* `@property` - describes a property of an object
* `@description` - used to provide a description of a component in markdown
* `@link` - specifies a link to a URL or a type in the API reference.
  Links to the API have the following structure:

    * the module namespace, followed by `.` (optional, default `ng`)
    * the `@ngdoc` type (see below), followed by `:` (optional, automatically inferred)
    * the name
    * the method, property, or anchor (optional)
    * the display name

  For example: `{@link ng.type:$rootScope.Scope#$new Scope.$new()}`.

* `@example` - specifies an example. This can be a simple code block, or a
  [runnable example](#the-example-tag).
* `@deprecated` - specifies that the following code is deprecated and should not be used.
  In The AngularJS docs, there are two specific patterns which can be used to further describe
  the deprecation: `sinceVersion="<version>"` and `removeVersion="<version>"`

The `type` in `@param` and `@returns` must be wrapped in `{}` curly braces, e.g. `{Object|Array}`.
Parameters can be made optional by *either* appending a `=` to the type, e.g. `{Object=}`, *or* by
putting the `[name]` in square brackets.
Default values are only possible with the second syntax by appending `=<value>` to the parameter
name, e.g. `@param {boolean} [ownPropsOnly=false]`.

Descriptions can contain markdown formatting.

#### AngularJS-specific jsdoc directives

In addition to the standard jsdoc tags, there are a number that are specific to the Angular
code-base:

* `@ngdoc` - specifies the type of thing being documented. See below for more detail.
* `@eventType emit|broadcast` - specifies whether the event is emitted or broadcast
* `@usage` - shows how to use a `function` or `directive`. Is usually automatically generated.
* `@knownIssue` - adds info about known quirks, problems, or limitations with the API, and possibly,
  workarounds. This section is not for bugs.

The following are specific to directives:

* `@animations` - specifies the animations a directive supports
* `@multiElement` - specifies if a directive can span over multiple elements
* `@priority` - specifies a directive's priority
* `@restrict` - is extracted to show the usage of a directive. For example, for [E]lement,
  [A]ttribute, and [C]lass, use `@restrict ECA`
* `@scope` - specifies that a directive will create a new scope

### The `@ngdoc` Directive
This directive helps to specify the template used to render the item being documented.  For instance,
a directive would have different properties to a filter and so would be documented differently.  The
commonly used types are:

* `overview` - a general page (guide, api index)
* `provider` - AngularJS provider, such as `$compileProvider` or `$httpProvider`.
* `service` - injectable AngularJS service, such as `$compile` or `$http`.
* `object` - well defined object (often exposed as a service)
* `function` - function that will be available to other methods (such as a helper function within
  the ng module)
* `method` - method on an object/service/controller
* `property` - property on an object/service/controller
* `event` -  AngularJS event that will propagate through the `$scope` tree.
* `directive` - AngularJS  directive
* `filter` - AngularJS filter
* `error` - minErr error description

### General documentation with Markdown

Any text in tags can contain markdown syntax for formatting. Generally, you can use any markdown
feature.

#### Headings

Only use *h2* headings and lower, as the page title is set in *h1*. Also make sure you follow the
heading hierarchy. This ensures correct table of contents are created.

#### Code blocks
In line code can be specified by enclosing the code in back-ticks (\`).
A block of multi-line code can be enclosed in triple back-ticks (\`\`\`) but it is formatted better
if it is enclosed in &lt;pre&gt;...&lt;/pre&gt; tags and the code lines themselves are indented.

### Writing runnable (live) examples and e2e tests
It is possible to embed examples in the documentation along with appropriate e2e tests. These
examples and scenarios will be converted to runnable code within the documentation.  So it is
important that they work correctly.  To ensure this, all these e2e scenarios are run as part of the
continuous integration tests.

If you are adding an example with an e2e test, you should [run the test locally](#e2e-tests) first
to ensure it passes. You can change `it(...)` to `fit(...)` to run only your test,
but make sure you change it back to `it(...)` before committing.

#### The `<example>` tag
This tag identifies a block of HTML that will define a runnable example. It can take the following
attributes:

* `animations` - if set to `true` then this example uses ngAnimate.
* `deps` - Semicolon-separated list of additional angular module files to be loaded,
  e.g. `angular-animate.js`
* `name` - every example should have a name. It should start with the component, e.g directive name,
  and not contain whitespace
* `module` - the name of the app module as defined in the example's JavaScript

Within this tag we provide `<file>` tags that specify what files contain the example code.

```
<example
  module="angularAppModule"
  name="exampleName"
  deps="angular-animate.js;angular-route.js"
  animations="true">
  ...
  <file name="index.html">...</file>
  <file name="script.js">...</file>
  <file name="animations.css">...</file>
  <file name="protractor.js">...</file>
  ...
</example>
```

You can see an example of a well-defined example [in the `ngRepeat` documentation][code.ngRepeat-example].

[closing-issues]: https://help.github.com/articles/closing-issues-via-commit-messages/
[Closure guide to i18n changes]: https://github.com/google/closure-library/wiki/Internationalization-%28i18n%29-changes-in-Closure-Library
[code.badrestrict]: https://github.com/angular/angular.js/blob/202f1809ad14827a6ac6a125157c605d65e0b551/src/ng/compile.js#L1107-L1110
[code.debugInfoEnabled]: https://github.com/angular/angular.js/blob/32fbb2e78f53d765fbb170f7cf99e42e072d363b/src/ng/compile.js#L1378-L1413
[code.html5Mode]: https://github.com/angular/angular.js/blob/202f1809ad14827a6ac6a125157c605d65e0b551/src/ng/location.js#L752-L797
[code.minErr]: https://github.com/angular/angular.js/blob/202f1809ad14827a6ac6a125157c605d65e0b551/src/minErr.js#L53-L113
[code.ngRepeat-example]: https://github.com/angular/angular.js/blob/0822d34b10ea0371c260c80a1486a4d508ea5a91/src/ng/directive/ngRepeat.js#L249-L340
[commit-message-format]: https://docs.google.com/document/d/1QrDFcIiPjSLDn3EL15IJygNPiHORgU1_OOAqWjiDU5Y/edit#
[Common Locale Data Repository (CLDR)]: http://cldr.unicode.org
[corporate-cla]: http://code.google.com/legal/corporate-cla-v1.0.html
[dgeni]: https://github.com/angular/dgeni
[docs.badrestrict]: docs/content/error/$compile/badrestrict.ngdoc
[docs.provider]: https://code.angularjs.org/snapshot/docs/api/auto/service/$provide#provider
[git-revert]: https://git-scm.com/docs/git-revert
[git-setup]: https://help.github.com/articles/set-up-git
[github-issues]: https://github.com/angular/angular.js/issues
[github]: https://github.com/angular/angular.js
[js-style-guide]: https://google.github.io/styleguide/javascriptguide.xml
[karma-browserstack]: https://github.com/karma-runner/karma-browserstack-launcher
[karma-saucelabs]: https://github.com/karma-runner/karma-sauce-launcher
[unit-testing]: https://docs.angularjs.org/guide/unit-testing
[yarn-install]: https://yarnpkg.com/en/docs/install