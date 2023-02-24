package com.orgtool.JiraEpics;

import com.orgtool.JiraEpics.Service.JiraEpicService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JiraEpicsApplication {
	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(JiraEpicsApplication.class, args);

		JiraEpicService service = new JiraEpicService();
		//service.getIssuesForEpic("TES-7");
		service.getBoardEpics("2");
		//service.getEpic("TES-5");
		//service.deleteEpic("TES-7");

	}

}
