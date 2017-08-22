package com.dddtraining.catalog;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import com.dddtraining.catalog.application.category.CategoryService;
import com.dddtraining.catalog.application.product.ProductServices;
import com.dddtraining.catalog.domain.model.category.Category;
import com.dddtraining.catalog.domain.model.product.Brand;
import com.dddtraining.catalog.domain.model.product.Title;



public class Consumer {
	private Product produit;
	private ProductServices productServices;
	private CategoryService categoryService;
	private Receiver receiver;
	
	
	
	
	
	public Product getProduit() {
		return produit;
	}
	public void setProduit(Product produit) {
		this.produit = produit;
	}
	public ProductServices getProductServices() {
		return productServices;
	}
	public void setProductServices(ProductServices productServices) {
		this.productServices = productServices;
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public Consumer(CategoryService categoryService, ProductServices productServices){
		this.categoryService = categoryService;
		this.productServices = productServices;
		this.receiver = new Receiver();
	}
	public void consume() throws InterruptedException, JMSException{
		boolean vrai = true;
		while(vrai){
			thread(receiver, false);
			Thread.sleep(100);
		}
		
		receiver.getConsumer().close();
        receiver.getSession().close();
        receiver.getConnection().close();
	}
	
	private  class Receiver implements Runnable, ExceptionListener {
		
		private ActiveMQConnectionFactory connectionFactory;
		private Connection connection;
		private  Session session;
		private Destination destination;
		private MessageConsumer consumer;
		
		public Receiver(){
			 try {
				 
				// Create a ConnectionFactory
	                connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	 
	                // Create a Connection
	                connection = connectionFactory.createConnection();
	                connection.start();
	 
	                connection.setExceptionListener(this);
	 
	                // Create a Session
	                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	 
	                // Create the destination (Topic or Queue)
	                destination = session.createQueue("NEW.PRODUCT");
	 
	                // Create a MessageConsumer from the Session to the Topic or Queue
	                consumer = session.createConsumer(destination);
			 } catch (Exception e) {
	                System.out.println("Caught: " + e);
	                e.printStackTrace();
	            }
		}
		
		
        public void run() {
            try {
 
                // Wait for a message
                Message message = consumer.receive();
                
                /*if (message instanceof ObjectMessage) {
	            	Object object = ((ObjectMessage) message).getObject();
	            	produit = (Product) object;
	            	System.out.println(produit.getDescription());
	            	//System.out.println(this.getClass().getName()
	            	//+ "has received a message : " + (EventMessage) object);
            	}else{
            		System.out.println("Different ObjectMessage instance");
            	}*/
 
                if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                
                JSONObject jsonObject = new JSONObject(tm.getText());
                //System.out.println(produit.getDescription());
                
              
        		
        		Category defaultCat = categoryService.getCategoryById("default");
        				//new Category("default", "default", "default", "/home/diapogift/img/catalog/category/default.png");
                
                List<String> imgs = new ArrayList<String>();
        		
        		imgs.add("/home/nkalla/img/dddtraining/product/" + jsonObject.getString("name") + System.currentTimeMillis() + "1.png");
        		imgs.add("/home/nkalla/img/dddtraining/product/" + jsonObject.getString("name") + System.currentTimeMillis() + "2.png");
        		imgs.add("/home/nkalla/img/dddtraining/product/" + jsonObject.getString("name") + System.currentTimeMillis() + "2.png");
        		imgs.add("/home/nkalla/img/dddtraining/product/" + jsonObject.getString("name")+ System.currentTimeMillis() + "3.png");
        		imgs.add("/home/nkalla/img/dddtraining/product/" + jsonObject.getString("name") + System.currentTimeMillis() + "4.png");
        		
        		
        		com.dddtraining.catalog.domain.model.product.Product prod = 
        				new com.dddtraining.catalog.domain.model.product.Product(
        						jsonObject.getString("id"), 
        						defaultCat,
        						jsonObject.getString("name"), 
        						jsonObject.getString("description"), 
        						new Title(""),
        						imgs, 
        						0, 
        						new Brand("Default", "default"), 
        						new BigDecimal(0));
        		prod.updateTitle();
        		
        		productServices.add(prod);
                    //String text = textMessage.getText();
                   // System.out.println("Received: " + text);
                } else {
                    System.out.println("Received: " + message);
                }
 
                
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
            }
        }
 
        @Override
        public synchronized void onException(JMSException ex) {
            System.out.println("JMS Exception occured.  Shutting down client.");
        }


		public ActiveMQConnectionFactory getConnectionFactory() {
			return connectionFactory;
		}


		public void setConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
			this.connectionFactory = connectionFactory;
		}


		public Connection getConnection() {
			return connection;
		}


		public void setConnection(Connection connection) {
			this.connection = connection;
		}


		public Session getSession() {
			return session;
		}


		public void setSession(Session session) {
			this.session = session;
		}


		public Destination getDestination() {
			return destination;
		}


		public void setDestination(Destination destination) {
			this.destination = destination;
		}


		public MessageConsumer getConsumer() {
			return consumer;
		}


		public void setConsumer(MessageConsumer consumer) {
			this.consumer = consumer;
		}
        
        
        

		
    }
	
	public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }
}
