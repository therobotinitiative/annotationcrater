package com.orbital3d.domain.annotation;

import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

/**
 * Annotation processor that checks if classes annotated with @DomainEntityMarker have an id field.
 */
@SupportedAnnotationTypes("com.orbital3d.domain.annotation.DomainEntityMarker")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class DomainEntityMarkerProcessor extends AbstractProcessor {

  /** Default constructor for the annotation processor. */
  public DomainEntityMarkerProcessor() {}

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(DomainEntityMarker.class)) {
      if (annotatedElement.getKind() != ElementKind.CLASS) {
        continue;
      }
      TypeElement typeElement = (TypeElement) annotatedElement;

      boolean idFound =
          typeElement.getEnclosedElements().stream()
              .filter(fieldElement -> fieldElement.getKind() == ElementKind.FIELD)
              .anyMatch(e -> e.getAnnotation(DomainEntityId.class) != null);

      if (!idFound) {
        processingEnv
            .getMessager()
            .printError("Element does not have a field marked as an id.", annotatedElement);
      }
    }
    return true;
  }
}
