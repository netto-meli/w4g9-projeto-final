# How-to create diagrams with code

In MELI documentation framework is already enabled two tools to create diagrams with code.


## Mermaid.js

To use [mermaid.js](https://mermaid-js.github.io/mermaid/#/) create a block of code with `mermaid` type:


~~~
    ``` mermaid
        your script
    ```
~~~

Example:

The code

~~~
   ``` mermaid
      graph TD
        A[Christmas] -->|Get money| B(Go shopping)
        B --> C{Let me think}
        C -->|One| D[Laptop]
        C -->|Two| E[iPhone]
        C -->|Three| F[fa:fa-car Car]
    ```
~~~

will generate the image:

``` mermaid
    graph TD
    A[Christmas] -->|Get money| B(Go shopping)
    B --> C{Let me think}
    C -->|One| D[Laptop]
    C -->|Two| E[iPhone]
    C -->|Three| F[fa:fa-car Car]
```


## Plant UML

To use [PlantUML](https://plantuml.com/) create a block of code with `plantuml` type:

~~~
    ``` plantuml
        your script
    ```
~~~

Example:

The code

~~~
   ``` plantuml
        @startuml
            node node1
            node node2
            node node3
            node node4
            node node5
            node1 -- node2 : label1
            node1 .. node3 : label2
            node1 ~~ node4 : label3
            node1 == node5
        @enduml
    ```
~~~

will generate the image:

``` plantuml
        @startuml
            node node1
            node node2
            node node3
            node node4
            node node5
            node1 -- node2 : label1
            node1 .. node3 : label2
            node1 ~~ node4 : label3
            node1 == node5
        @enduml
```