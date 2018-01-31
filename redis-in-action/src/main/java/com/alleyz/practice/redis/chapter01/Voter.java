package com.alleyz.practice.redis.chapter01;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

/**
 * 文章投票
 *  1、如果一篇文章获得了至少200票，则该文章为有趣的文章
 *  2、如果1000篇文章中50篇符合有趣的要求，则将其置于列表前100 一天
 *  3、一周前的文章不能继续投票
 *
 * 文章评分
 *  支持票数 * 432 + pubDate
 *
 *
 * @author alleyz
 *
 */
public class Voter {
    private final static String ARTICLE_ID_PRE = "article:";
    private final static String GROUP_PRE = "group:";
    private final static int ONE_WEEK_SECONDS = 7 * 24 * 60 * 60;
    private final static int VOTE_SCORE = 432;
    private final static String TITLE = "title", LINK = "link", TIME = "time", USER = "user", VOTES = "votes";
    private final  static String VOTED = "voted:", SCORE = "score:", TIME_K = "time:";

    private final Jedis conn;

    Voter(Jedis conn) {
        this.conn = conn;
    }
    /**
     * 发布文章
     * @param article 文章
     */
    void publishArticle(Article article) {
        // 获取文章id
        String id = conn.incr(ARTICLE_ID_PRE) + "";
        article.id = ARTICLE_ID_PRE + id;

        long time = System.currentTimeMillis() / 1000;
        article.time = time + "";
        Map<String, String> artMap = new HashMap<>(5);
        artMap.put(TITLE, article.title);
        artMap.put(LINK, article.link);
        artMap.put(TIME, article.time);
        artMap.put(USER, article.user);
        artMap.put(VOTES, "1");
        // 发布文章
        conn.hmset(article.id, artMap);
        // 默认投自己一票
        String votedId = VOTED + id;
        conn.sadd(votedId, article.user);
        conn.expire(votedId, ONE_WEEK_SECONDS);
        // 添加评分和时间
        conn.zadd(SCORE, time + VOTE_SCORE, article.id);
        conn.zadd(TIME_K, time, article.id);
    }

    /**
     * 进行文章投票
     * @param user 用户
     * @param articleId 文章
     */
    void vote(String user, String articleId){
        long timeDiff = System.currentTimeMillis() / 1000 - ONE_WEEK_SECONDS;
        String article = ARTICLE_ID_PRE + articleId;
        if(conn.zscore(TIME_K, article) < timeDiff) {
            return;
        }
        if(conn.sadd(VOTED + articleId, user) == 1) {
            conn.zincrby(SCORE, VOTE_SCORE, article);
            conn.hincrBy(article, VOTES, 1);
        }
    }

    /**
     * 获取文章top n
     * @param n 数量
     * @return 文章列表
     */
    private List<Article> listArticleWithTopN(int n, String order) {
        int start = 0 ;
        Set<String> articleIds = conn.zrevrange(order, start, n);
        List<Article> articles = new ArrayList<>(n);
        articleIds.forEach(s -> articles.add(getArticle(s)));
        return articles;
    }

    /**
     * 获取文章top N 根据评分
     * @param n n
     * @return articles
     */
    List<Article> listArticleWithTopN(int n) {
        return listArticleWithTopN(n, SCORE);
    }

    /**
     * 获取文章
     * @param articleId 文章ID
     * @return 文章
     */
    private Article getArticle(String articleId) {
        Map<String, String> artMap = conn.hgetAll(articleId);
        Article article = new Article();
        article.link = artMap.get(LINK);
        article.title = artMap.get(TITLE);
        article.user = artMap.get(USER);
        article.id = articleId;
        article.time = artMap.get(TIME);
        article.votes = artMap.get(VOTES);
        return article;
    }

    /**
     * 添加文章到指定group
     * @param articleId 文章ID
     * @param group group
     */
    void addGroups(String articleId, List<String> group) {
        String article = ARTICLE_ID_PRE + articleId;
        group.forEach(s-> conn.sadd(GROUP_PRE + s, article));
    }

    /**
     *  获取group中文章topN
     * @param group group
     * @param n top N
     * @param order order
     * @return articles
     */
    private List<Article> listArticleWithGroup(String group, int n, String order) {
        String key = order + group;
        if(!conn.exists(key)) {
            System.out.println("No Cache!!!");
            ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);
            System.out.println(conn.zinterstore(key, params,  GROUP_PRE + group, order));
            conn.expire(key, 60);
        }
        return listArticleWithTopN(n, key);
    }

    /**
     * 获取组内按评分top N
     * @param group 组
     * @param n N
     * @return articles
     */
    List<Article> listArticleWithGroup(String group, int n) {
        return listArticleWithGroup(group, n, SCORE);
    }
}
class Article{

    String id;
    String title;
    String link;
    String user;
    String time;
    String votes;
    @Override
    public String toString() {
        return String.format("Article=id:%s,title:%s,user:%s,votes:%s", id, title, user, votes);
    }
}
