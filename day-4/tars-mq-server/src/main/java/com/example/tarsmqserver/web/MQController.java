package com.example.tarsmqserver.web;


// @RestController
// @EnableJms
// @RequestMapping("/api/mq")
// public class MQController { 
//     @Autowired
// 	private JmsTemplate jmsTemplate;

// 	@GetMapping("send") 
// 	String send() {
// 		try {
// 			jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World!");
// 			return "OK";
// 		} catch (JmsException ex) {
// 			ex.printStackTrace();
// 			return "FAIL";
// 		}
// 	}

// 	@GetMapping("recv")
// 	String recv() {
// 		try {
// 			return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
// 		} catch (JmsException ex) {
// 			ex.printStackTrace();
// 			return "FAIL";
// 		}
// 	}
    
// }