# annotationcrater
Annotation procressor for Moon Modules.
Compile time annotation processor to verify
domain entity classes annotated with DomainEntityMarker
classes have field marked as identification.
In your project use com.orbital3d.domain as dependency
with combination with this project as the annotation processor.

## Gradle example
annotationProcessor("com.orbital3d:annnotationcrater:<version to use>")
in combination with
implementation("com.orbital3d:domain:<version to use>")

# Code example
```
@DomainEntityMarker
public class MyDomainEntity implements DomainEntity<Long> {
    @Id
    private Long id;

    @Override
    public Long getId() {
        return id;
    }
}
```