package com.kaggle.titanic.dbservice;

import com.kaggle.titanic.dbservice.model.Titanic;
import com.kaggle.titanic.dbservice.repository.TitanicRepository;
import org.apache.catalina.connector.InputBuffer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Optional;

@EnableJpaRepositories(basePackages = "com.kaggle.titanic.dbservice.repository")
@SpringBootApplication
public class DbserviceApplication {
	@Bean
	CommandLineRunner commandLineRunner(TitanicRepository titanicRepository){
		return args -> {
			try {
				File filePath = new File("C:/dbservice/train.csv");
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
				reader.lines().forEach(line -> {
					//System.out.println(line);
					String[] str= line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
					//System.out.println(str[11]);
					Integer number=0;
					try
					{
						if(str[5] != "")
						    number = Integer.parseInt(str[5]);
					}
					catch (NumberFormatException e)
					{
						number = null;
					}
					titanicRepository.save(new Titanic(Integer.valueOf(str[0]),Integer.valueOf(str[1]),Integer.valueOf(str[2]),str[3],str[4], number,Integer.valueOf(str[6]),Integer.valueOf(str[7]),str[8],Double.parseDouble(str[9]),str[10],str[str.length-1].charAt(0)));
				});
				reader.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		};
	}
	public static void main(String[] args) {
		SpringApplication.run(DbserviceApplication.class, args);
	}

}
