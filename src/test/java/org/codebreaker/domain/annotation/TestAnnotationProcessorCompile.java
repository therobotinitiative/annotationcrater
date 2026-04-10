package org.codebreaker.domain.annotation;

import static com.google.testing.compile.Compiler.javac;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.JavaFileObjects;
import javax.tools.JavaFileObject;
import org.junit.jupiter.api.Test;

public class TestAnnotationProcessorCompile {
  @Test
  void testCompileOk() {
    JavaFileObject sourceFile = JavaFileObjects.forResource("sourcefiles/SuccessEntityOne.java");
    Compilation compilation =
        javac().withProcessors(new DomainEntityMarkerProcessor()).compile(sourceFile);
    assertEquals(Compilation.Status.SUCCESS, compilation.status());
  }

  @Test
  void testCompileFailure() {
    JavaFileObject sourceFile = JavaFileObjects.forResource("sourcefiles/FailureEntityOne.java");
    Compilation compilation =
        javac().withProcessors(new DomainEntityMarkerProcessor()).compile(sourceFile);
    assertEquals(Compilation.Status.FAILURE, compilation.status());
  }
}
