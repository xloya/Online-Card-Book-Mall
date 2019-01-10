package test.com.example.demo.util;


import com.example.demo.util.RandomCodeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

/** 
* RandomCodeUtils Tester. 
* 
* @author <Authors name> 
* @since <pre>七月 31, 2018</pre> 
* @version 1.0 
*/ 
public class RandomCodeUtilsTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getStringRandom() 
* 
*/ 
@Test
public void testGetStringRandom() throws Exception { 
//TODO: Test goes here...
    RandomCodeUtils test = new RandomCodeUtils();
//测试
    System.out.println(test.getStringRandom());
} 


} 
