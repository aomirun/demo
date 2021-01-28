package com.example.demo;

// import com.example.demo.jms.JmqConfig;
// import com.example.demo.jms.Producer;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;


// @SpringBootTest
// class DemoApplicationTests {

// 	@Autowired
// 	private Producer producer; 

// 	@Autowired
//     private JmqConfig jmqConfig;

// 	@Test
// 	public void jmsIBMMqTest() throws InterruptedException {
// 		String destination = jmqConfig.getSendQueueName();

// 		for (int i = 0; i < 21; i++) {
// 			producer.sendMessage(destination, String.format("My name is aomi%s", i));
// 		}
// 	}

// 	@Test
// 	void contextLoads() {
// 	}

// }