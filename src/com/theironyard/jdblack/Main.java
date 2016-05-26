package com.theironyard.jdblack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Post> parsePosts(String filename) throws FileNotFoundException {
        //parse file
        ArrayList<Post> posts = new ArrayList<>();
        File f = new File(filename);
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|"); //need to store in a variable
            Post post = new Post(Integer.valueOf(columns[0]), columns[1], columns[2]);
            posts.add(post); //adding post object to posts
        }
        return posts;
    }

    public static void printPosts(ArrayList<Post> posts, int currentPost){
        int postId = 0;
        for( Post post : posts){
            if (post.replyId == currentPost) {
                System.out.printf("[%s] %s by %s\n",postId, post.text, post.author);
            }
            postId++; //increments postId
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Post> posts = parsePosts("posts.txt");


        //start loop
        Scanner consoleScanner = new Scanner(System.in);
        int currentPost = -1;
        while (true) {
            // print out the replies to current post
           printPosts(posts, currentPost);
            // ask for the new id
            System.out.println("Please type id you want to see replies to:");
            currentPost = Integer.valueOf(consoleScanner.nextLine());
        }
    }
}
