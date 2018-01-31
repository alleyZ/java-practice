package com.alleyz.practice.redis.chapter01;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * date: 2018-01-30
 * email: alleyz@126.com
 * @author alleyz
 *
 */
public class App {

    public static void main(String[] args) {
        try(Jedis jedis = new Jedis("localhost")) {
            jedis.select(3);
            Voter voter = new Voter(jedis);
            publish(voter);
            vote(voter);
            voter.listArticleWithTopN(9).forEach(System.out::println);
            group(voter);
            voter.listArticleWithGroup("8", 4).forEach(System.out::println);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void vote(Voter voter) throws Exception{
        for (int i = 0; i < 1000; i++) {
            voter.vote("user-" + i % 999, (1 + Math.round(Math.random() * 19)) + "");
        }
    }

    private static void publish(Voter voter) {
        for (int i = 0; i < 20; i++) {
            Article article = new Article();
            article.link = "http://ww." + i + ".com";
            article.title = i + "-title";
            article.user = "user-" + i % 5;
            voter.publishArticle(article);
        }
    }

    private static void group(Voter voter){
        List<String> groups = new ArrayList<>(5);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 5; j++) {
                groups.add(1 + Math.round(Math.random() * 10) + "");
            }
            voter.addGroups(i + "", groups);
            groups.clear();
        }
    }
}
