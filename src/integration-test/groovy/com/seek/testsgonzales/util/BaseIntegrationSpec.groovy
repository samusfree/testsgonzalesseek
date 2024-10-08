package com.seek.testsgonzales.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.seek.testsgonzales.TestSGonzalesApplication
import org.spockframework.spring.EnableSharedInjection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.ext.ScriptUtils
import org.testcontainers.jdbc.JdbcDatabaseDelegate
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@EnableSharedInjection
@ActiveProfiles('test')
@SpringBootTest(webEnvironment = RANDOM_PORT,
    classes = TestSGonzalesApplication)
abstract class BaseIntegrationSpec extends Specification {
  @Autowired
  protected TestRestTemplate testRestTemplate

  @Shared
  @Autowired
  protected ObjectMapper objectMapper

  @Shared
  private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
      .withDatabaseName("seek")
      .withUsername("user")
      .withPassword("password")

  private JdbcDatabaseDelegate containerDelegate = new JdbcDatabaseDelegate(postgreSQLContainer, "")

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl)
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername)
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword)
    postgreSQLContainer.start()
  }

  def setup() {
    ScriptUtils.runInitScript(containerDelegate, "init.sql")
  }

  def cleanup() {
    ScriptUtils.runInitScript(containerDelegate, "cleanup.sql")
  }
}
