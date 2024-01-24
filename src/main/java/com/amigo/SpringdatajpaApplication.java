package com.amigo;

import com.amigo.Entity.Student;
import com.amigo.Entity.StudentIdCard;
import com.amigo.Repository.StudentIdCardRepository;
import com.amigo.Repository.StudentRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class SpringdatajpaApplication {
	private static final Logger logger = LoggerFactory.getLogger(SpringdatajpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository,
											   StudentIdCardRepository studentIdCardRepository) {
		return args -> {
			Faker faker = new Faker();
			String firstName = faker.name().firstName();
			String lastName = faker.name().lastName();
			String email = String.format("%s.%s@helper.in", firstName, lastName);

			/*StudentIdCard studentIdCard = new StudentIdCard("123456789");
			Student student = new Student(firstName, lastName, email,
					faker.number().numberBetween(18, 50),studentIdCard);
			studentRepository.save(student);*/

			Student student = new Student(firstName, lastName, email,
					faker.number().numberBetween(18, 50));
			StudentIdCard studentIdCard = new StudentIdCard("123456789", student);
			studentIdCardRepository.save(studentIdCard);

			logger.info("\n--------------Saved student: {}-----------------", student);
			//Here im not using student repo to save Student property data
			//studentIdCardRepository.save(studentIdCard);
			// by default OneToOne mapping uses FetchType.EAGER so that here student object will be load here
			logger.info("\n------finding id 1 in studidcard------ ");
			studentIdCardRepository.findById(1L).
					ifPresent(System.out::println);
			// by default OneToOne mapping uses FetchType.EAGER so that here studentIdCard object will be load here
			logger.info("\n------finding id 1 in studentrepo------ ");
			studentRepository.findById(1L)
					.ifPresent(System.out::println);
			logger.info("\n------deleting id 1------ ");
			studentRepository.deleteById(1L);
			//studentIdCardRepository.deleteById(1L);
			// Find student and delete
			/*studentRepository.findById(1L).ifPresent(savedStudent -> {
				logger.info("\n------Deleting student------ ");
				studentRepository.delete(savedStudent);
			};*/


		};

	}
}