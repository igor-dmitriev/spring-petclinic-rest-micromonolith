package org.springframework.samples.petclinic.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;

import org.junit.jupiter.api.Test;

class StructureTest {

  private static final String APPLICATION_MODULE = "org.springframework.samples.petclinic.application..";
  private static final JavaClasses ALL_CLASSES = new ClassFileImporter()
      .importPackages("org.springframework.samples.petclinic");
  private static final String CUSTOMER_MODULE = "org.springframework.samples.petclinic.customer..";
  private static final String CUSTOMER_API = "org.springframework.samples.petclinic.customer.api..";
  private static final String CONTROLLERS = "..controller..";
  private static final String SERVICES = "..service..";
  private static final String REPOSITORIES = "..repository..";

  @Test
  void layers() {
    Architectures.layeredArchitecture()
        .layer("Controller").definedBy(CONTROLLERS)
        .layer("Service").definedBy(SERVICES)
        .layer("Repository").definedBy(REPOSITORIES)
        .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
        .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Service")
        .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
        .check(ALL_CLASSES);
  }

  @Test
  void customerModuleMustBeAccessedJustFromApplicationAndItself() {
    ArchRuleDefinition
        .classes()
        .that()
        .resideInAPackage(CUSTOMER_MODULE)
        .and()
        .resideOutsideOfPackage(CUSTOMER_API)
        .should()
        .onlyBeAccessed()
        .byAnyPackage(APPLICATION_MODULE, CUSTOMER_MODULE)
        .check(ALL_CLASSES);
  }
}