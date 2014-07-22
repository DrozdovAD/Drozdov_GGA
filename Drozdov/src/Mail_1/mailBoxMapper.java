package Mail_1;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

public interface mailBoxMapper {
	@Select("select id,adress from MailBox")
	List<MailBox> selectAllBoxes();
	
	@Insert("Insert into MailBox values(#{0},#{1})")
	void InsertIntoMailBox(int id, String adress);

	@Insert(" insert into Letters (fromid, text,toid) values (#{from} ,#{text}, #{to})")
	void InsertIntoLetters(@Param("from") int fromid,@Param("text") String text,@Param("to") int toid);

	@Select("select max(id) from MailBox")
	int selectMaxBoxes();

	@Delete("delete from MailBox"+"delete from Letters")
	void deleteAll();

}