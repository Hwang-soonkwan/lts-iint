package ltsiint.common;

import io.cucumber.spring.CucumberContextConfiguration;
import ltsiint.ProgressApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { ProgressApplication.class })
public class CucumberSpingConfiguration {}
