# Code, code and code



## Give examples and clear code snippets

For each use case you choose, have code snippets to demonstrate how to replicate it.

You can use snippets to highlight the main parts of the code.

!> Remember. THE CODE MUST ALWAYS WORK


```kotlin
# Configure the service
val myService = ServiceBuilder().withConfiguration(conf).build()
```

But do not explain all options, configurations, or caveats of the service. If you want, you can add a link to the [references](/references/) for more informations.

```kotlin
# Save resource
myService.save(entity, options)
```

Give the user all information necessary to validate the case and feel confident to use the service. 

```kotlin
# So is necessary a command to check the saved entity
val myEntity = myService.get(entityId)
println(myEntity)
```

It is also a good idea to give the user a complete code to reproduce the case. 

<img src="assets/images/github.png" width="20px"/> [repository](https://github.com/mercadolibre).

?> Each section can be small to give time to the user to understand what was doing and try other things.

