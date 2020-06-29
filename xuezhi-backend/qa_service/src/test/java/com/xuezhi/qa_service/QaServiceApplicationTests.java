package com.xuezhi.qa_service;

import com.xuezhi.qa_service.adapter.out.QARepositoryImpl;
import com.xuezhi.qa_service.domain.entity.Question;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class QaServiceApplicationTests {
    @Autowired
    QARepositoryImpl qaRepositoryImpl=new QARepositoryImpl();

    @Test
    void getQuestionByRegexTest(){
        Question q = new Question();
        q.setQuestionId("");
        q.setTitle("");
        q.setDescription("");
        q.setAskerId("");
        q.setSchool("public");
        List<Question> list = new ArrayList<>();
        list.add(q);

        assertEquals(null, qaRepositoryImpl.getQuestionByRegex("","public") );
        assertEquals(null, qaRepositoryImpl.getQuestionByRegex("同济大学食堂","") );
        assertEquals(null, qaRepositoryImpl.getQuestionByRegex("sdsd","fff") );
        assertEquals(null, qaRepositoryImpl.getQuestionByRegex("sdsd","public") );
        assertEquals(list, qaRepositoryImpl.getQuestionByRegex("同济大学食堂","public") );
    }
    @Test
    public void updateQuestionTest() {
        assertEquals(false,qaRepositoryImpl.updateQuestion("","xxx","xx"));
        assertEquals(false,qaRepositoryImpl.updateQuestion("001","","xx"));
        assertEquals(false,qaRepositoryImpl.updateQuestion("001","xxx",""));
        assertEquals(false,qaRepositoryImpl.updateQuestion("1!@$!!%","xxx","xx"));
        assertEquals(true,qaRepositoryImpl.updateQuestion("001","xxx","x"));
        assertEquals(true,qaRepositoryImpl.updateQuestion("002","xxx","xx"));
        assertEquals(true,qaRepositoryImpl.updateQuestion("001","xxxx","xx"));
    }
    @Test
    public void addAnswerTest(){
        assertEquals(false,qaRepositoryImpl.addAnswer("","01","xx"));
        assertEquals(false,qaRepositoryImpl.addAnswer("001","","xx"));
        assertEquals(false,qaRepositoryImpl.addAnswer("001","01",""));
        assertEquals(false,qaRepositoryImpl.addAnswer("1!@$!!%","01","xx"));
        assertEquals(false,qaRepositoryImpl.addAnswer("001","1!@$!!%","xx"));
        assertEquals(true,qaRepositoryImpl.addAnswer("001","01","1!@$!!%"));
        assertEquals(true,qaRepositoryImpl.addAnswer("001","01","x"));
        assertEquals(true,qaRepositoryImpl.addAnswer("002","01","xx"));
        assertEquals(true,qaRepositoryImpl.addAnswer("001","02","xx"));
    }
    @Test
    public void deleteAnswerTest(){
        assertEquals(false,qaRepositoryImpl.deleteAnswer("","01"));
        assertEquals(false,qaRepositoryImpl.deleteAnswer("001",""));
        assertEquals(false,qaRepositoryImpl.deleteAnswer("1!@$!!%","01"));
        assertEquals(false,qaRepositoryImpl.deleteAnswer("001","1!@$!!%"));
        assertEquals(true,qaRepositoryImpl.deleteAnswer("002","01"));
        assertEquals(true,qaRepositoryImpl.deleteAnswer("001","02"));
    }
}
