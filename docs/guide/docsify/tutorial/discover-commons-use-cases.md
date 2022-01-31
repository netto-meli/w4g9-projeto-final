# Discover the most commons use cases


## Know the cases more used in the service
It is important to have **working examples** of the simples cases so the user can test the service.

The use case must be of the basic operations that the user will do using the service. 

Each use case must accompany code snippets, print screens or gifs, and explanations to the user to see the sequence of commands.

## Reproduce the behavior

The most important thing when choosing a use case is that the user must reproduce the behavior. So it is necessary to think in simple cases and also in how to verify that the case was successful.

```kotlin
# if I have a save operation
myService.save(entity)

# I must also show a get operation to see the result
myService.get(entityId)
```

?> The tutorial is like teaching someone how to cook. It is important to explain some details, but not so much. The user must get confident first.

## Provide the complete picture before start

Allow the learner to form an idea of what they will achieve right from the start. Explain what the learners will do and what they will achieve at the end of the tutorial.