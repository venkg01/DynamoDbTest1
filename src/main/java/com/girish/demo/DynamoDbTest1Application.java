package com.girish.demo;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;


@SpringBootApplication
public class DynamoDbTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(DynamoDbTest1Application.class, args);
		
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
		List<Bucket> buckets = s3.listBuckets();
		
		buckets.stream().forEach(b -> {
			System.out.println("Bucket name : " + b.getName());
		});
		
		final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);
		Table table = dynamoDB.getTable("user");
		table.putItem(new Item().withPrimaryKey("user_id", "A"));
		
		
	}

}
