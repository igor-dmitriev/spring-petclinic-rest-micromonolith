package org.springframework.samples.petclinic.visit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

public class AbstractPostgresRepositoryTest {

  @Autowired
  protected DataSource dataSource;

  @PersistenceContext
  protected EntityManager em;

  private static final Integer POSTGRES_PORT = 5432;
  private static final String URL_PATTERN = "jdbc:p6spy:postgresql://%s:%s/petclinic";
  private static final String TEST_USERNAME = "test_login";
  private static final String TEST_PASSWORD = "test_password";

  private static final PostgreSQLContainer POSTGRESQL_CONTAINER = (PostgreSQLContainer) new PostgreSQLContainer("postgres:9.6.1")
      .withUsername(TEST_USERNAME)
      .withPassword(TEST_PASSWORD)
      .withDatabaseName("petclinic")
      .withExposedPorts(POSTGRES_PORT);

  static {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    POSTGRESQL_CONTAINER.start();
    Runtime.getRuntime().addShutdownHook(new Thread(POSTGRESQL_CONTAINER::stop));
    String url = String.format(URL_PATTERN, POSTGRESQL_CONTAINER.getContainerIpAddress(), POSTGRESQL_CONTAINER.getMappedPort(POSTGRES_PORT));
    System.setProperty("spring.datasource.url", url);
    System.setProperty("spring.datasource.username", TEST_USERNAME);
    System.setProperty("spring.datasource.password", TEST_PASSWORD);
  }

  protected void flushAndClear() {
    em.flush();
    em.clear();
  }
}
