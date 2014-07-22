package Mail_1;

import java.util.List;

import org.apache.ibatis.annotations.Select;


public interface mailBoxMapper {
	@Select("select id,adress from MailBox")
	List<MailBox> loadMailBoxes();
}
