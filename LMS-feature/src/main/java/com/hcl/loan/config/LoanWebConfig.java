package com.hcl.loan.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.hcl.loan.dao.LoanDisbursmentDao;
import com.hcl.loan.dao.impl.LoanApprovalDAOImpl;
import com.hcl.loan.dao.impl.LoanDisbursmentDaoImpl;
import com.hcl.loan.dao.impl.RateOfInterestDAOImpl;
import com.hcl.loan.dao.impl.StatusDAOImpl;
import com.hcl.loan.dao.impl.UserLoginDAOImpl;
import com.hcl.loan.model.validator.EMIValidator;
import com.hcl.loan.model.validator.UserLoginValidator;
import com.hcl.loan.service.LoanDisbursmentService;
import com.hcl.loan.service.impl.CalculateEMIServiceImpl;
import com.hcl.loan.service.impl.LoanApprovalServiceImpl;
import com.hcl.loan.service.impl.LoanDisbursmentServiceImpl;
import com.hcl.loan.service.impl.RateOfInterestServiceImpl;
import com.hcl.loan.service.impl.StatusServiceImpl;
import com.hcl.loan.service.impl.UserLoginServiceImpl;
import com.hcl.loan.service.impl.UserServiceImpl;

@Configuration
@EnableWebMvc
@PropertySource("classpath:sql.properties")
@ComponentScan(basePackages = "com.hcl.loan.controller")
public class LoanWebConfig {

	@Autowired
	private Environment environment;

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean
	public LoanApprovalServiceImpl getLoanApprovalService() {
		return new LoanApprovalServiceImpl();
	}

	@Bean
	public UserServiceImpl getUserService() {
		return new UserServiceImpl();
	}

	@Bean
	public UserLoginServiceImpl getUserLoginServiceImpl() {
		return new UserLoginServiceImpl();
	}

	@Bean
	public UserLoginDAOImpl getUserLoginDAOImpl() {
		return new UserLoginDAOImpl(getDataSource());
	}

	@Bean
	public UserLoginValidator getUserLoginValidator() {
		return new UserLoginValidator();
	}

	@Bean
	public LoanApprovalDAOImpl getLoanApprovalDAO() {
		return new LoanApprovalDAOImpl(getDataSource());
	}

	@Bean(name = "loanDisbursmentDao")
	public LoanDisbursmentDao getLoanDisbursementDAO() {
		return new LoanDisbursmentDaoImpl(getDataSource());
	}

	@Bean(name = "loanDisbursmentService")
	public LoanDisbursmentService getLoanDisbursementService() {
		return new LoanDisbursmentServiceImpl();
	}

	@Bean
	public StatusDAOImpl getStatusDAO() {
		return new StatusDAOImpl(getDataSource());
	}

	@Bean
	public StatusServiceImpl getstatusService() {
		return new StatusServiceImpl();
	}

	@Bean
	public CalculateEMIServiceImpl getCalculateEMIService() {
		return new CalculateEMIServiceImpl();
	}

	@Bean
	public RateOfInterestServiceImpl getRateOfInterestService() {
		return new RateOfInterestServiceImpl();
	}

	@Bean
	public RateOfInterestDAOImpl geRateOfInterestDAO() {
		return new RateOfInterestDAOImpl(getDataSource());
	}

	@Bean
	public static EMIValidator getEMIValidator() {
		return new EMIValidator();
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("classpath:dbconfig");
		resource.setDefaultEncoding("UTF-8");
		return resource;
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/lms");
		dataSource.setUsername("root");
		dataSource.setPassword("india2016");

		return dataSource;
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
