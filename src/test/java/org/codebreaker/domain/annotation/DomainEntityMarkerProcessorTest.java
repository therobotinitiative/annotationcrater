package org.codebreaker.domain.annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class DomainEntityMarkerProcessorTest {
  @Spy private RoundEnvironment mockRoundEnvironment;

  private DomainEntityMarkerProcessor processor;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    processor = new DomainEntityMarkerProcessor();
  }

  @Test
  void testProcessorAnnotations() {
    Class<?> processorClass = DomainEntityMarkerProcessor.class;
    assertTrue(processorClass.isAnnotationPresent(SupportedAnnotationTypes.class));
    assertTrue(processorClass.isAnnotationPresent(SupportedSourceVersion.class));
    assertEquals(
        "org.codebreaker.domain.annotation.DomainEntityMarker",
        processorClass.getAnnotation(SupportedAnnotationTypes.class).value()[0]);
  }

  @Test
  void testProcessAnnotatedClassesOk() {
    assertTrue(processor.process(Collections.emptySet(), mockRoundEnvironment));
    // Gather classes that are annotated with @DomainEntityMarker and has
    // @DomainEntityId field
    verify(mockRoundEnvironment, atLeastOnce())
        .getElementsAnnotatedWith(eq(DomainEntityMarker.class));
  }

  @Test
  void testNullPointException() {
    assertThrows(NullPointerException.class, () -> processor.process(null, null));
  }

  @Test
  void returnFalseWhenprocessingOver() {
    when(mockRoundEnvironment.processingOver()).thenReturn(true);
    assert !processor.process(Collections.EMPTY_SET, mockRoundEnvironment);
    verify(mockRoundEnvironment, atLeastOnce()).processingOver();
  }
}
