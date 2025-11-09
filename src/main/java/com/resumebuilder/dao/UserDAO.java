package com.resumebuilder.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.resumebuilder.model.User;
import com.resumebuilder.util.MongoDBUtil;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    private MongoCollection<Document> collection;
    
    public UserDAO() {
        collection = MongoDBUtil.getDatabase().getCollection("users");
    }
    
    public boolean registerUser(User user) {
        try {
            if (getUserByEmail(user.getEmail()) != null) {
                return false;
            }
            
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            
            Document doc = new Document("email", user.getEmail())
                    .append("password", hashedPassword)
                    .append("fullName", user.getFullName());
            
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public User loginUser(String email, String password) {
        try {
            Document doc = collection.find(Filters.eq("email", email)).first();
            if (doc != null && BCrypt.checkpw(password, doc.getString("password"))) {
                User user = new User();
                user.setId(doc.getObjectId("_id"));
                user.setEmail(doc.getString("email"));
                user.setFullName(doc.getString("fullName"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User getUserByEmail(String email) {
        try {
            Document doc = collection.find(Filters.eq("email", email)).first();
            if (doc != null) {
                User user = new User();
                user.setId(doc.getObjectId("_id"));
                user.setEmail(doc.getString("email"));
                user.setFullName(doc.getString("fullName"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User getUserById(String userId) {
        try {
            Document doc = collection.find(Filters.eq("_id", new ObjectId(userId))).first();
            if (doc != null) {
                User user = new User();
                user.setId(doc.getObjectId("_id"));
                user.setEmail(doc.getString("email"));
                user.setFullName(doc.getString("fullName"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateUserProfile(String userId, String fullName, String email) {
        try {
            Document update = new Document("$set", new Document()
                    .append("fullName", fullName)
                    .append("email", email));
            
            collection.updateOne(Filters.eq("_id", new ObjectId(userId)), update);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean changePassword(String userId, String currentPassword, String newPassword) {
        try {
            Document doc = collection.find(Filters.eq("_id", new ObjectId(userId))).first();
            if (doc != null && BCrypt.checkpw(currentPassword, doc.getString("password"))) {
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                
                Document update = new Document("$set", new Document("password", hashedPassword));
                collection.updateOne(Filters.eq("_id", new ObjectId(userId)), update);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
