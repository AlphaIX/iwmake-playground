package com.iwmake.boot.elasticsearch;

import com.iwmake.boot.elasticsearch.model.Stu;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dylan
 * @since 2020-11-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class)
public class ESTest {

    /**
     * 不建议使用ElasticsearchTemplate对索引进行管理(创建索引，更新映射，删除索引)
     * 索引就像是数据库，或者是数据库中的表，我们平时是不会通过java代码频繁的去创建和删除表，
     * 我们只针对数据做CRUD
     * 在ES中同理，我们尽量使用ElasticsearchTemplate对文档数据做CRUD操作
     * 1. 属性类型(FieldType)，设置不一定生效，如Stu.class中国sign设置为keyword，ES中依然是text类型
     * 2. 主分片与副分片设置不生效
     */
    @Autowired
    private ElasticsearchTemplate esTemplate;


    /**
     * 创建索引，根据字段创建或者更新mapping映射
     */
    @Test
    public void createIndexStu() {
        Stu stu = new Stu();
        stu.setStuId(1005L);
        stu.setName("super man");
        stu.setAge(23);
        stu.setMoney(55.8f);
        stu.setSign("super woman's husband");
        stu.setDesc("save the world");
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(stu).build();
        esTemplate.index(indexQuery);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndexStu() {
        esTemplate.deleteIndex(Stu.class);
    }

    //=================文档操作===================//

    /**
     * 文档更新
     */
    @Test
    public void updateDocStu() {
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("sign", "I am not super man");
        sourceMap.put("money", 88.6f);
        sourceMap.put("age", 33);

        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source(sourceMap);

        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withClass(Stu.class)// 索引
                .withId("1003")//文档id
                .withIndexRequest(indexRequest) // 更新的内容
                .build();

        // 类似sql：update stu set sign='abc', age = 33, money=88.6 where docId=1003

        esTemplate.update(updateQuery);
    }

    /**
     * 根据文档id查询
     */
    @Test
    public void getDocStuById() {

        GetQuery query = new GetQuery();
        query.setId("1003");

        Stu stu = esTemplate.queryForObject(query, Stu.class);
        System.out.println(stu);

        // 类似sql：select * from stu where id=1003
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDocStu() {

        esTemplate.delete(Stu.class, "1003");

        // 类似sql：delete from stu where id = 1003
    }

    //=================搜索===================//

    /**
     * 简单分页搜索
     */
    @Test
    public void searchDocStu() {

        Pageable pageable = PageRequest.of(1, 2);

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("desc", "save man"))
                .withPageable(pageable)
                .build();
        AggregatedPage<Stu> pagedStu = esTemplate.queryForPage(query, Stu.class);
        System.out.println("总分页数：" + pagedStu.getTotalPages());
        List<Stu> list = pagedStu.getContent();
        for (Stu s : list) {
            System.out.println(s);
        }
    }

    /**
     * 高亮
     */
    @Test
    public void searchDocStuHighlight() {

        Pageable pageable = PageRequest.of(1, 2);

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("desc", "save man"))
                // 高亮设置
                .withHighlightFields(new HighlightBuilder.Field("desc")
                        .preTags("<font color='red'>")
                        .postTags("</font>"))
                .withPageable(pageable)
                .build();
        AggregatedPage<Stu> pagedStu = esTemplate.queryForPage(query, Stu.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {

                List<Stu> stuHighlight = new ArrayList<>();

                SearchHits hits = response.getHits();
                for (SearchHit hit : hits) {
                    HighlightField highlightField = hit.getHighlightFields().get("desc");
                    String highlightString = highlightField.getFragments()[0].toString();

                    Stu stuHL = new Stu();
                    stuHL.setDesc(highlightString);
                    // 设置其他属性
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    stuHL.setStuId((Long) sourceAsMap.get("id"));
                    stuHL.setSign((String) sourceAsMap.get("sign"));
                    stuHL.setName((String) sourceAsMap.get("name"));
                    stuHL.setMoney(Float.valueOf(sourceAsMap.get("money").toString()));
                    stuHL.setAge((Integer) sourceAsMap.get("age"));

                    stuHighlight.add(stuHL);
                }

                if (stuHighlight.size() > 0) {
                    return new AggregatedPageImpl<>((List<T>) stuHighlight);
                }

                return null;
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
                return null;
            }
        });
        System.out.println("总分页数：" + pagedStu.getTotalPages());
        List<Stu> list = pagedStu.getContent();
        for (Stu s : list) {
            System.out.println(s);
        }
    }

    /**
     * 排序
     */
    @Test
    public void searchDocStuSort() {
        Pageable pageable = PageRequest.of(0, 10);

        SortBuilder sortBuilder = new FieldSortBuilder("money").order(SortOrder.DESC);

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("desc", "save man"))
                .withPageable(pageable)
                // 排序
                .withSort(sortBuilder)
                .build();
        AggregatedPage<Stu> pagedStu = esTemplate.queryForPage(query, Stu.class);
        System.out.println("总分页数：" + pagedStu.getTotalPages());
        List<Stu> list = pagedStu.getContent();
        for (Stu s : list) {
            System.out.println(s);
        }
    }

}
