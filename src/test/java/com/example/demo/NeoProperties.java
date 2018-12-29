package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Component 的定义为实例，方便在 Spring Boot 项目中引用；
   @ConfigurationProperties(prefix="neo")，
            表示以 neo 开头的属性会自动赋值到对象的属性中，比如，neo.title 会自动赋值到对象属性 title 中。
 * @author admin
 *
 */
@Component
@ConfigurationProperties(prefix="neo")
public class NeoProperties {

	private String title;
	private String description;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
