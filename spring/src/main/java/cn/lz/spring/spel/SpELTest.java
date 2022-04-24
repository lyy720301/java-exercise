package cn.lz.spring.spel;

import cn.lz.spring.po.UserPo;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;


public class SpELTest {
    public static void main(String[] args) {
        try {
            UserPo userPo = new UserPo();
            userPo.setName("lz");
            ExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression("name");
            System.out.println(exp.getValue(userPo));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
