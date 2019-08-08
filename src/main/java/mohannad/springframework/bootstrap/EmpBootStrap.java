package mohannad.springframework.bootstrap;

import mohannad.springframework.model.Repository.EmailRepository;
import mohannad.springframework.model.Repository.EmployeeRepository;
import mohannad.springframework.model.books.Email;
import mohannad.springframework.model.books.Employee;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * created by mohannad
 */
@Component
public class EmpBootStrap   implements ApplicationListener<ContextRefreshedEvent> {

    private EmailRepository emailRepository;
    private EmployeeRepository employeeRepository;

    public EmpBootStrap(EmailRepository emailRepository, EmployeeRepository employeeRepository) {
        this.emailRepository = emailRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Email email1 = new Email();
        email1.setDomain("@gmail.com");

        Email email2 = new Email();
        email2.setDomain("@hotmail.com");

        Email email3 = new Email();
        email3.setDomain("@cs.com");


        Employee emp1 = new Employee();
        emp1.setName("mohanad");


        Employee emp2 = new Employee();
        emp2.setName("mohanad");


        List<Email> emails = new LinkedList<>();
        emails.add(email1);
        emails.add(email2);
        emp1.setEmails(emails);
        email1.setEmployee(emp1);
        email2.setEmployee(emp1);

        List<Email> emails2 = new LinkedList<>();
        emails2.add(email3);
        emp2.setEmails(emails2);
        email3.setEmployee(emp2);


        employeeRepository.save(emp1);
        employeeRepository.save(emp2);

        emailRepository.save(email1);
        emailRepository.save(email2);
        emailRepository.save(email3);


    }
}
