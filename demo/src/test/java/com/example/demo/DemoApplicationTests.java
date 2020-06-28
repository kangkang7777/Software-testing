package com.example.demo;

import com.example.demo.service.CheckTriangle;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    CheckTriangle triangleTest = new CheckTriangle();

    /*
     * 每个测试调用前都会执行
     */
    @Before
    public void before()
    {
        System.out.println("Before");
    }

    /*
     * 测试前会被调用一次
     */
    @BeforeClass
    public static void beforeClass()
    {
        System.out.println("BeforeClass");
    }

    /*
     * 每个测试调用后都会执行
     */
    @After
    public void after()
    {
        System.out.println("After");
    }

    /*
     * 测试后会被调用一次
     */
    @AfterClass
    public static void afterClass()
    {
        System.out.println("AfterClass");
    }

    /*
     * 设置超时时间
     */
    @Test(timeout = 1000)
    public void checkTriangleTrue() {
        //正确
        assertEquals("a不能为0",triangleTest.checkTriangle(0,1,1));
        assertEquals("a不在取值范围内",triangleTest.checkTriangle(-1,1,1));
        assertEquals("非三角形",triangleTest.checkTriangle(1,2,3));
        assertEquals("等边三角形",triangleTest.checkTriangle(1,1,1));
        assertEquals("等腰三角形",triangleTest.checkTriangle(2,2,3));
        assertEquals("非等腰三角形",triangleTest.checkTriangle(2,3,4));

        System.out.println("测试进行中");
    }

    /*
     * Ignore禁用了测试
     */
    @Test
    @Ignore
    public void checkTriangleFalse() {
        assertEquals("a不能为0",triangleTest.checkTriangle(2,1,1));
    }
}
