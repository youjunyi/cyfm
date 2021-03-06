package com.ppcxy.common.repository.search;

import com.ppcxy.common.entity.search.SearchOperator;
import com.ppcxy.common.entity.search.SearchRequest;
import com.ppcxy.common.entity.search.Searchable;
import com.ppcxy.common.entity.search.filter.SearchFilter;
import com.ppcxy.common.entity.search.filter.SearchFilterHelper;
import com.ppcxy.common.repository.UserRepository;
import com.ppcxy.common.repository.entity.Sex;
import com.ppcxy.common.repository.entity.User;
import com.ppcxy.common.repository.test.BaseUserIT;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>测试查询条件</p>
 */
public class SearchUserRepositoryIT extends BaseUserIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testEqForEnum() {
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setSex(Sex.male);
            userRepository.save(user);
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("baseInfo.sex_eq", "male");
        Searchable search = Searchable.newSearchable(searchParams);
        Assert.assertEquals(count, userRepository.count(search));
    }

    @Test
    public void testNotEqForEnum() {
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setSex(Sex.male);
            userRepository.save(user);
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("baseInfo.sex_ne", "male");
        Searchable search = Searchable.newSearchable(searchParams);
        Assert.assertEquals(0, userRepository.count(search));
    }

    @Test
    public void testEqForInteger() {
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setAge(15);
            userRepository.save(user);
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("baseInfo.age_eq", "15");
        SearchRequest search = new SearchRequest(searchParams);
        Assert.assertEquals(count, userRepository.count(search));
    }


    @Test
    public void testEqForDate() throws ParseException {
        int count = 15;
        String dateStr = "2012-01-15 16:59:00";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.setRegisterDate(df.parse(dateStr));
            userRepository.save(user);
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("registerDate_eq", dateStr);
        SearchRequest search = new SearchRequest(searchParams);
        Assert.assertEquals(count, userRepository.count(search));
    }


    @Test
    public void testLtAndGtForDate() throws ParseException {
        int count = 15;
        String dateStr = "2012-01-15 16:59:00";
        String dateStrFrom = "2012-01-15 16:58:00";
        String dateStrEnd = "2012-01-15 16:59:01";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setBirthday(new Timestamp(df.parse(dateStr).getTime()));
            userRepository.save(user);
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("baseInfo.birthday_gt", dateStrFrom);
        searchParams.put("baseInfo.birthday_lt", dateStrEnd);
        Searchable search = Searchable.newSearchable(searchParams);
        Assert.assertEquals(count, userRepository.count(search));
    }


    @Test
    public void testLteAndGteForDate() throws ParseException {
        int count = 15;
        String dateStr = "2012-01-15 16:59:00";
        String dateStrFrom = "2012-01-15 16:59:00";
        String dateStrEnd = "2012-01-15 16:59:00";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setBirthday(new Timestamp(df.parse(dateStr).getTime()));
            userRepository.save(user);
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("baseInfo.birthday_gte", dateStrFrom);
        searchParams.put("baseInfo.birthday_lte", dateStrEnd);
        Searchable search = Searchable.newSearchable(searchParams);
        Assert.assertEquals(count, userRepository.count(search));
    }


    @Test
    public void testIsNotNull() throws ParseException {
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            userRepository.save(user);
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("username_isNotNull", null);
        Searchable search = Searchable.newSearchable(searchParams);
        Assert.assertEquals(count, userRepository.count(search));
    }

    @Test
    public void testInWithList() throws ParseException {
        List<Long> uuids = new ArrayList<Long>();
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            userRepository.save(user);
            uuids.add(user.getId());
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("id_in", uuids);
        Searchable search = Searchable.newSearchable(searchParams);
        Assert.assertEquals(count, userRepository.count(search));
    }


    @Test
    public void testInWithArray() throws ParseException {
        List<Long> uuids = new ArrayList<Long>();
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            userRepository.save(user);
            uuids.add(user.getId());
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("id_in", uuids.toArray(new Long[count]));
        Searchable search = Searchable.newSearchable(searchParams);
        Assert.assertEquals(count, userRepository.count(search));
    }

    @Test
    public void testInWithSingleValue() throws ParseException {
        List<Long> uuids = new ArrayList<Long>();
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            userRepository.save(user);
            uuids.add(user.getId());
        }
        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put("id_in", uuids.get(0));
        Searchable search = Searchable.newSearchable(searchParams);
        Assert.assertEquals(1, userRepository.count(search));
    }


    @Test
    public void testOr() {
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setAge(i);
            userRepository.save(user);
        }
        Searchable search = Searchable.newSearchable();
        search.or(
                SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.gt, 13),
                SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.lt, 1)
        );

        Assert.assertEquals(2, userRepository.count(search));
    }

    @Test
    public void testAnd() {
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setAge(i);
            userRepository.save(user);
        }
        Searchable search = Searchable.newSearchable();
        search.and(
                SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.gte, 13),
                SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.lte, 14)
        );

        Assert.assertEquals(2, userRepository.count(search));
    }

    @Test
    public void testAndOr() {
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setAge(i);
            userRepository.save(user);
        }
        Searchable search = Searchable.newSearchable();
        SearchFilter condition11 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.gte, 0);
        SearchFilter condition12 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.lte, 2);
        SearchFilter and1 = SearchFilterHelper.and(condition11, condition12);

        SearchFilter condition21 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.gte, 3);
        SearchFilter condition22 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.lte, 5);

        SearchFilter and2 = SearchFilterHelper.and(condition21, condition22);

        search.or(and1, and2);

        Assert.assertEquals(6, userRepository.count(search));
    }


    @Test
    public void testOrAnd() {
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setAge(i);
            userRepository.save(user);
        }
        Searchable search = Searchable.newSearchable();

        SearchFilter condition11 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.eq, 3);
        SearchFilter condition12 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.eq, 5);
        SearchFilter or1 = SearchFilterHelper.or(condition11, condition12);

        SearchFilter condition21 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.eq, 3);
        SearchFilter condition22 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.eq, 4);

        SearchFilter or2 = SearchFilterHelper.or(condition21, condition22);

        //( =3 or =5) and (=3 or =4)
        search.and(or1, or2);

        Assert.assertEquals(1, userRepository.count(search));
    }


    @Test
    public void testNestedOrAnd() {
        int count = 15;
        for (int i = 0; i < count; i++) {
            User user = createUser();
            user.getBaseInfo().setAge(i);
            userRepository.save(user);
        }
        Searchable search = Searchable.newSearchable();

        SearchFilter condition11 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.eq, 3);

        SearchFilter condition12 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.lte, 4);
        SearchFilter condition13 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.gte, 4);

        SearchFilter or11 = SearchFilterHelper.or(condition12, condition13);
        SearchFilter or1 = SearchFilterHelper.or(condition11, or11);

        SearchFilter condition21 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.eq, 3);
        SearchFilter condition22 = SearchFilterHelper.newCondition("baseInfo.age", SearchOperator.eq, 4);

        SearchFilter or2 = SearchFilterHelper.or(condition21, condition22);

        //( =3 or (>=4 and <=4)) and (=3 or =4)
        search.and(or1, or2);

        Assert.assertEquals(2, userRepository.count(search));
    }
}

