package Angelo.dao.impl;

import Angelo.dao.IUserDao;
import Angelo.domain.User;
import Angelo.util.XmlUtils;
import org.dom4j.Element;

import java.text.SimpleDateFormat;

public class UserDaoImpl implements IUserDao {
    @Override
    public User find(String userName, String userPwd) {
        try{
            org.dom4j.Document document = XmlUtils.getDocument();
            //使用XPath表达式来操作XML节点
            Element e = (Element) document.selectSingleNode("//user[@userName='"+userName+"' and @userPwd='"+userPwd+"']");
            if(e==null){
                return null;
            }
            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setEmail(e.attributeValue("email"));
            user.setUserPwd(e.attributeValue("userPwd"));
            user.setUserName(e.attributeValue("userName"));
            String birth = e.attributeValue("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(birth));

            return user;

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void add(User user) {
        try{
            org.dom4j.Document document = XmlUtils.getDocument();
            org.dom4j.Element root = document.getRootElement();
            org.dom4j.Element user_node = root.addElement("user");  //创建user结点，并挂到root
            user_node.setAttributeValue("id", user.getId());
            user_node.setAttributeValue("userName", user.getUserName());
            user_node.setAttributeValue("userPwd", user.getUserPwd());
            user_node.setAttributeValue("email", user.getEmail());

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            user_node.setAttributeValue("birthday", sdf.format(user.getBirthday()));

            XmlUtils.write2Xml(document);

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String userName) {
        try{
            org.dom4j.Document document = XmlUtils.getDocument();
            Element e = (Element) document.selectSingleNode("//user[@userName='"+userName+"']");
            if(e==null){
                return null;
            }
            User user = new User();
            user.setId(e.attributeValue("id"));
            user.setEmail(e.attributeValue("email"));
            user.setUserPwd(e.attributeValue("userPwd"));
            user.setUserName(e.attributeValue("userName"));
            String birth = e.attributeValue("birthday");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(sdf.parse(birth));

            return user;

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
