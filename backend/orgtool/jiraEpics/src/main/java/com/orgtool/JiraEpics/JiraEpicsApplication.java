package com.orgtool.JiraEpics;

import com.orgtool.JiraEpics.models.Epic;
import com.orgtool.JiraEpics.models.EpicsResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JiraEpicsApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(JiraEpicsApplication.class, args);
		JiraEpicService service = new JiraEpicService();
		EpicsResponse epic = service.getBoardEpics("2");
		epic.test();
		//service.getEpic("1009");

	}

}
