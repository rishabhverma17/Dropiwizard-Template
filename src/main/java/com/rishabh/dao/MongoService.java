/* Copyright 2020 https://www.rishabhverma.in */
package com.rishabh.dao;

import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import com.rishabh.model.Employee;

public class MongoService {

  public void insertOne(MongoCollection<Document> collection, Document document) {
    collection.insertOne(document);
  }

  public void insertMany(MongoCollection<Document> collection, List<Document> documents) {
    collection.insertMany(documents);
  }

  public List<Document> find(MongoCollection<Document> collection) {
    return collection.find().into(new ArrayList<>());
  }

  public List<Document> findByKey(MongoCollection<Document> collection, String key, String value) {
    return collection.find(eq(key, value)).into(new ArrayList<>());
  }

  public List<Document> findByCriteria(MongoCollection<Document> collection, String key, int lessThanValue, int greaterThanValue,
          int sortOrder) {
    List<Document> documents = new ArrayList<>();
    FindIterable iterable = collection.find(and(lt(key, lessThanValue), gt(key, greaterThanValue))).sort(new Document(key, sortOrder));
    iterable.into(documents);
    return documents;
  }

  public void updateOneEmployee(MongoCollection<Document> collection, String key1, String key2, String key3, Employee employee) {
    collection.updateOne(new Document(key1, employee.getName()),
            new Document("$set", new Document(key2, employee.getDepartment()).append(key3, employee.getSalary())));
  }

  public void deleteOne(MongoCollection<Document> collection, String key, String value) {
    collection.deleteOne(eq(key, value));
  }
}
