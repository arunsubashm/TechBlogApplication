package technicalblog.service;

import technicalblog.model.Post;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

@PersistenceUnit(unitName = "techblog")

private EntityManagerFactory emf;

    public PostService() {

        System.out.println("*** PostService ***");
    }

    public void createPost (Post newPost){
    }

    public List <Post> getAllPosts () {

        EntityManager em = emf.createEntityManager();

        TypedQuery<Post> query = em.createQuery("SELECT p from Post p", Post.class);
        List<Post> resultList;

        resultList = query.getResultList();

        return resultList;

    }

    public List<Post> getOnePost() {

        EntityManager em = emf.createEntityManager();

        TypedQuery<Post> query = em.createQuery("SELECT p from Post p where p.id = 3", Post.class);
        List<Post> resultList;

        resultList = query.getResultList();

        return resultList;

    }

    public ArrayList <Post> getAllPostsJDBC () {
        ArrayList<Post> posts = new ArrayList <>();

//        Post post1 = new Post();
//        post1.setTitle("Post 1");
//        post1.setBody("Post Body 1");
//        post1.setDate(new Date());
//
//        Post post2 = new Post();
//        post2.setTitle("Post 2");
//        post2.setBody("Post Body 2");
//        post2.setDate(new Date());
//
//        Post post3 = new Post();
//        post3.setTitle("Post 3");
//        post3.setBody("Post Body 3");
//        post3.setDate(new Date());
//
//        Post post4 = new Post();
//        post4.setTitle("Post 4");
//        post4.setBody("Post Body 4");
//        post4.setDate(new Date());
//
//        posts.add(post1);
//        posts.add(post2);
//        posts.add(post3);
//        posts.add(post4);

        Connection connection = null;

        try{
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres", "password");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM posts");
            while(rs.next()){
                Post post = new Post();
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));
                posts.add(post);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return posts;
    }

    public ArrayList<Post> getOnePostJDBC() {
        ArrayList<Post> posts = new ArrayList<>();

//        Post post1 = new Post();
//        post1.setTitle("This is your Post");
//        post1.setBody("This is your Post. It has some valid content");
//        post1.setDate(new Date());
//        posts.add(post1);

        Connection connection = null;

        try{
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres", "password");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM posts where id = 4");
            while(rs.next()){
                Post post = new Post();
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));
                posts.add(post);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally{
            try{
                connection.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        return posts;

    }
}

